package com.tcl.uf.member.aop;

import java.util.Date;

import com.tcl.commondb.member.model.MemberRightsAndInterestsModel;
import com.tcl.commondb.member.repository.MemberRightsAndInterestsModelRepository;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.member.dto.GrowthValueDto;
import com.tcl.uf.member.utils.RedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author youyun.xu
 * @ClassName: GrowthInstantiateAspect
 * @Description 通过切面初始化用户成长值信息
 * @date 2020/9/2 17:10
 */
@Aspect
@Component
public class GrowthInstantiateAspect {

    private static final Logger log = LoggerFactory.getLogger(GrowthInstantiateAspect.class);

    @Autowired
    private MemberRightsAndInterestsModelRepository memberRightsAndInterestsModelRepository;

    @Autowired
    private RedisUtils redisUtils;

    // 定义切入点
    @Pointcut("@annotation(com.tcl.uf.member.annotation.InitGrowth)")
    public void initGrowth() {
    }

    /**
     * 获取参数列表
     *
     * @param joinPoint
     * @return Map<String       ,               Object>
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    private static Map<String, Object> getFieldsName(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = pnd.getParameterNames(method);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }

    @Before("initGrowth()")
    public void p1(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object obj = args[0];
        GrowthValueDto growthValueDto = null;
        if (obj instanceof GrowthValueDto) {
            growthValueDto = (GrowthValueDto) args[0];
        }
        if (growthValueDto == null) {
            return;
        }
        Long ssoid = growthValueDto.getSsoid();
        if (ssoid == null) {
            throw new JMException(-1, "ssoid 不能为空");
        }
        String cacheKey = "GROWTH_INIT:" + ssoid;
        Object value = redisUtils.get(cacheKey);
        if (value == null) {
            int count = memberRightsAndInterestsModelRepository.countBySsoid(ssoid);
            if (count <= 0) {
                try {
                    MemberRightsAndInterestsModel memberRightsAndInterests = new MemberRightsAndInterestsModel();
                    memberRightsAndInterests.setSsoid(ssoid);
                    memberRightsAndInterests.setGrowthValue(0);
                    memberRightsAndInterests.setCreateTime(new Date());
                    memberRightsAndInterests.setUpdateTime(new Date());
                    memberRightsAndInterestsModelRepository.saveAndFlush(memberRightsAndInterests);
                    //放入缓存中
                    redisUtils.set(cacheKey, ssoid);
                } catch (Exception e) {
                    log.error("该用户初始化记录已经存在", e);
                }
            }
        }
    }
}

package com.tcl.uf.points.vo.taskcomplete;

import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.points.service.PointTaskService;
import com.tcl.uf.points.vo.AppPointTaskVo;
import com.tcl.uf.points.vo.PointTaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 积分任务
 */  
public abstract class TaskComplete {
	
	@Autowired
	PointTaskService pointTaskService;
    
    /**
     * 校验用户是否符合奖励条件
     */
    public void check(TokenAppUserInfo userInfo,int typeCode)throws JMException{
    	
    };

    /**
     * 执行奖励
     */
    public void finish(TokenAppUserInfo userInfo,int typeCode,String source)throws JMException{
    	
    };

    /**
     * 校验任务是否有效
     */
    /**
     * 校验任务是否有效
     */ 
	public void taskEffective(int type) throws JMException{
		List<PointTaskVo> list = pointTaskService.queryValidTask();
		List<Integer> typeCodeList = list.stream().map(PointTaskVo::getTypeCode).collect(Collectors.toList());
		if (!typeCodeList.contains(type)) {
			throw new JMException("任务已过期");
		}
	};
}

package com.tcl.uf.points.service.impl;

import java.util.List;

import com.tcl.uf.common.base.constant.CommonErrorConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tcl.commondb.points.model.PointCoupon;
import com.tcl.commondb.points.model.PointCouponExchangeRecord;
import com.tcl.commondb.points.repository.PointCouponExchangeRecordRepository;
import com.tcl.commondb.points.repository.PointCouponRepository;
import com.tcl.commonservice.service.ContentService;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.points.controller.PointCouponController;
import com.tcl.uf.points.service.PointCouponService;
import com.tcl.uf.points.service.PointService;
import com.tcl.uf.points.vo.PointCouponVo;

@Service
public class PointCouponServiceImpl implements PointCouponService {
    Log _log = LogFactory.getLog(PointCouponController.class);
    @Autowired
    private PointCouponRepository pointCouponRepository;
    @Autowired
    private PointService pointService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private PointCouponExchangeRecordRepository pointCouponExchangeRecordRepository;
    @Override
    public Page<PointCoupon> listPage(Pageable pageable) {
        return pointCouponRepository.findAllByIsDeleteIs((byte) 1,pageable);
    }

    @Override
    public Long save(PointCouponVo pointVo) {
        long local = System.currentTimeMillis();
        PointCoupon pointCoupon = new PointCoupon();
        BeanUtils.copyProperties(pointVo,pointCoupon);
        pointCoupon.setCreateTime(local);
        pointCoupon.setUpdateTime(local);
        pointCoupon.setIsDelete((byte)1);
        pointCoupon.setStatus(2);
        _log.info("---------PointCouponController----save:{}"+ JSON.toJSONString(pointCoupon));
        pointCouponRepository.save(pointCoupon);
        return pointCoupon.getId();
    }

    @Override
    public Long update(PointCouponVo pointVo) {
    	PointCoupon pointCoupon = new PointCoupon();
        BeanUtils.copyProperties(pointVo,pointCoupon);
        pointCoupon.setUpdateTime(System.currentTimeMillis());
        pointCouponRepository.save(pointCoupon);
        return null;
    }

    @Override
    public boolean del(Long id) {
        PointCoupon one = pointCouponRepository.getOne(id);
        if(null != one){
			/*//判断是不是已发布
			 * if(one.getStatus().equals(2)) {
				return false;
			}*/
            one.setIsDelete((byte)2);
            one.setUpdateTime(System.currentTimeMillis());
            pointCouponRepository.save(one);
        }
        return true;
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        PointCoupon one = pointCouponRepository.getOne(id);
        if(null != one){
            one.setStatus(status);
            one.setUpdateTime(System.currentTimeMillis());
            pointCouponRepository.save(one);
        }
        return true;
    }
    

	@Override
	public PointCouponVo findById(Long id) {
		PointCouponVo vo = new PointCouponVo();
		PointCoupon one = pointCouponRepository.getOne(id);
		BeanUtils.copyProperties(one, vo);
		return vo;
	}

    @Override
    public boolean exchangeCoupon(String token, Long ssoid, Long pointCoupinId)throws JMException {
        PointCoupon po = pointCouponRepository.findByIdEquals(pointCoupinId);
        if(null == po){
            throw new JMException(CommonErrorConstant.POINT_EXCHANGE_COUPON_MISSING.getCode(),CommonErrorConstant.POINT_EXCHANGE_COUPON_MISSING.getMsg());
        }
        Integer points = pointService.getPointBySSOID(token, ssoid);
        if(points == 0 || po.getUsePoint()>points){
            throw new JMException(CommonErrorConstant.POINT_NOT_ENOUGH.getCode(),CommonErrorConstant.POINT_NOT_ENOUGH.getMsg());
        }
        List<PointCouponExchangeRecord> exchangeIs = pointCouponExchangeRecordRepository.findAllByAccountIdEqualsAndPointCouponIdEquals(ssoid, pointCoupinId);
        if(null != exchangeIs && po.getUseNum()!=0 &&exchangeIs.size()<po.getUseNum()){
            throw new JMException(CommonErrorConstant.POINT_EXCHANGE_NUM_MAX.getCode(),CommonErrorConstant.POINT_EXCHANGE_NUM_MAX.getMsg());
        }
        try {
            boolean resultPoint = pointService.pushPoints(token, ssoid, po.getUsePoint(), po.getName(), "积分兑换券", "-","积分兑换券",0);
            if(resultPoint){
                ResponseData<String> result = contentService.sendCoupon(ssoid + "", po.getDcCouponId());
                _log.info("---PointCouponService exchangeCoupon ssoid:"+ssoid+"  ,resultPoint"+resultPoint+"   resultCoupon:"+JSON.toJSONString(result));
                if(result.getCode() == 1){
                    PointCouponExchangeRecord record = new PointCouponExchangeRecord();
                    record.setAccountId(ssoid);
                    record.setPointCouponId(pointCoupinId);
                    record.setCreateTime(System.currentTimeMillis());
                    pointCouponExchangeRecordRepository.save(record);
                    return true;
                }
                return false;
            }
        }catch (Exception e){
            _log.error("PointCouponService exchangeCoupon error:"+e);
            throw new JMException(CommonErrorConstant.POINT_EXCHANGE_FAIL.getCode(),CommonErrorConstant.POINT_EXCHANGE_FAIL.getMsg());
        }
        return false;
    }
    

}

package com.tcl.uf.points.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.points.service.PointCouponService;
import com.tcl.uf.points.vo.PointCouponVo;

/**
 * 积分优惠券
 */
@RestController
@RequestMapping("/coupon")
public class PointCouponController extends AbstractBaseController {
	Log _log = LogFactory.getLog(PointCouponController.class);
	@Autowired
	private PointCouponService pointCouponService;

	@PostMapping("/list")
	public ResponseData list(@RequestBody Map<String, Integer> map, HttpServletRequest request) {

		Integer page = map.get("page");
		Integer size = map.get("size");
		Pageable pageable = PageUtils.getPageable(page, size);

		return success(pointCouponService.listPage(pageable));
	}

	@PostMapping("/save")
	public ResponseData save(@RequestBody PointCouponVo pointCoupon) {
		Map<String, Object> map = voCheckNotNull(pointCoupon);
		boolean flag = Boolean.parseBoolean(String.valueOf(map.get("flag")));
		if (!flag) {
			return new ResponseData<>(0, String.valueOf(map.get("msg")), "param is null");
		}
		pointCouponService.save(pointCoupon);
		return success();
	}

	@PostMapping("/update")
	public ResponseData update(@RequestBody PointCouponVo pointCoupon) {
		if (null == pointCoupon.getId()) {
			return fail("缺少参数:id");
		}
		Map<String, Object> map = voCheckNotNull(pointCoupon);
		boolean flag = Boolean.parseBoolean(String.valueOf(map.get("flag")));
		if (!flag) {
			return new ResponseData<>(0, String.valueOf(map.get("msg")), "param is null");
		}
		pointCouponService.update(pointCoupon);
		return success();
	}

	@GetMapping("/del/{id}")
	public ResponseData del(@PathVariable("id") Long id) {
		boolean flag = pointCouponService.del(id);
		return success(flag);
	}

	@PostMapping("/updateStatus")
	public ResponseData updateStatus(@RequestBody PointCouponVo pointCoupon) {
		pointCouponService.updateStatus(pointCoupon.getId(), pointCoupon.getStatus());
		return success();
	}

	/**
	 * 兑换
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/exchange/{id}")
	public Object exchange(@PathVariable("id") Long id, HttpServletRequest request) throws JMException {
		TokenAppUserInfo user = getAppUserInfo(request);
		pointCouponService.exchangeCoupon(user.getToken(), user.getAccountId(), id);
		return success();
	}

	private Map<String, Object> voCheckNotNull(PointCouponVo vo) {
		Map<String, Object> map = new HashMap<>();
		map.put("flag", false);
		String msg = "";
		if (vo.getStartTime() == null) {
			msg = "开始生效时间不能为空";
			map.put("msg", msg);
			return map;
		} else if ((vo.getStartTime() + 300000l) < TimeUtils.getCurrentTimeMillis()) {
			msg = "开始生效时间不能早于当前时间超过5分钟";
			map.put("msg", msg);
			return map;
		} else if (vo.getEndTime() == null) {
			msg = "结束时间不能为空";
			map.put("msg", msg);
			return map;
		} else if (vo.getStartTime() >= vo.getEndTime()) {
			msg = "开始时间不能大于等于结束时间";
			map.put("msg", msg);
			return map;
		}else if (vo.getEndTime() < TimeUtils.getCurrentTimeMillis()) {
			msg = "结束时间不能早于当前时间";
			map.put("msg", msg);
			return map;
		} else if (vo.getContent() == null) {
			msg = "使用范围不能为空";
			map.put("msg", msg);
			return map;
		} else if (vo.getDcCouponId() == null) {
			msg = "电商系统优惠券不能为空";
			map.put("msg", msg);
			return map;
		} else if (vo.getName() == null) {
			msg = "优惠券名称不能为空";
			map.put("msg", msg);
			return map;
		} else if (vo.getUseNum() == null) {
			msg = "兑换次数不能为空";
			map.put("msg", msg);
			return map;
		} else if (vo.getUsePoint() == null) {
			msg = "兑换积分不能为空";
			map.put("msg", msg);
			return map;
		}
		map.put("flag", true);
		return map;
	}
}

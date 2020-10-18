package com.tcl.uf.points.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcl.commondb.points.model.PointSignInRecord;
import com.tcl.commondb.points.repository.PointSignInRecordRepository;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.points.service.PointService;
import com.tcl.uf.points.service.PointSignInRecordService;
import com.tcl.uf.points.vo.SignInVo;


@Service
public class PointSignInRecordServiceImpl implements PointSignInRecordService {

	@Autowired
	private PointSignInRecordRepository pointSignInRecordRepository;
	@Autowired
	private PointService pointService;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Map<String, Object> saveSignIn(Long ssoid, String apptoken) {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("flag", false);
		Long time = TimeUtils.getCurrentTimeMillis();
		PointSignInRecord model = pointSignInRecordRepository.findFirstByAccountIdOrderBySignTimeDesc(ssoid);
		PointSignInRecord saveData = new PointSignInRecord();
		saveData.setSignTime(time);
		saveData.setIsDelete((byte) 1);
		saveData.setSignTime(time);
		saveData.setUpdateTime(time);
		saveData.setCreateTime(time);
		saveData.setAccountId(ssoid);
		if (model == null) {
			saveData.setDayNum(1);
		} else {
			if (format.format(new Date(model.getSignTime())).equals(format.format(new Date(time)))) {
				returnMap.put("msg", "今天已签到");
				return returnMap;
			} else if (format.format(new Date(model.getSignTime())).equals(format.format(new Date(time - 86400000l)))) {
				saveData.setDayNum((model.getDayNum().equals(7)) ? 1 : model.getDayNum() + 1);
			} else {
				saveData.setDayNum(1);
			}
		}
		pointSignInRecordRepository.save(saveData);
		if (saveData.getId() != null) {
			pointService.pushPoints(apptoken, ssoid, 2, "签到领取积分", "签到", "+", "用户签到",0);
			returnMap.put("flag", true);
		} else {
			returnMap.put("msg", "保存失败");
		}
		return returnMap;
	}

	@Override
	public boolean isSignIn(Long ssoid) {
		Long time = TimeUtils.getCurrentTimeMillis();
		PointSignInRecord model = pointSignInRecordRepository.findFirstByAccountIdOrderBySignTimeDesc(ssoid);
		if (model == null) {
			return false;
		} else {
			if (format.format(new Date(model.getSignTime())).equals(format.format(new Date(time)))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<SignInVo> querySignRecord(Long ssoid) {
		Long time = TimeUtils.getCurrentTimeMillis();
		List<SignInVo> voList = new ArrayList<>();
		PointSignInRecord model = pointSignInRecordRepository.findFirstByAccountIdOrderBySignTimeDesc(ssoid);
		if (model != null) {
			if (!(format.format(new Date(model.getSignTime())).equals(format.format(new Date(time)))
					|| format.format(new Date(model.getSignTime())).equals(format.format(new Date(time - 86400000l))))) {
				return voList;
			}
			List<PointSignInRecord> list = pointSignInRecordRepository.querySignRecord(ssoid);
			for (PointSignInRecord pointSignInRecord : list) {
				SignInVo vo = new SignInVo();
				vo.setAccountId(pointSignInRecord.getAccountId())
						.setDayNum(pointSignInRecord.getDayNum())
						.setId(pointSignInRecord.getId())
						.setSignTime(pointSignInRecord.getSignTime());
				voList.add(vo);
			}
		}
		return voList;
	}

}

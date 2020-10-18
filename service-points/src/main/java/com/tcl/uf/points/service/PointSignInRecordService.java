package com.tcl.uf.points.service;

import java.util.List;
import java.util.Map;

import com.tcl.uf.points.vo.SignInVo;

public interface PointSignInRecordService {

	Map<String, Object> saveSignIn(Long ssoid,String token);

	boolean isSignIn(Long ssoid);

	List<SignInVo> querySignRecord(Long ssoid);

}

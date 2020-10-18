package com.tcl.uf.shop.controller;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.shop.model.IntegralShopVouchercardModel;
import com.tcl.commondb.shop.repository.IntegralShopVouchercardRepository;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.shop.service.IntegralShopVouchercardService;
import com.tcl.uf.shop.vo.IntegralShopVouchercardVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/vouchercard")
@ApiModel(description = "券卡管理")
public class IntegralShopVouchercardController {
	private static final Logger logger = LoggerFactory.getLogger(IntegralShopVouchercardController.class);
	@Autowired
	IntegralShopVouchercardService integralShopVouchercardService;
	@Autowired
	IntegralShopVouchercardRepository integralShopVouchercardRepository;
	@GetMapping(path = "/back/list")
	@ApiOperation(value = "列表信息", notes = "列表信息", httpMethod = "GET")
	public ResponseData<ListWithPage<IntegralShopVouchercardVo>> getlist(HttpServletRequest request,
        @RequestParam(value = "gno", required = true) String gno) {

		try {
			Pageable pageable = PageUtils.getPageFromRequestParam(request);
			ListWithPage<IntegralShopVouchercardVo> datas = integralShopVouchercardService.findList(gno,pageable);
			return new ResponseData<>(datas);
		} catch (Exception e) {
			logger.error("查询列表失败:{}", e.getMessage());
			return new ResponseData<>(0, "查询列表失败", "");
		}
	}

	@GetMapping(path = "/back/delrow")
	@ApiOperation(value = "删除记录" , notes="删除记录",httpMethod = "GET")
	public ResponseData<Object> delrow(HttpServletRequest request,@RequestParam(value = "id",defaultValue = "0") Long id ) {
		if(id==0){
			return new ResponseData<>(0, "id参数不能为空", "");
		}
		try {
			integralShopVouchercardRepository.deleteById(id);
		}
		catch (Exception e){
			return new ResponseData<>(0, "删除奖品图片失败", e.getMessage());
		}
		return new ResponseData<>(1, "删除奖品图片成功", "");
	}

	@GetMapping(path = "/back/cardstatis")
	@ApiOperation(value = "券码/卡号列表统计", notes = "券码/卡号列表统计", httpMethod = "GET")
	public ResponseData<JSONArray> cardstatis(HttpServletRequest request, @RequestParam(value = "gno", required = true) String gno) {
		JSONArray ja = new JSONArray();
		JSONObject jo= integralShopVouchercardService.getcardstatis(gno);
		ja.add(jo);
		return new ResponseData<>(ja);
	}

	@PostMapping(path = "/back/upload")
	@ApiOperation(value = "导入", notes = "导入", httpMethod = "POST")
	public ResponseData<Integer> upload(HttpServletRequest request,
										@RequestParam("file") MultipartFile file,
										@RequestParam(value = "gno", required = true) String gno) {
		if(gno==null){
			return new ResponseData<>(0, "奖品编码不能为空!", "");
		}

		return integralShopVouchercardService.uploadVouchercard(file,gno);
	}

	@PostMapping(path = "/back/updaterow")
	@ApiOperation(value = "更新状态", notes = "更新状态和回写中奖记录", httpMethod = "POST")
	public ResponseData<Object> updaterow(HttpServletRequest request,
										  @RequestParam(value = "stt", required = false,defaultValue = "0") Integer stt,
										  @RequestParam(value = "winid", required = false,defaultValue = "0") Integer winid,
										  @RequestParam(value = "id", required = true) Long id) {
		IntegralShopVouchercardModel shopVouchercardModel =integralShopVouchercardService.updaterow(id,stt);
		String retstr=null;
		try{
			retstr=shopVouchercardModel.getStatus()==1?"已使用":"未使用";
		}
		catch (NullPointerException e){
			return new ResponseData<>(0, "未找到对应券卡", e.getMessage());
		}
		return new ResponseData<>(0, shopVouchercardModel.getStatus().toString(), retstr);
	}

}

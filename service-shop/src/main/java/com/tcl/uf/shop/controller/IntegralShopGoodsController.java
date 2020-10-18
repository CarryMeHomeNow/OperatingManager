package com.tcl.uf.shop.controller;

import com.tcl.commondb.shop.model.enums.GoodsBrandEnum;
import com.tcl.commondb.shop.repository.IntegralShopGoodsRepository;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.shop.consts.ShopConstants;
import com.tcl.uf.shop.dto.IntegralShopGoodsDto;
import com.tcl.uf.shop.service.IntegralShopGoodsService;
import com.tcl.uf.shop.vo.IntegralShopGoodsFo;
import com.tcl.uf.shop.vo.IntegralShopGoodsVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import com.tcl.uf.common.base.util.StringsUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/shopgoods")
@ApiModel(description = "商品管理")
public class IntegralShopGoodsController extends AbstractBaseController {

    private static final Logger logger = LoggerFactory.getLogger(IntegralShopGoodsController.class);
    @Autowired
    IntegralShopGoodsService integralShopGoodsService;
    @Autowired
    IntegralShopGoodsRepository integralShopGoodsRepository;
    @Autowired
    public HttpServletRequest servletRequest;

    @GetMapping(path = "/back/list")
    @ApiOperation(value = "商品列表", notes = "后端商品列表", httpMethod = "GET")
    public ResponseData<ListWithPage<IntegralShopGoodsVo>> findlist(HttpServletRequest request,
           @RequestParam(value = "kw", required = false, defaultValue = "") String kw,
           @RequestParam(value = "bid", required = false, defaultValue = "") Long bid,
           @RequestParam(value = "stt", required = false, defaultValue = "") Integer stt) throws JMException {

        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            ListWithPage<IntegralShopGoodsVo> datas = integralShopGoodsService.findListbykw(kw, bid, stt, pageable);
            return new ResponseData<>(datas);

        } catch (Exception e) {
            logger.error("查询列表失败", e);
			throw new JMException("查询列表失败");
        }
    }

    @GetMapping(path = "/list")
    @ApiOperation(value = "商品列表", notes = "前端商品列表", httpMethod = "GET")
    public ResponseData<ListWithPage<IntegralShopGoodsFo>> goodslist(HttpServletRequest request,
           @RequestParam(value = "condition", required = false, defaultValue = "new") String condition,
           @RequestParam(value = "orderstr", required = false, defaultValue = "desc") String orderstr)throws JMException {

        try {
            TokenAppUserInfo info = getAppUserInfo(request);
            String token = info.getToken();
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            ListWithPage<IntegralShopGoodsFo> datas = integralShopGoodsService.findList(condition, orderstr, token, pageable);
            return new ResponseData<>(datas);

        } catch (Exception e) {
            logger.error("获取商品列表发生异常:{}", e.getMessage());
			throw new JMException("获取列表失败");
        }

    }

    @GetMapping(path = "/back/getgoodssn")
    @ApiOperation(value = "获取商品品类", notes = "获取商品品类", httpMethod = "GET")
    public ResponseData<String> getGoodssn(HttpServletRequest request) {
        // 产生商品编码
        String formatNumber = StringsUtil.getRandomFileName();
        String goodssn = "TPG" + formatNumber;
        return new ResponseData<>(goodssn);
    }

    @GetMapping(path = "/back/brandlist")
    @ApiOperation(value = "获取商品编码", notes = "获取商品编码", httpMethod = "POST")
    public ResponseData<List> brandlist(HttpServletRequest request) {
        List brandlist = GoodsBrandEnum.toList();
        return new ResponseData<>(brandlist);
    }

    @PostMapping(path = "/back/save")
    @ApiOperation(value = "保存活动基础配置信息", notes = "保存活动基础信息", httpMethod = "POST")
    public ResponseData<Object> saveOrUpdate(HttpServletRequest request, HttpServletResponse response,
		   @RequestBody IntegralShopGoodsDto shopGoodsDto) throws JMException{

        String retstr = "保存成功";
        try {
            if (StringUtils.isEmpty(shopGoodsDto.getId())) {
                integralShopGoodsService.createIntegralShopGoods(shopGoodsDto);
            } else {
                retstr = integralShopGoodsService.updateIntegralShopGoods(shopGoodsDto);
            }
        } catch (Exception e) {
            logger.error("活动配置发生异常{}", e.getMessage());
			throw new JMException("保存失败");
        }
        return new ResponseData(retstr);
    }

    @GetMapping(path = "/detail")
    @ApiOperation(value = "查询活动详情接口", notes = "根据id查询活动详情", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", dataType = "Long")})
    public ResponseData<IntegralShopGoodsVo> findDetail(HttpServletRequest request, HttpServletResponse response,
           @RequestParam(value = "id", required = false, defaultValue = "1") Long id) throws JMException  {
        IntegralShopGoodsVo integralShopGoodsVo;
        try {
            integralShopGoodsVo = integralShopGoodsService.findOne(id);
            if (integralShopGoodsVo == null) {
				throw new JMException("查无数据");
            }
        } catch (Exception e) {
            logger.error("查询活动详情失败", e);
			throw new JMException("查询活动详情失败");
        }
        return new ResponseData<>(integralShopGoodsVo);
    }

    @GetMapping(path = "/back/delrow")
    @ApiOperation(value = "删除记录", notes = "删除记录", httpMethod = "GET")
	public ResponseData<Object> delrow(HttpServletRequest request,@RequestParam(value = "id",defaultValue = "0") Long id ) throws JMException {

        if (id == 0) {
			throw new JMException("id参数不能为空");
        }
        try {
            integralShopGoodsRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseData<>(0, "删除奖品图片失败", e.getMessage());
        }
        return new ResponseData<>(1, "删除奖品图片成功", "");
    }

    @GetMapping(path = "/back/updatestt")
    @ApiOperation(value = "更新状态", notes = "更新状态", httpMethod = "POST")
    public ResponseData<Object> updatestatus(HttpServletRequest request,
                                             @RequestParam(value = "stt") Integer stt,
                                             @RequestParam(value = "id",defaultValue = "0") Long[] ids) throws JMException {

        if (ids == null) {
			throw new JMException("参数不能为空");
        }
        Integer cnt = integralShopGoodsService.updateStatus(ids, stt);
        String retstr = null;
        try {
            if (stt == 1) {
                retstr = ShopConstants.GOODS_DOWN;
            } else if (stt == 0) {
                retstr = ShopConstants.GOODS_UP;
            } else if (stt == -1) {
                retstr = ShopConstants.GOODS_DELETE;
            }
        } catch (NullPointerException e) {
            logger.error("未找到修改数据对象：{}", e.getMessage());
        }
        return new ResponseData<>(1, retstr, stt.toString());
    }


    @GetMapping(path = "/back/delimg")
    @ApiOperation(value = "删除商品展示图片", notes = "删除商品展示图片", httpMethod = "GET")
    public ResponseData<Object> delgoodsimg(HttpServletRequest request,
			@RequestParam(value = "id", required = true,defaultValue = "0") Long id) throws JMException{

        if (id == 0) {
			throw new JMException("id参数不能为空");
        }
        try {
            integralShopGoodsService.delgoodsimg(id);
        } catch (Exception e) {
            return new ResponseData<>(0, "删除商品图片失败", e.getMessage());
        }
        return new ResponseData<>(1, "删除商品图片成功", "");
    }

    @GetMapping(path = "/IntegralGoodslist")
    @ApiOperation(value = "商品列表", notes = "前端商品列表-Tangram", httpMethod = "GET")
    public ListWithPage<IntegralShopGoodsFo> getGoodslist(@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
                                                          @RequestParam(value = "size", required = true, defaultValue = "10") Integer size,
                                                          @RequestParam(value = "condition", required = false, defaultValue = "new") String condition,
                                                          @RequestParam(value = "orderstr", required = false, defaultValue = "desc") String orderstr,
                                                          @RequestParam(value = "cf", required = false, defaultValue = "0") Integer cf) {

        try {
            TokenAppUserInfo info = getAppUserInfo(servletRequest);
            String token = info.getToken();
            servletRequest.setAttribute("page", page);
            servletRequest.setAttribute("size", size);
            Pageable pageable = PageUtils.getPageFromRequestParam(servletRequest);
            ListWithPage<IntegralShopGoodsFo> datas = integralShopGoodsService.findList(condition, orderstr, token, pageable);
            return datas;

        } catch (Exception e) {
            logger.error("Tangram获取商品列表发生异常:{}", e.getMessage());
            return new ListWithPage<IntegralShopGoodsFo>();
        }

    }

	@GetMapping(path = "/back/updateorder")
	@ApiOperation(value = "设置排序", notes = "设置排序", httpMethod = "GET")
	public ResponseData<Object> updateorder(HttpServletRequest request,
											@RequestParam(value = "sort", required = true,defaultValue = "0") Integer sort,
											@RequestParam(value = "id", required = true,defaultValue = "0") Long id) throws JMException {

		if(id==0){
			throw new JMException("参数不能为空");
		}
		String retstr=null;
		Integer ret=integralShopGoodsService.updateOrder(id,sort);
		retstr=ret>0?"设置成功":"设置失败";

		return new ResponseData<>(1, retstr, "");
	}

}

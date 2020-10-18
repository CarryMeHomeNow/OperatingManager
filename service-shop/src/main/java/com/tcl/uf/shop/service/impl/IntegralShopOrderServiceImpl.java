package com.tcl.uf.shop.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.shop.model.IntegralShopGoodsModel;
import com.tcl.commondb.shop.model.IntegralShopOrderModel;
import com.tcl.commondb.shop.model.IntegralShopVouchercardModel;
import com.tcl.commondb.shop.repository.IntegralShopGoodsRepository;
import com.tcl.commondb.shop.repository.IntegralShopOrderRepository;
import com.tcl.commondb.shop.repository.IntegralShopVouchercardRepository;
import com.tcl.commonservice.service.PointsService;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.SqlBuilder;
import com.tcl.uf.common.base.util.StringsUtil;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.shop.consts.ShopConstants;
import com.tcl.uf.shop.dto.IntegralShopOrderDto;
import com.tcl.uf.shop.service.IntegralShopOrderService;
import com.tcl.uf.shop.vo.IntegralShopOrderVo;
import com.tcl.uf.shop.vo.IntegralShopOrderFo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service

public class IntegralShopOrderServiceImpl implements IntegralShopOrderService {

	@Autowired
	private IntegralShopOrderRepository integralShopOrderRepository;
    @Autowired
    private IntegralShopGoodsRepository integralShopGoodsRepository;
    @Autowired
    private IntegralShopVouchercardRepository vouchercardRepository;
    @Autowired
    private PointsService pointsService;

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(IntegralShopGoodsServiceImpl.class);
    private static final String STATUS = "status";

    @Override
	public void createIntegralShopOrder(IntegralShopOrderDto paramBody){

		IntegralShopOrderModel domain=new IntegralShopOrderModel();
		synchronized (IntegralShopOrderServiceImpl.class) {
			BeanUtils.copyProperties(paramBody,domain);
			integralShopOrderRepository.saveAndFlush(domain);
		}

	}

	@Override
	public String updateIntegralShopOrder(IntegralShopOrderDto paramBody){
		Long id = paramBody.getId();
		IntegralShopOrderModel domain = integralShopOrderRepository.getOne(id);
		BeanUtils.copyProperties(paramBody,domain);
		integralShopOrderRepository.saveAndFlush(domain);
		return "save success";

	}

	@Override
	public IntegralShopOrderVo findOne(Long id){
		IntegralShopOrderModel domain = integralShopOrderRepository.getOne(id);
        IntegralShopOrderVo viewobject=new IntegralShopOrderVo();
		BeanUtils.copyProperties(domain,viewobject);

        Timestamp utime= domain.getUpdatetime();
        if(utime!=null) {
            viewobject.setUpdatetime(TimeUtils.getLongDateStr(utime));
        }
        Timestamp ctime= domain.getCreatetime();
        if(ctime!=null) {
            viewobject.setCreatetime(TimeUtils.getLongDateStr(ctime));
        }

        Timestamp extime= domain.getExpresstime();
        if(extime!=null) {
            viewobject.setExpresstime(TimeUtils.getLongDateStr(extime));
        }
        Timestamp ptime= domain.getPaytime();
        if(ptime!=null) {
            viewobject.setPaytime(TimeUtils.getLongDateStr(ptime));
        }

        try {
            IntegralShopGoodsModel shopGoodsModel=integralShopGoodsRepository.getOne(domain.getGoodsid());
            viewobject.setGoodsname(shopGoodsModel.getGoodsName());
            viewobject.setGoodsimage(shopGoodsModel.getImage());
        }
        catch (Exception e){
            logger.error("{}","查找商品为空");
        }

		return viewobject;

	}

    @Override
    public IntegralShopOrderFo getOrderdetail(Long id) {
        IntegralShopOrderModel shopOrderModel = integralShopOrderRepository.getOne(id);
        IntegralShopOrderFo item=new IntegralShopOrderFo();
        item.setId(shopOrderModel.getId());
        item.setPointmoney(shopOrderModel.getPointprice());
        item.setTotal(shopOrderModel.getTotal());
        item.setBuyname(shopOrderModel.getBuyname());
        item.setLinkmobile(shopOrderModel.getLinkmobile());
        item.setAddress(shopOrderModel.getAddress());
        item.setOrdersn(shopOrderModel.getOrdersn());
        item.setExpresssn(shopOrderModel.getExpresssn());
        item.setCardsn(shopOrderModel.getCardsn());
        Timestamp utime= shopOrderModel.getUpdatetime();
        if(utime!=null) {
            item.setFinishtime(TimeUtils.getLongDateStr(utime));
        }
        Timestamp ctime= shopOrderModel.getCreatetime();
        if(ctime!=null) {
            item.setCreatetime(TimeUtils.getLongDateStr(ctime));
        }

        item.setPointnum(shopOrderModel.getPointnum());
        item.setStatus(shopOrderModel.getStatus());
        item.setComefrom(shopOrderModel.getComefrom());

        try {
            IntegralShopGoodsModel shopGoodsModel=integralShopGoodsRepository.getOne(shopOrderModel.getGoodsid());
            item.setGoodsname(shopGoodsModel.getGoodsName());
            item.setGoodsimage(shopGoodsModel.getImage());
        }
        catch (Exception e){
            logger.error("{}","查找商品为空");
        }

        return item;
    }

    @Override
	public IntegralShopOrderModel updateStatus(Long id, Integer stt) {
        String nowtime=TimeUtils.getCurrentTime();
		int ret=integralShopOrderRepository.updateStatus(id,stt,nowtime);
		IntegralShopOrderModel modelobject=new IntegralShopOrderModel();
		if(ret>0){
			modelobject=integralShopOrderRepository.getOne(id);
		}
		return modelobject;
    }

    @Override
    public Integer expresssn(Long id, String exno) {
        String nowtime=TimeUtils.getCurrentTime();
        Long receovertime=TimeUtils.dateToTimestamp(nowtime);
        Integer day15 = 3600*24*15;
        receovertime=receovertime+Long.valueOf(day15);
        return integralShopOrderRepository.updateexpress(id,exno,nowtime,receovertime);
    }

    @Override
	public ListWithPage<IntegralShopOrderVo> findListbykw(String keyword, Integer status, String startDay, String endDay, Pageable pageable) {
        JSONObject condition=new JSONObject();
        condition.put("keyword",keyword);
        condition.put(STATUS,status);
        condition.put("startday",startDay);
        condition.put("endday",endDay);

        Page<IntegralShopOrderVo> shopOrderVos=this.getListsrc(condition,pageable);
        List<IntegralShopOrderVo> shopOrderVoList=shopOrderVos.getContent();
        Long total=shopOrderVos.getTotalElements();

        return PageUtils.formatData(shopOrderVoList, pageable, total);

	}

	@Override
	public ListWithPage<IntegralShopOrderFo> findList(String ssoid, Integer status, Pageable pageable){
        JSONObject condition=new JSONObject();
        condition.put("ssoid",ssoid);
        condition.put(STATUS,status);
        Page<IntegralShopOrderVo> shopOrderVos=this.getListsrc(condition,pageable);
        List<IntegralShopOrderVo> shopOrderVoList=shopOrderVos.getContent();
        Long total=shopOrderVos.getTotalElements();

        List<IntegralShopOrderFo> content = new ArrayList<>();
        for (IntegralShopOrderVo shopOrderVo:shopOrderVoList){
            IntegralShopOrderFo item=this.shopOrderVotoOthervo(shopOrderVo);
            content.add(item);
        }
        return PageUtils.formatData(content, pageable, total);
	}

    @Override
    @Transactional(rollbackOn={RuntimeException.class, Exception.class})
    public Integer saveOrder(Integer cf, Long goodsid, Integer total, Long addressid, String address, String linkman, String linkmobile,Long ssoid, String token) {
        String nowtime=TimeUtils.getCurrentTime();
        IntegralShopGoodsModel goodsModel=integralShopGoodsRepository.getStock(goodsid);
        if(goodsModel!=null&&goodsModel.getStorge()==0){
            throw new NullPointerException("库存不足!");
        }

        ResponseData<Integer> pointinfo=pointsService.getPointsBySSOID(token);
        Long mypoint=Long.valueOf(pointinfo.getData());
        String ordersn=StringsUtil.getRandomFileName();
        if(goodsModel!=null&&goodsModel.getStorge()>0){
            // 乐观锁更新库存
            Integer update=integralShopGoodsRepository.desStockForWithholdnum(goodsid,goodsModel.getWithholdnum());
            // 库存扣减失败，说明其他线程已经修改过数据，本次扣减库存失败，可以重试一定次数或者返回
            if(update==0){
                throw new NullPointerException("系统繁忙，请稍后重试!");
            }

            // 用户积分扣除
            Long price=goodsModel.getPointmoney() * total.longValue();
            if(price > mypoint){
                throw new NullPointerException("用户积分不足");
            }

            IntegralShopOrderModel orderModel=new IntegralShopOrderModel();
            orderModel.setComefrom(cf);
            orderModel.setGoodsid(goodsid.longValue());
            orderModel.setGoodsprice(goodsModel.getPrice());
            orderModel.setPointnum(goodsModel.getPointmoney());
            orderModel.setTotal(total.longValue());
            orderModel.setPrice(BigDecimal.valueOf(price));
            orderModel.setAddressid(addressid.longValue());
            orderModel.setAddress(address);
            orderModel.setBuyname(linkman);
            orderModel.setLinkmobile(linkmobile);

            orderModel.setCreatetime(Timestamp.valueOf(nowtime));
            orderModel.setStatus(0);
            //判断商品类型
            if(goodsModel.getCategory()==2){
                IntegralShopVouchercardModel vouchercardModel=vouchercardRepository.findByGoodsNoAndStatusOrderByIdAsc(goodsModel.getGoodssn(),0);
                orderModel.setCardsn(vouchercardModel.getCardNo());
            }
            orderModel.setMemuid(ssoid);
            //根据来源订单编码
            ordersn=cf==1?"TA".concat(ordersn):"TN".concat(ordersn);
            orderModel.setOrdersn(ordersn);

            if (integralShopOrderRepository.saveAndFlush(orderModel).getId()!=0){
                //扣除用户积分
                ResponseData<String> ret=pointsService.pushPoints(token,ssoid, price.intValue(),"商品积分兑换","积分商城","-","1",0);
                if(ret.getCode()!=1){
                    throw new NullPointerException("用户积分扣除失败:"+ret.getMsgZ());
                }
            }

        }
        return 1;
    }

    @Override
    public Integer finishOrder() {
        String nowtime=TimeUtils.getCurrentTime();
        Long finishtime=TimeUtils.dateToTimestamp(nowtime);
        List<IntegralShopOrderModel> orderModelList=integralShopOrderRepository.findAllByStatusAndReceovertimeLessThanEqual(2,finishtime);
        int cnt=0;
        if(orderModelList!=null) {
            for (IntegralShopOrderModel shopOrderModel : orderModelList) {
                int ret = integralShopOrderRepository.updateStatus(shopOrderModel.getId(), ShopConstants.ORDER_STATUS_SUCCESSED, nowtime);
                if (ret > 0) cnt++;
            }
        }
        return cnt;
    }


    private IntegralShopOrderFo shopOrderVotoOthervo(IntegralShopOrderVo shopOrderVo){
        IntegralShopOrderFo item=new IntegralShopOrderFo();
        item.setId(shopOrderVo.getId());
        item.setGoodsname(shopOrderVo.getGoodsname());
        item.setGoodsimage(shopOrderVo.getGoodsimage());
        item.setPointmoney(shopOrderVo.getPointprice());
        item.setTotal(shopOrderVo.getTotal());
        item.setBuyname(shopOrderVo.getBuyname());
        item.setLinkmobile(shopOrderVo.getLinkmobile());
        item.setAddress(shopOrderVo.getAddress());
        item.setOrdersn(shopOrderVo.getOrdersn());
        item.setExpresssn(shopOrderVo.getExpresssn());
        item.setCardsn(shopOrderVo.getCardsn());
        item.setCreatetime(shopOrderVo.getCreatetime());
        item.setFinishtime(shopOrderVo.getUpdatetime());
        item.setPointnum(shopOrderVo.getPointnum());
        item.setStatus(shopOrderVo.getStatus());
        item.setComefrom(shopOrderVo.getComefrom());
        return item;
    }

    /**
     * 获取列表查询
     * @param condition
     * @param pageable
     * @return
     */
    public Page<IntegralShopOrderVo> getListsrc(JSONObject condition, Pageable pageable) {
        SqlBuilder sb = new SqlBuilder("SELECT COUNT(*) FROM integral_shop_order a " +
                "LEFT JOIN integral_shop_goods goods ON goods.id=a.goodsid " +
                "WHERE 1=1 ");

        String kw=condition.getString("keyword");
        Integer stt=condition.getInteger(STATUS);
        String startDay=condition.getString("startday");
        String endDay=condition.getString("endday");
        String ssoid=condition.getString("ssoid");

        if (stt != null) {
            sb.appendStatement(" and a.status = ").appendParamValue(stt);
        }
        if (StringUtils.hasText(ssoid)) {
            sb.appendStatement(" and a.memuid = ").appendParamValue(ssoid);
        }

        if (StringUtils.hasText(startDay)) {
            Date start = TimeUtils.parseDate(startDay, "yyyy-MM-dd");
            if (start != null) {
                sb.appendStatement(" and a.createtime >= ").appendParamValue(start);
            }
        }

        if (StringUtils.hasText(endDay)) {
            Date end = TimeUtils.parseDate(endDay, "yyyy-MM-dd");
            if (end != null) {
                sb.appendStatement(" and a.createtime < ").appendParamValue(TimeUtils.addDay(end, 1));
            }
        }

        String sql = sb.toStatement();
        if(StringUtils.hasText(kw)){
            sql +=" and (a.ordersn like '%"+kw+"%'or a.buyname like '%"+kw+"%' or a.linkmobile like '%"+kw+"%' or a.expresssn like '%"+kw+"%')";
        }
        Query countQ = em.createNativeQuery(sql);
        Object[] params = sb.toParams();
        int p = 1;
        for (Object param : params) {
            countQ.setParameter(p++, param);
        }
        List countList = countQ.getResultList();

        sql = sql.replace("COUNT(*)", "a.id,a.ordersn,a.ordersn2,a.goodsid,a.pointprice,a.cardsn,a.total," +
                "a.price,a.status,a.createtime,a.paytime,a.buyname,a.linkmobile,a.address,a.pointnum,a.expresssn,a.expresstime,a.comefrom," +
                "goods.goods_name,goods.image,a.updatetime");
        String orderbystr="";

        sql += orderbystr+" LIMIT " + pageable.getOffset() + ", " + pageable.getPageSize();
        logger.info("SQL:{}",sql);
        Query rowQ = em.createNativeQuery(sql);
        p = 1;
        for (Object param : params) {
            rowQ.setParameter(p++, param);
        }
        List rowList = rowQ.getResultList();

        List<IntegralShopOrderVo> content = new ArrayList<>();
        IntegralShopOrderVo item;
        for (Object o : rowList) {
            Object[] oArr = (Object[]) o;
            item = new IntegralShopOrderVo();
            item.setId(((Number) oArr[0]).longValue());
            item.setOrdersn((String) oArr[1]);
            item.setOrdersn2((String) oArr[2]);
            item.setGoodsid(oArr[3] != null ?((Number) oArr[3]).longValue():Long.valueOf(0));
            item.setPointprice((BigDecimal) oArr[4]); //商品积分
            item.setCardsn((String) oArr[5]);
            item.setTotal(oArr[6] != null ?((Number) oArr[6]).longValue():Long.valueOf(0)); //商品数量
            item.setPrice(oArr[7] != null ? (BigDecimal) oArr[7] : new BigDecimal(0));
            item.setStatus(oArr[8] != null ?((Number) oArr[8]).intValue():Integer.valueOf(0));

            Timestamp ctime= (Timestamp) oArr[9];
            if(ctime!=null) {
                item.setCreatetime(TimeUtils.getLongDateStr(ctime)); //订单完成时完成时间
            }
            Timestamp ptime= (Timestamp) oArr[10];
            if(ptime!=null) {
                item.setPaytime(TimeUtils.getLongDateStr(ptime)); //订单完成时完成时间
            }
            item.setBuyname((String) oArr[11]);
            item.setLinkmobile((String) oArr[12]);
            item.setAddress((String) oArr[13]);
            item.setPointnum(oArr[14] != null ?((Number) oArr[14]).longValue():Long.valueOf(0)); //订单总使用积分=商品积分*商品数量
            item.setExpresssn((String) oArr[15]);
            Timestamp etime= (Timestamp) oArr[16];
            if(etime!=null) {
                item.setExpresstime(TimeUtils.getLongDateStr(etime)); //订单完成时完成时间
            }
            item.setComefrom(oArr[17] != null ?((Number) oArr[17]).intValue():Integer.valueOf(0));
            item.setGoodsname((String) oArr[18]);
            item.setGoodsimage((String) oArr[19]);

            Timestamp utime= (Timestamp) oArr[20];
            if(utime!=null) {
                item.setUpdatetime(TimeUtils.getLongDateStr(utime)); //订单完成时完成时间
            }


            content.add(item);
        }

        return new PageImpl<>(content, pageable, ((Number) countList.get(0)).intValue());
    }


}

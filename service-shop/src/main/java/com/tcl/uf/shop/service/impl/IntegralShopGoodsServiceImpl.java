package com.tcl.uf.shop.service.impl;

import com.tcl.commondb.shop.model.IntegralShopGoodsModel;
import com.tcl.commondb.shop.model.IntegralShopImagesModel;
import com.tcl.commondb.shop.model.enums.GoodsBrandEnum;
import com.tcl.commondb.shop.repository.IntegralShopGoodsRepository;
import com.tcl.commondb.shop.repository.IntegralShopImagesRepository;
import com.tcl.commonservice.service.PointsService;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.SqlBuilder;
import com.tcl.uf.common.base.util.StringsUtil;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.shop.consts.ShopConstants;
import com.tcl.uf.shop.dto.IntegralShopGoodsDto;
import com.tcl.uf.shop.dto.IntegralShopImagesDto;
import com.tcl.uf.shop.service.IntegralShopGoodsService;
import com.tcl.uf.shop.vo.IntegralShopGoodsFo;
import com.tcl.uf.shop.vo.IntegralShopGoodsVo;
import com.tcl.uf.shop.vo.IntegralShopOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Transactional(rollbackOn = Exception.class)
public class IntegralShopGoodsServiceImpl implements IntegralShopGoodsService {

	@Autowired
	private IntegralShopGoodsRepository integralShopGoodsRepository;
    @Autowired
    private IntegralShopImagesRepository integralShopImagesRepository;
    @Autowired
    private PointsService pointsService;

	@PersistenceContext
	private EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(IntegralShopGoodsServiceImpl.class);
	@Override
	public void createIntegralShopGoods(IntegralShopGoodsDto paramBody){
        // 查询数据最大数据编号
        IntegralShopGoodsModel goodsModel=integralShopGoodsRepository.getLastrow();
        long maxid=Long.valueOf(0);
        if(goodsModel!=null){
            maxid=goodsModel.getId();
        }
        String goodsno=paramBody.getGoodssn();
        List<IntegralShopImagesDto> shopImagesDtoList = paramBody.getShopImagesDtoList();
        if(shopImagesDtoList!=null) {
            for (IntegralShopImagesDto image : shopImagesDtoList) {
                IntegralShopImagesModel shopImagesModel = new IntegralShopImagesModel();
                shopImagesModel.setComefrom(ShopConstants.COME_FROM_GOODS);
                shopImagesModel.setFileType(1);
                shopImagesModel.setFileUrl(image.getFileUrl());
                shopImagesModel.setLinkflag(goodsno);
                shopImagesModel.setStatus(1);
                integralShopImagesRepository.saveAndFlush(shopImagesModel);
            }
        }

		IntegralShopGoodsModel domain=new IntegralShopGoodsModel();
		synchronized (IntegralShopGoodsServiceImpl.class) {
			BeanUtils.copyProperties(paramBody,domain);
			if(paramBody.getBrandId()==null)
			    domain.setBrandId(Long.valueOf(1)); //默认电视

			domain.setWithholdnum(Long.valueOf(0));
			domain.setStatus(0);
			domain.setCreatedBy(1);
			domain.setCreatedTime(TimeUtils.getTimestamp());
			domain.setUptime(TimeUtils.getTimestamp());
			integralShopGoodsRepository.saveAndFlush(domain);
			
		}

	}

	@Override
	public String updateIntegralShopGoods(IntegralShopGoodsDto paramBody){
		Long id = paramBody.getId();
		IntegralShopGoodsModel domain = integralShopGoodsRepository.getOne(id);
        List<IntegralShopImagesDto> shopImagesDtoList = paramBody.getShopImagesDtoList();
        if(shopImagesDtoList!=null) {
            for (IntegralShopImagesDto image : shopImagesDtoList) {
                if(image.getId()>0){
                    continue;
                }
                IntegralShopImagesModel shopImagesModel = new IntegralShopImagesModel();
                shopImagesModel.setComefrom("goods");
                shopImagesModel.setFileType(1);
                shopImagesModel.setFileUrl(image.getFileUrl());
                shopImagesModel.setLinkflag(paramBody.getGoodssn());
                shopImagesModel.setStatus(1);
                integralShopImagesRepository.saveAndFlush(shopImagesModel);
            }
        }
        Timestamp createtime=domain.getCreatedTime();
		BeanUtils.copyProperties(paramBody,domain);
        if(domain.getStatus()==0){
            domain.setUptime(TimeUtils.getTimestamp());
        }
        if(domain.getStatus()==1){
            domain.setDowntime(TimeUtils.getTimestamp());
        }
        if(domain.getStatus()==-1){
            domain.setModifiedTime(TimeUtils.getTimestamp());
        }
        domain.setCreatedBy(1);
        domain.setModifiedBy(1);
        domain.setModifiedTime(TimeUtils.getTimestamp());
        domain.setCreatedTime(createtime);
		integralShopGoodsRepository.saveAndFlush(domain);
		return "save success";

	}

	@Override
	public IntegralShopGoodsVo findOne(Long id){
		IntegralShopGoodsModel domain = integralShopGoodsRepository.getOne(id);
		IntegralShopGoodsVo viewobject=new IntegralShopGoodsVo();
		BeanUtils.copyProperties(domain,viewobject);
		if(domain.getBrandId()==null){
            throw new JMException("商品主类Brandid不能为空!");
        }
		viewobject.setBrandName(GoodsBrandEnum.parse(Long.toString(domain.getBrandId())).getName());

        Timestamp mtime= domain.getModifiedTime();
        if(mtime!=null) {
            viewobject.setModifiedTime(TimeUtils.getLongDateStr(mtime));
        }
        Timestamp ctime= domain.getCreatedTime();
        if(ctime!=null) {
            viewobject.setCreatedTime(TimeUtils.getLongDateStr(ctime));
        }
        Timestamp uptime= domain.getUptime();
        if(uptime!=null) {
            viewobject.setUptime(TimeUtils.getLongDateStr(uptime));
        }
        Timestamp downtime= domain.getDowntime();
        if(downtime!=null) {
            viewobject.setDowntime(TimeUtils.getLongDateStr(downtime));
        }

        String policyImgStr=domain.getSrvpolicy();
        List<String> policyimgsList =new ArrayList<String>();
        if(!StringUtils.isEmpty(policyImgStr)){
            List<String> slist = Arrays.asList(policyImgStr.split(","));
            for (String simgurl : slist){
                policyimgsList.add(simgurl);
            }
        }
        viewobject.setSrvpolicyimgs(policyimgsList);

        String proparamImgStr=domain.getProparam();
        List<String> proparamimgsList =new ArrayList<String>();
        if(!StringUtils.isEmpty(proparamImgStr)){
            List<String> plist = Arrays.asList(proparamImgStr.split(","));
            for (String pimgurl : plist){
                proparamimgsList.add(pimgurl);
            }
        }
        viewobject.setProparamimgs(proparamimgsList);

        String descImgStr=domain.getDescription();
        List<String> imgsList =new ArrayList<String>();
        if(!StringUtils.isEmpty(descImgStr)){
            List<String> list = Arrays.asList(descImgStr.split(","));
            for (String imgurl : list){
                imgsList.add(imgurl);
            }
        }
        viewobject.setDescimgs(imgsList);

        List<IntegralShopImagesModel> shopImagesModelList= integralShopImagesRepository.findByComefromAndLinkflag("goods",domain.getGoodssn());
        List<IntegralShopImagesDto> shopImagesDtoList=new ArrayList<>();
        for (IntegralShopImagesModel shopImagesModel:shopImagesModelList){
            IntegralShopImagesDto shopImagesDto=new IntegralShopImagesDto();
            shopImagesDto.setId(shopImagesModel.getId());
            shopImagesDto.setFileUrl(shopImagesModel.getFileUrl());
            shopImagesDtoList.add(shopImagesDto);
        }

        viewobject.setShopImagesDtoList(shopImagesDtoList);
		return viewobject;

	}

	@Override
	public Integer updateStatus(Long[] ids, Integer stt) {
        String nowtime=TimeUtils.getCurrentTime();
		Integer count = 0;
		IntegralShopGoodsModel modelobject=null;
		if (ids != null && ids.length > 0 && stt != null) {
			for (Long id : ids) {
				int ret=integralShopGoodsRepository.updateStatus(id,stt,nowtime);
				if(ret>0){
					count++;
				}
			}
		}
		return count;
    }

    @Override
    public Integer updateOrder(Long id, Integer sort) {
        int ret=integralShopGoodsRepository.updateOrder(id,sort);
        return ret;
    }

    @Override
	public ListWithPage<IntegralShopGoodsVo> findListbykw(String keyword, Long bid, Integer status, Pageable pageable) {
		Long sumTotal;
		List<IntegralShopGoodsVo> records = new ArrayList<>();
		Page<IntegralShopGoodsModel> result=null;


        if(status==null && StringUtils.isEmpty(keyword)&& bid==null){
            result = integralShopGoodsRepository.getListAll(pageable);
        }
        else if(bid!=null&&status==null&& StringUtils.isEmpty(keyword)){
            result = integralShopGoodsRepository.getBybidAll(bid,pageable);
        }
		else if(status!=null && !StringUtils.isEmpty(keyword)) {
            result = integralShopGoodsRepository.getBysttLikekw(status,keyword,pageable);
        }
		else if(status==null && !StringUtils.isEmpty(keyword)) {
            result = integralShopGoodsRepository.getByLikekw(keyword, pageable);
        }
		else if(status!=null && StringUtils.isEmpty(keyword)) {
            result = integralShopGoodsRepository.getBystt(status, pageable);
        }

		if (result != null) {
			sumTotal = result.getTotalElements();
			for (IntegralShopGoodsModel entity : result.getContent()) {
				IntegralShopGoodsVo viewobject=new IntegralShopGoodsVo();
				BeanUtils.copyProperties(entity,viewobject);
				viewobject.setBrandName(GoodsBrandEnum.parse(Long.toString(entity.getBrandId())).getName());

                Timestamp mtime= entity.getModifiedTime();
                if(mtime!=null) {
                    viewobject.setModifiedTime(TimeUtils.getLongDateStr(mtime));
                }
                Timestamp ctime= entity.getCreatedTime();
                if(ctime!=null) {
                    viewobject.setCreatedTime(TimeUtils.getLongDateStr(ctime));
                }
                Timestamp uptime= entity.getUptime();
                if(uptime!=null) {
                    viewobject.setUptime(TimeUtils.getLongDateStr(uptime));
                }
                Timestamp downtime= entity.getDowntime();
                if(downtime!=null) {
                    viewobject.setDowntime(TimeUtils.getLongDateStr(downtime));
                }

                String policyImgStr=entity.getSrvpolicy();
                List<String> policyimgsList =new ArrayList<String>();
                if(!StringUtils.isEmpty(policyImgStr)){
                    List<String> slist = Arrays.asList(policyImgStr.split(","));
                    for (String simgurl : slist){
                        policyimgsList.add(simgurl);
                    }
                }
                viewobject.setSrvpolicyimgs(policyimgsList);

                String proparamImgStr=entity.getProparam();
                List<String> proparamimgsList =new ArrayList<String>();
                if(!StringUtils.isEmpty(proparamImgStr)){
                    List<String> plist = Arrays.asList(proparamImgStr.split(","));
                    for (String pimgurl : plist){
                        proparamimgsList.add(pimgurl);
                    }
                }
                viewobject.setProparamimgs(proparamimgsList);

                String descImgStr=entity.getDescription();
                List<String> imgsList =new ArrayList<String>();
                if(!StringUtils.isEmpty(descImgStr)){
                    List<String> list = Arrays.asList(descImgStr.split(","));
                    for (String imgurl : list){
                        imgsList.add(imgurl);
                    }
                }
                viewobject.setDescimgs(imgsList);


				records.add(viewobject);
			}
		} else {
			sumTotal = Long.getLong("0");
			records = new ArrayList<>();
		}
		return PageUtils.formatData(records, pageable, sumTotal);
	}

    @Override
    public ListWithPage<IntegralShopGoodsFo> findList(String condition, String orderstr, String token, Pageable pageable){
        ResponseData<Integer> pointinfo=pointsService.getPointsBySSOID(token);
	    Integer mypoint=pointinfo.getData();//我的积分
        Page<IntegralShopGoodsFo> shopGoodsFos=this.getgoodssrc(condition,orderstr,mypoint,pageable);
        List<IntegralShopGoodsFo> shopGoodsFoList=shopGoodsFos.getContent();
        Long total=shopGoodsFos.getTotalElements();
        return PageUtils.formatData(shopGoodsFoList, pageable, total);
    }

    @Override
    public Integer delgoodsimg(Long id){
        try {
            integralShopImagesRepository.deleteById(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 前端获取商品列表查询
     * @param condition
     * @param orderstr
     * @param mypoint
     * @param pageable
     * @return
     */
	public Page<IntegralShopGoodsFo> getgoodssrc(String condition, String orderstr, Integer mypoint, Pageable pageable) {
		SqlBuilder sb = new SqlBuilder("SELECT COUNT(*) FROM integral_shop_goods goods WHERE status=0");
		if (StringUtils.hasText(condition) && condition.equals("exchange")){
			sb.appendStatement(" and goods.pointmoney <= ").appendParamValue(mypoint);
		}

		String sql = sb.toStatement();

		Query countQ = em.createNativeQuery(sql);
		Object[] params = sb.toParams();
		int p = 1;
		for (Object param : params) {
			countQ.setParameter(p++, param);
		}
		List countList = countQ.getResultList();


		sql = sql.replace("COUNT(*)", "goods.id,goods.category,goods.goods_name,goods.image,goods.price,goods.pointmoney");
		String orderbystr="";
		if (StringUtils.hasText(condition)) {
			if(condition.equals("hot")){
				orderbystr=" ORDER BY favorite_count DESC";
			}
			else if(condition.equals("new")){
				orderbystr=" ORDER BY id DESC";
			}
			else if(condition.equals("price")){
				orderbystr=" ORDER BY pointmoney " +orderstr;
			}

		}
		sql += orderbystr+" LIMIT " + pageable.getOffset() + ", " + pageable.getPageSize();
        logger.info("mySQL:{}",sql);
		Query rowQ = em.createNativeQuery(sql);
		p = 1;
		for (Object param : params) {
			rowQ.setParameter(p++, param);
		}
		List rowList = rowQ.getResultList();

		List<IntegralShopGoodsFo> content = new ArrayList<>();
        IntegralShopGoodsFo item;
		for (Object o : rowList) {
			Object[] oArr = (Object[]) o;
			item = new IntegralShopGoodsFo();
			item.setId(oArr[0] != null ?((Number) oArr[0]).longValue():Long.valueOf(0));
			item.setCategory(oArr[1] != null ?((Number) oArr[1]).longValue():Long.valueOf(0));
			item.setGoodsName((String) oArr[2]);
			item.setImage((String) oArr[3]);
			item.setPrice(oArr[4] != null ? (BigDecimal) oArr[4] : new BigDecimal(0));
			item.setPointmoney(oArr[5] != null ?((Number) oArr[5]).longValue():Long.valueOf(0));
			content.add(item);
		}

		return new PageImpl<>(content, pageable, ((Number) countList.get(0)).intValue());
	}

}

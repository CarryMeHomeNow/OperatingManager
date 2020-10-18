package com.tcl.uf.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.shop.model.IntegralShopGoodsModel;
import com.tcl.commondb.shop.model.IntegralShopVouchercardModel;
import com.tcl.commondb.shop.repository.IntegralShopGoodsRepository;
import com.tcl.commondb.shop.repository.IntegralShopVouchercardRepository;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.shop.service.IntegralShopVouchercardService;
import com.tcl.uf.shop.vo.IntegralShopVouchercardVo;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(rollbackOn = Exception.class)
public class IntegralShopVouchercardServiceImpl implements IntegralShopVouchercardService {

	@Autowired
	private IntegralShopVouchercardRepository integralShopVouchercardRepository;
	@Autowired
	private IntegralShopGoodsRepository integralShopGoodsRepository;
	@Override
	public IntegralShopVouchercardVo findOne(Long id){
		IntegralShopVouchercardModel domain = integralShopVouchercardRepository.getOne(id);
		if (domain == null) {
			throw new NullPointerException();
		}
		IntegralShopVouchercardVo viewobject=new IntegralShopVouchercardVo();
		BeanUtils.copyProperties(domain,viewobject);
		return viewobject;

	}

	@Override
	public IntegralShopVouchercardModel updaterow(Long id, Integer stt) {
		int ret=integralShopVouchercardRepository.updateStatus(id,stt);
		IntegralShopVouchercardModel cardlistModel=new IntegralShopVouchercardModel();
		if(ret>0){
			cardlistModel=integralShopVouchercardRepository.getOne(id);
		}
		return cardlistModel;
	}

	@Override
	public ListWithPage<IntegralShopVouchercardVo> findList(String gno,Pageable pageable){

		Long sumTotal;
		List<IntegralShopVouchercardVo> records = new ArrayList<>();
		Page<IntegralShopVouchercardModel> result;
		result = integralShopVouchercardRepository.getListAll(gno,pageable);
		if (result != null) {
			sumTotal = result.getTotalElements();
			for (IntegralShopVouchercardModel entity : result.getContent()) {
				IntegralShopVouchercardVo viewobject=new IntegralShopVouchercardVo();
				BeanUtils.copyProperties(entity,viewobject);
				records.add(viewobject);
			}
		} else {
			sumTotal = Long.getLong("0");
			records = new ArrayList<>();
		}
		return PageUtils.formatData(records, pageable, sumTotal);


	}

	@Override
	public ResponseData<Integer> uploadVouchercard(MultipartFile file, String gno) {
		ResponseData<Integer> resp = new ResponseData<Integer>();
		if(file == null) {
			return new ResponseData<>(-1, "导入EXCEL文件不能为空!", "fail");
		}

		String name = file.getOriginalFilename();
		if(!name.endsWith(".xls") && !name.endsWith(".xlsx")) {
			return new ResponseData<>(-1, "请导入EXCEL文件", "fail");
		}
		Workbook wb = null;
		InputStream in = null;
		try {
			try {
				in = file.getInputStream();
				if (name.endsWith(".xls")) {
					wb = new HSSFWorkbook(in);
				} else {
					wb = new XSSFWorkbook(in);
				}
			} catch (Exception e) {
				return new ResponseData<>(-1, "请选择正确的EXCEL文件导入", "fail");
			}
            Integer cnt=this.writeExcelfile(wb,gno);
			//清空一下卡号和卡密都为空的记录
			integralShopVouchercardRepository.deleteRowBynull(gno);
			resp.setData(cnt);
		} finally {
			try {
				if (wb != null) {
					wb.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resp;
	}
    private Integer writeExcelfile(Workbook wb,String gno){
        Sheet sheet = wb.getSheetAt(0);
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        int count = 0;
        String cardno;
        String cardpwd;
        Row row;
        for (int rowNo = 1; rowNo < physicalNumberOfRows; rowNo++) {
            row = sheet.getRow(rowNo);
            cardno = getString(row.getCell(0), "0");
            cardno = cardno.trim();
            cardpwd=getString(row.getCell(1), "0");
            cardpwd = cardpwd.trim();
            if(StringUtils.isNotBlank(cardno)&&StringUtils.isNotBlank(cardpwd)){
                IntegralShopVouchercardModel cardlistModel=integralShopVouchercardRepository.findByCardNoAndAndCardPwdAndGoodsNo(cardno,cardpwd,gno);
                if(cardlistModel!=null&&cardlistModel.getId() > 0) {
                    continue;
                }
            }
            if(StringUtils.isBlank(cardno)&&StringUtils.isNotBlank(cardpwd)){
                IntegralShopVouchercardModel cardlistModel=integralShopVouchercardRepository.findByCardPwdAndGoodsNo(cardpwd,gno);
                if(cardlistModel!=null&&cardlistModel.getId() > 0) {
                    continue;
                }
            }
            IntegralShopVouchercardModel cardlistModel=new IntegralShopVouchercardModel();
            cardlistModel.setCardNo(cardno);
            cardlistModel.setCardPwd(cardpwd);
            cardlistModel.setStartTime(getString(row.getCell(2), null));
            cardlistModel.setEndTime(getString(row.getCell(3), null));
            cardlistModel.setCteateTime(TimeUtils.getTimestamp());
            cardlistModel.setGoodsNo(gno);
            cardlistModel.setStatus(0);

            integralShopVouchercardRepository.save(cardlistModel);
            count++;
        }
        return count;
    }
	private String getString(Cell cell, String numFormat) {
		if (cell == null) {
			return "";
		}
		int cellType = cell.getCellType();
		if (Cell.CELL_TYPE_NUMERIC == cellType) {
			if (StringUtils.isNotBlank(numFormat)) {
				NumberFormat nf = new DecimalFormat(numFormat);
				return nf.format(cell.getNumericCellValue());
			} else {
				return String.valueOf(cell.getNumericCellValue());
			}
		} else {
			return cell.getStringCellValue();
		}
	}

	@Override
	public JSONObject getcardstatis(String gno) {
		JSONObject jo=new JSONObject();
		Integer total=0;
		Integer usable=0;   //可用
		Integer unusable=0; //已使用
		Integer expire=0;
		String prizename="";
		IntegralShopGoodsModel shopGoodsModel=integralShopGoodsRepository.findBygoodssn(gno);
		if(shopGoodsModel!=null){
			prizename=shopGoodsModel.getGoodssn();
		}
		List<IntegralShopVouchercardModel> shopVouchercardModelList=integralShopVouchercardRepository.findAllByGoodsNo(gno);
		for(IntegralShopVouchercardModel vouchercardModel:shopVouchercardModelList){
			if(vouchercardModel.getStatus()==1) unusable++;
			if(vouchercardModel.getStatus()==0) usable++;

			if(vouchercardModel.getStatus()==0 && TimeUtils.compareDateAfter(vouchercardModel.getEndTime(),TimeUtils.getCurrentTime())){
				expire++;
			}
			total++;
		}

		jo.put("total",total);
		jo.put("usable",usable);
		jo.put("unusable",unusable);
		jo.put("expire",expire);
		jo.put("prizename",prizename);

		return jo;
	}


}

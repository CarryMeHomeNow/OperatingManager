package com.tcl.uf.member.service.impl;

import com.tcl.commondb.member.model.AfterSaleConfigureModel;
import com.tcl.commondb.member.repository.AfterSaleConfigureModelRepository;
import com.tcl.uf.member.service.AfterSaleService;
import com.tcl.uf.member.vo.AfterSaleConfigureVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service("afterSaleService")
public class AfterSaleServiceImpl implements AfterSaleService {

    @Autowired
    private AfterSaleConfigureModelRepository afterSaleConfigure;

    @Override
    public List<AfterSaleConfigureVo> findAfterSaleConfigureList() {
        List<AfterSaleConfigureVo> voList = null;
        List<AfterSaleConfigureModel> modelList = afterSaleConfigure.findByOrderByPositionAsc();
        if (!CollectionUtils.isEmpty(modelList)) {
            voList = new ArrayList<AfterSaleConfigureVo>();
            for (AfterSaleConfigureModel afterSaleConfigureModel : modelList) {
                AfterSaleConfigureVo afterSaleConfigureVo = new AfterSaleConfigureVo();
                BeanUtils.copyProperties(afterSaleConfigureModel, afterSaleConfigureVo);
                voList.add(afterSaleConfigureVo);
            }
        }
        return voList;
    }
}

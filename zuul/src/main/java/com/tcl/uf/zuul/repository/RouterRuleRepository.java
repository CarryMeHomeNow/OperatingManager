package com.tcl.uf.zuul.repository;

import com.tcl.uf.zuul.model.RouterRuleV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author chiwm@kuyumall.com
 * @InterfaceName: RouterRuleRepository
 * @Description:
 * @date 2018/1/30 下午8:49
 */

public interface RouterRuleRepository extends JpaRepository<RouterRuleV, Long> {

    /**
     * 通过serviceId获取数据
     * @param serviceId
     * @return
     */
    RouterRuleV getFirstByServiceIdEquals(String serviceId);
    
    List<RouterRuleV> findAllByOrderByIdDesc();
}

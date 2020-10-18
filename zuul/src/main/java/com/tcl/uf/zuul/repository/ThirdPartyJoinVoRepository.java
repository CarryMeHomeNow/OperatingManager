package com.tcl.uf.zuul.repository;

import com.tcl.uf.zuul.model.ThirdPartyJoinVo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chiwm@kuyumall.com
 * @InterfaceName: RouterRuleRepository
 * @Description:
 * @date 2018/1/30 下午8:49
 */

public interface ThirdPartyJoinVoRepository extends JpaRepository<ThirdPartyJoinVo, Long> {

    /**
     * 通过serviceId获取数据
     *
     * @return
     */
	ThirdPartyJoinVo getFirstByAppidEquals(String appid);
}

package com.tcl.commondb.advert.repository;

import com.tcl.commondb.advert.model.AdvertEmailSendRecordModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertEmailSendRecordRepository
 * @Description:
 * @date 2019/12/11 15:03
 */
public interface AdvertEmailSendRecordRepository extends JpaRepository<AdvertEmailSendRecordModel, Long> {

    /**
     * 获取最后一次向该邮箱发送邮件记录
     * @param email 邮件地址
     * @return
     * @date 2019/12/11 15:05
     */
    AdvertEmailSendRecordModel findFirstByEmailEqualsOrderByCreateTimeDesc(String email);
}

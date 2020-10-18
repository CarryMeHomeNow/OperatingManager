package com.tcl.uf.advert.service.impl;

import com.tcl.commondb.advert.model.AdvertEmailSendRecordModel;
import com.tcl.commondb.advert.repository.AdvertEmailSendRecordRepository;
import com.tcl.uf.advert.exception.JMailException;
import com.tcl.uf.advert.service.AdvertMailService;
import com.tcl.uf.common.base.util.StringsUtil;
import com.tcl.uf.common.base.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertMailServiceImpl
 * @Description:
 * @date 2020/8/19 20:39
 */
@Service("AdvertMailService")
public class AdvertMailServiceImpl implements AdvertMailService {

    private static final Logger log = LoggerFactory.getLogger(AdvertMailServiceImpl.class);
    private static final String FINISH = "FINISH";
    private static final String FAIL = "FAIL";

    @Value("${send.account.username}")
    //使用@Value注入application.properties中指定的用户名
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    AdvertEmailSendRecordRepository advertEmailSendRecordRepository;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        if (!StringsUtil.isEmailAddress(to)) {
            throw new JMailException("邮件格式不正确");
        }
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);//收信人
            message.setSubject(subject);//主题
            message.setText(content);//内容
            message.setFrom(from);//发信人
            mailSender.send(message);
            saveSendRecord(to, subject, content, FINISH);
            log.info("发送普通邮件完成：{},{}", to, subject);
        } catch (Exception e) {
            saveSendRecord(to, subject, content, FAIL);
            log.error("发送普通邮件失败：", e);
            throw new JMailException(e.getMessage());
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        if (!StringsUtil.isEmailAddress(to)) {
            throw new JMailException("邮件格式不正确");
        }
        try {
            //使用MimeMessage，MIME协议
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            saveSendRecord(to, subject, "", FINISH);
            log.info("发送HTML邮件完成：{},{}", to, subject);
        } catch (Exception e) {
            saveSendRecord(to, subject, "", FAIL);
            log.error("发送HTML邮件失败：", e);
            throw new JMailException(e.getMessage());
        }
    }

    private void saveSendRecord(String to, String subject, String content, String result) {
        AdvertEmailSendRecordModel sendRecords = new AdvertEmailSendRecordModel();
        sendRecords.setEmail(to);
        sendRecords.setTitle(subject);
        sendRecords.setContent(content);
        sendRecords.setResults(result);
        sendRecords.setCreateTime(TimeUtils.getTimestamp());
        advertEmailSendRecordRepository.saveAndFlush(sendRecords);
    }
}

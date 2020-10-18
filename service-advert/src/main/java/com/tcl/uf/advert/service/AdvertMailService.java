package com.tcl.uf.advert.service;

public interface AdvertMailService {

    /**
     * 发送普通文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @return
     * @date 2019/12/11 12:00
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 送HTML邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容（可以包含<html>等标签）
     * @return
     * @date 2019/12/12 9:59
     */
    void sendHtmlMail(String to, String subject, String content);

}

package com.tcl.uf.advert.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author linhuimin@kuyumall.com
 * @ClassName: MailConfiguration
 * @Description:邮件服务配置
 * @date 2019/12/11 18:40
 */

@Configuration
public class MailConfiguration {
    @Value("${send.account.host}")
    private String mailSmtpHost;

    @Value("${send.account.port}")
    private Integer port;

    @Value("${send.account.username}")
    private String username;

    @Value("${send.account.password}")
    private String password;

    @Value("${send.smtp.timeout}")
    private Integer timeout;

    @Value("${send.smtp.connectionTimeout}")
    private Integer connectionTimeout;

    @Value("${send.smtp.writeTimeout}")
    private Integer writeTimeout;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(mailSmtpHost);//mail.tcl.com//smtp.163.com
        javaMailSenderImpl.setPort(25);
        javaMailSenderImpl.setUsername(username);
        javaMailSenderImpl.setPassword(password);
        Properties javaMailProperties = javaMailSenderImpl.getJavaMailProperties();
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.smtp.port", "25");
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.debug", true);
        //javaMailProperties.put("mail.smtp.auth.mechanisms", "NTLM");
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.ssl.trust", mailSmtpHost);
        ///javaMailProperties.put("mail.smtp.auth.ntlm.domain", mailSmtpHost);
        javaMailProperties.put("mail.smtp.connectiontimeout", connectionTimeout);
        javaMailProperties.put("mail.smtp.writetimeout", writeTimeout);
        javaMailProperties.put("mail.smtp.timeout", timeout);
        javaMailSenderImpl.setJavaMailProperties(javaMailProperties);
        return javaMailSenderImpl;
    }
}

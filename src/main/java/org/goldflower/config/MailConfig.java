package org.goldflower.config;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactory;

import java.io.IOException;
import java.util.Properties;

@Configuration
class MailConfig {

    @Value("${mail.smtp.username}")
    String mailUserName;
    @Value("${mail.smtp.password}")
    String mailPassword;
    @Value("${mail.smtp.host}")
    String mailHost;
    @Value("${mail.smtp.port}")
    int mailPort;
    @Value("${mail.smtp.auth}")
    String mailAuth;
    @Value("${mail.smtp.starttls.enable}")
    String mailStarttlsEnable;

    @Bean
    public VelocityEngine getVelocityEngine() throws VelocityException, IOException {
        VelocityEngineFactory factory = new VelocityEngineFactory();
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        factory.setVelocityProperties(props);
        return factory.createVelocityEngine();
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailsender = new JavaMailSenderImpl();
        mailsender.setHost(mailHost);
        mailsender.setPort(mailPort);
        mailsender.setUsername(mailUserName);
        mailsender.setPassword(mailPassword);
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", mailAuth);
        javaMailProperties.put("mail.smtp.starttls.enable", mailStarttlsEnable);
        mailsender.setJavaMailProperties(javaMailProperties);
        return mailsender;
    }

}

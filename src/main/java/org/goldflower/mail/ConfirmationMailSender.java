package org.goldflower.mail;


import org.apache.velocity.app.VelocityEngine;
import org.goldflower.payment.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class ConfirmationMailSender {

    private static final Logger logger = LoggerFactory.getLogger(ConfirmationMailSender.class);

    @Autowired
    VelocityEngine velocityEngine;
    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.from}")
    String mailFrom;
    @Value("${mail.subject}")
    private String mailSubject;


    public void sendConfirmationMail(final Payment payment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(payment.getEmail());
        message.setSubject(mailSubject);
        Map<String, Object> model = new HashMap<>();
        model.put("names", payment.getName());
        model.put("amount", payment.getAmount());
        message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/confirmation.vm", "UTF-8", model));
        mailSender.send(message);
    }

}

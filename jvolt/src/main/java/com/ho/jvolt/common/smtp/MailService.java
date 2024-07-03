package com.ho.jvolt.common.smtp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;

@Service
public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);
    private final MailSender mailSender;

    public MailService(MailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendSimpleMail(String emailAddress, String title, String body) {
        // SimpleMailMessage 단순 텍스트 기반 이메일 용.
        // MailMessage 복잡한 이메일 메시지 용. 첨부 파일, 이미지 삽입 등.

        SimpleMailMessage smsg = new SimpleMailMessage();
        smsg.setTo(emailAddress);
        smsg.setSubject(title);

        smsg.setText(body);

        try {
            this.mailSender.send(smsg);
            log.info("간편 임시 이메일 전송");
        }catch (MailException e){
            throw e;
        }

    }
}

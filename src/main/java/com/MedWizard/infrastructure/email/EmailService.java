package com.MedWizard.infrastructure.email;

import com.MedWizard.domain.notification.EmailSender;
import com.MedWizard.domain.user.User;
import com.MedWizard.infrastructure.exception.BusinnesRuleException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService implements EmailSender {

    private final JavaMailSender emailSender;
    private static final String EMAIL_ORIGEM = "medWizard@email.com";
    private static final String NOME_ENVIADOR = "Med Wizard";

    public static final String URL_SITE = "http://localhost:8080"; //"medwizard.com.br"

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    @Override
    @Async
    public void sendEmail(String emailUser, String subject, String content) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(EMAIL_ORIGEM, NOME_ENVIADOR);
            helper.setTo(emailUser);
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch(MessagingException | UnsupportedEncodingException e){
            throw new BusinnesRuleException("Erro ao enviar email");
        }

        emailSender.send(message);
    }

    @Override
    public void SendEmailVerification(User user) {
        String subject = "Aqui está seu link para verificar o email";
        String content = generateContentEmail("Olá [[name]],<br>"
                + "Por favor clique no link abaixo para verificar sua conta:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFICAR</a></h3>"
                + "Obrigado,<br>"
                + "Fórum Hub :).", user.getNomeCompleto(), URL_SITE + "/verificar-conta?codigo=" + user.getToken());

        sendEmail(user.getUsername(), subject, content);
    }

    private String generateContentEmail(String template, String name, String url){
        return template.replace("[[name]]", name).replace("[[URL]]", url);
    }


}

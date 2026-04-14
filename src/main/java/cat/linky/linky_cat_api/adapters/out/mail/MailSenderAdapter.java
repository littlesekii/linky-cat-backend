package cat.linky.linky_cat_api.adapters.out.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cat.linky.linky_cat_api.core.ports.out.mail.MailSenderPort;

@Component
public class MailSenderAdapter implements MailSenderPort {

    private final JavaMailSender mailSender;

    public MailSenderAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    @Override
    @Async
    public void sendMail(String destination, String subject, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("Linky Cat <no-reply@linky.cat>");
        mail.setTo(destination);
        mail.setSubject(subject);
        mail.setText(body);
        mailSender.send(mail);
    }
    
}

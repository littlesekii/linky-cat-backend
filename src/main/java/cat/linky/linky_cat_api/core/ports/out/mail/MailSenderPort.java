package cat.linky.linky_cat_api.core.ports.out.mail;

public interface MailSenderPort {
    public void sendMail(String destination, String subject, String body);
}

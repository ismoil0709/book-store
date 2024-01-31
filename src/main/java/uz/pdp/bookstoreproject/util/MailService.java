package uz.pdp.bookstoreproject.util;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

public class MailService {
    private static final String AUTH_EMAIL_TEMPLATE = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
              <meta charset="UTF-8">
              <meta name="viewport" content="width=device-width, initial-scale=1.0">
              <title>Email Authentication</title>
            </head>
            <body style="font-family: Arial, sans-serif;">
                        
              <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ccc;">
                <h2>Email Authentication</h2>
                <p>Hello,</p>
                <p>To authenticate your email, please click the link below:</p>
                <p><a href=\"http://localhost:8080/auth/register?token=%s\" style="padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px;">Authenticate Email</a></p>
                <p>If you didn't request this, you can ignore this email.</p>
                <p>Thank you!</p>
              </div>
                        
            </body>
            </html>
            """;

    public static void send(String sendTo) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "465"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.ssl.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "abduganiyev.ismoil001@gmail.com",
                        "ntgmnccbrvuhviov");
            }
        };

        String tokenPart = sendTo + ";" + ConfigUtils.dateTimeFormatter().format(LocalDateTime.now());
        Session session = Session.getInstance(props, auth);
        MimeMessage message = new MimeMessage(session);
        String encrypt = TokenManager.encrypt(tokenPart);
        String s = encrypt.replaceAll("[+]", "%2B");
        System.out.println("before sending => " + s);
        message.setContent(String.format(AUTH_EMAIL_TEMPLATE, s), "text/html; charset=utf-8");
        message.setSubject("Hello from your friend");
        message.setFrom(new InternetAddress(sendTo));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
        Transport.send(message);
    }
}

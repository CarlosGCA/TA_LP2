package Email;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author alfredo
 */
public class Email {
    private Properties mailServerProperties;
    private Session getMailSession;
    private MimeMessage generateMailMessage;

    public void prepareConection() {

        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
      
    }

   public  void sendEmail(String subject,String emailToSend, String toEmail ) throws AddressException, MessagingException {

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
       
        
        generateMailMessage.setSubject(subject);        
        generateMailMessage.setContent(emailToSend, "text/html");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            // Aca va el correo y contrasena , estaba con el mio , pero por obvias razones lo saque 
            transport.connect("smtp.gmail.com", "", "");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }
}



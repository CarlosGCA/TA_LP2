package Email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Date;
import com.sun.mail.smtp.*;
import AccesoData.EmailAD;
/**
 *
 * @author alfredo
 */
public class Email {
    private Properties mailServerProperties;
    private Session getMailSession;
    private MimeMessage generateMailMessage;

    public void prepareConection() {

        mailServerProperties = new Properties();
        mailServerProperties.put("mail.smtp.host","smtp.gmail.com");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
      
    }
    
    public String getEmailDB(String user){
        EmailAD emailAD = new EmailAD();
        return emailAD.returnEmail(user);
    }
    
    public String getPassword(String user ){
        EmailAD emailAD = new EmailAD();
        return emailAD.returnContrasena(user);
    }

   public  void sendEmail(String subject,String emailToSend, String toEmail ) throws AddressException, MessagingException {
       
   
        getMailSession = Session.getInstance(mailServerProperties ,null);
        getMailSession.setDebug(true);
       
        
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.setFrom(new InternetAddress("pracinfobtpucp@gmail.com"));
            generateMailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            generateMailMessage.setSubject(subject);     
            generateMailMessage.setText(emailToSend);
            // 
            generateMailMessage.setHeader("X-Mailer", "smtpsend");
            generateMailMessage.setSentDate(new Date());
            
            SMTPTransport t =(SMTPTransport)getMailSession.getTransport("smtp");             
        try{
            t.connect("smtp.gmail.com" , "pracinfobtpucp@gmail.com", "btpucp123");
            t.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        } catch (Exception ex){
            System.out.println(ex.toString());
        } finally {
            System.out.println("Response: "  + t.getLastServerResponse());
            t.close();
        }
    }
}



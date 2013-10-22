package serviceplaybook.service;



import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
 
@Service("mailService")
public class ApplicationMailer 
{
    @Autowired
    private JavaMailSender mailSender;
     
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
 
    /**
     * This method will send compose and send the message 
     * */
    public void sendMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        
        mailSender.send(message);
    }
 
    /**
     * This method will send a pre-configured message
     * */
    public void sendRegistrationMail(final String to, final String messageText) 
    {
	MimeMessagePreparator preparator = new MimeMessagePreparator() {

	     public void prepare(MimeMessage mimeMessage) throws Exception {

	        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

	        message.setTo(to);

	        message.setFrom(preConfiguredMessage.getFrom());

	        //message.setReplyTo(preConfiguredMessage.getFrom());

	        message.setSubject(preConfiguredMessage.getSubject());

	        message.setText(messageText,true);

	     }

	    };

        //SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
     
        
        mailSender.send(preparator);
    }
}
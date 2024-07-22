package ma.ensa.sihmoduleadmission.service.sendemail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailSende {
    private final JavaMailSenderImpl mailSender;
    private JavaMailSender javaMailSender;

    public EmailSende(JavaMailSender javaMailSender, JavaMailSenderImpl mailSender) {
        this.javaMailSender = javaMailSender;
        this.mailSender = mailSender;
    }

    public  void setJavaMailSender(String to , String subject , String body){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("omarelmoujahid5@gmail.com");
        simpleMailMessage.setTo("omarelmoujahid5@gmail.com");
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);
        mailSender.send(simpleMailMessage);
    }
}

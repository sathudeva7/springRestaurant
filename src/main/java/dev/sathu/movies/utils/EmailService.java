package dev.sathu.movies.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService  {

    @Autowired
    private JavaMailSender emailSender;

    public void sendToken(String email, String token) {
        System.out.println(email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("krishnadeva.sathursan.eds@gmail.com");
        message.setTo(email);
        message.setSubject("Your Token");
        message.setText("Your token is: " + token);

        emailSender.send(message);

        System.out.println("mail Success....");
    }
}

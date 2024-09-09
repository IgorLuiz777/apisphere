package br.com.fiap.apisphere.mail;

import br.com.fiap.apisphere.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    MailSender mailSender;

    public void sendWelcomeEmail(User user) {
        var email = new SimpleMailMessage();
        email.setFrom("Sphere");
        email.setTo(user.getEmail());
        email.setSubject("Welcome");
        email.setText("""
                    Olá, %s
                    
                    Obrigado por se cadastrar no Sphere.
                    Parabens por entar para a IRMANDADE você é o cara...
                """.formatted(user.getName()));
        mailSender.send(email);
    }
}

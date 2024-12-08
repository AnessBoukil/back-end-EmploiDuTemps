package com.emploi.emploiapplication.settings;

import com.emploi.emploiapplication.DTO.EnseignantDTO;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailSenderService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("$(spring.mail.username)")
    private String fromEmail;
    @Override
    public void sendAccountToProf(EnseignantDTO enseignantDTO){
        String to = enseignantDTO.getEmail();
        String subject = "Compte universitaire";
        String content = "Bonjour,<br/><br/>" +
                "Voici vos informations de connexion :<br/><br/>Nom d'utilisateur : [[username]]<br/>Mot de passe : [[password]]<br/><br/>" +
                "Vous pouvez consulter votre compte .<br/><br/>Cordialement,<br/><br/>L'équipe de support.";
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            content = content.replace("[[username]]", enseignantDTO.getUserAccountDTO().getUsername());
            content = content.replace("[[password]]", enseignantDTO.getUserAccountDTO().getPassword());
            helper.setText(content, true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

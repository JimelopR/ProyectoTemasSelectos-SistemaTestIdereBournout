/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.utils;

import com.mac.ProyectoTemasSelectos.dtos.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.springframework.mail.MailException;

/**
 *
 * @author jimena
 */

@Service
public class EmailUtil  {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailUtil(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }
    public void sendMail(EmailDTO emailModel) throws MessagingException {       
        try{           
             // Creamos el modelo de correo           
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
            helper.setTo(emailModel.getUsuario());
            helper.setSubject(emailModel.getAsunto());
                
            Context context = new Context();
            context.setVariable("mensaje", emailModel.getMensaje());
            String contentHTML = templateEngine.process("email", context);
            helper.setText(contentHTML, true);
            javaMailSender.send(message);
        }catch(MessagingException | MailException e){
            throw new RuntimeException("Error"+ "al enviar el correo: "+e.getMessage(), e);
        }
    }      
}

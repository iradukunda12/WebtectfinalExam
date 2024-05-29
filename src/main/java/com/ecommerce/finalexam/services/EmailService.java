package com.ecommerce.finalexam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendApprovalEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your order has been approved");
        message.setText("Your order has been approved. Thank you for shopping with us!");
        javaMailSender.send(message);
    }

    public void sendDenialEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your order has been denied");
        message.setText("We regret to inform you that your order has been denied. Please contact customer support for more information.");
        javaMailSender.send(message);
    }

    public void sendShippingEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your order has been shipped");
        message.setText("Your order has been shipped. It's on the way to your address. Thank you for shopping with us!");
        javaMailSender.send(message);
    }
}

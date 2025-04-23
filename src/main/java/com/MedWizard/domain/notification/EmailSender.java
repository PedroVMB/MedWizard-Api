package com.MedWizard.domain.notification;

import com.MedWizard.domain.user.User;

public interface EmailSender {
    void sendEmail(String emailUser, String subject, String content);
    void SendEmailVerification(User user);
}

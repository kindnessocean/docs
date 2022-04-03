package org.dapamoha.email.service.interf.smtp;

public interface SmtpService {
    void sendEmailWithCodeNumber(String address, Integer code);
}

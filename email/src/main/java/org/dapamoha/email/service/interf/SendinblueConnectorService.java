package org.dapamoha.email.service.interf;

public interface SendinblueConnectorService {
    void sendEmailCodeToUser(String address, Integer code);
}

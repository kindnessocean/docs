package org.dapamoha.email.service.interf.mq;


import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestKey;
import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestValue;
import org.dapamoha.shared.kafka.service.KStreamMessageHandler;

public interface EmailRequestHandlerService {
    KStreamMessageHandler<EmailRequestKey, EmailRequestValue> getHandler();
}

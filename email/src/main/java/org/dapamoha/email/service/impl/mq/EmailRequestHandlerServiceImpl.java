package org.dapamoha.email.service.impl.mq;

import lombok.extern.slf4j.Slf4j;
import org.dapamoha.email.service.interf.SendinblueConnectorService;
import org.dapamoha.email.service.interf.mq.EmailRequestHandlerService;
import org.dapamoha.email.service.interf.smtp.SmtpService;
import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestKey;
import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestValue;
import org.dapamoha.shared.kafka.service.KStreamMessageHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailRequestHandlerServiceImpl implements EmailRequestHandlerService {

    @Value("${org.dapamoha.shared.config.mq.topic.emailRequestResult}")
    private String outputTopic;

    private final SmtpService smtpService;

    private final SendinblueConnectorService sendinblueConnectorService;

    public EmailRequestHandlerServiceImpl(final SmtpService smtpService, final SendinblueConnectorService sendinblueConnectorService) {
        this.smtpService = smtpService;
        this.sendinblueConnectorService = sendinblueConnectorService;
    }

    @Override
    public KStreamMessageHandler<EmailRequestKey, EmailRequestValue> getHandler() {
        return kStream -> {
            kStream
                    .peek(((key, value) -> log.info("Received record {} {}", key, value)))
                    .peek(this::sendEmail)
                    .peek(((key, value) -> log.info("Pushing to topic {} {} {}", outputTopic, key, value)))
                    .to(outputTopic);

            return kStream;
        };
    }

    private void sendEmail(EmailRequestKey key, EmailRequestValue value){
//        smtpService.sendEmailWithCodeNumber(value.getAddress(), value.getCode());
        sendinblueConnectorService.sendEmailCodeToUser(value.getAddress(), value.getCode());
    }
}

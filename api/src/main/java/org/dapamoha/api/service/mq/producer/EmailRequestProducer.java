package org.dapamoha.api.service.mq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestKey;
import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestValue;
import org.dapamoha.shared.kafka.util.ProducerMqUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailRequestProducer {

    @Value("${org.dapamoha.shared.config.mq.topic.emailRequest}")
    private String topic;

    private final Producer<EmailRequestKey, EmailRequestValue> producer;

    public EmailRequestProducer(final Producer<EmailRequestKey, EmailRequestValue> emailRequestProducer) {
        this.producer = emailRequestProducer;
    }

    public void produceRequest(EmailRequestKey key, EmailRequestValue value){
        new ProducerMqUtil<EmailRequestKey, EmailRequestValue>().produce(producer, topic, key, value);
    }
}

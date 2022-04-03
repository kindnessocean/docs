package org.dapamoha.email.config.stream;

import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.dapamoha.shared.kafka.service.KafkaApplicationStream;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class KafkaConfig {

    private final Set<KafkaApplicationStream<?, ?>> kafkaApplicationStreams;

    public KafkaConfig(final Set<KafkaApplicationStream<?, ?>> kafkaApplicationStreams) {
        this.kafkaApplicationStreams = kafkaApplicationStreams;
    }

    @PostConstruct
    public void runStreams() throws Exception {
        log.info("Configure Application Kafka Streams");

        for (KafkaApplicationStream<?, ?> kafkaApplicationStream : kafkaApplicationStreams) {
            log.trace("Configure Kafka Streams for class '{}'", kafkaApplicationStream.getClass());
            kafkaApplicationStream.startStream();
        }
    }
}

package org.dapamoha.email.config.stream.emailRequest;

import java.util.Map;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.dapamoha.email.service.interf.mq.EmailRequestHandlerService;
import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestKey;
import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestValue;
import org.dapamoha.shared.domain.util.ConverterUtil;
import org.dapamoha.shared.kafka.serde.emailRequest.EmailRequestKeySerde;
import org.dapamoha.shared.kafka.serde.emailRequest.EmailRequestValueSerde;
import org.dapamoha.shared.kafka.service.KStreamMessageHandler;
import org.dapamoha.shared.kafka.service.KafkaApplicationStream;
import org.dapamoha.shared.kafka.util.KafkaPropertiesBuilderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

/**
 * Configuration EmailRequest Kafka Streams.
 */
@Slf4j
@Configuration
public class EmailRequestKafkaStreamsConfig {

    private static final String EMAIL_REQUEST_STREAM = "EmailRequestKafkaStream";

    @Value("${org.dapamoha.shared.config.mq.bootstrapServer}")
    private String bootstrapServer;

    @Value("${org.dapamoha.shared.config.mq.applicationId}")
    private String applicationId;

    @Value("${org.dapamoha.shared.config.mq.topic.emailRequest}")
    private String inputTopic;

    private final EmailRequestHandlerService emailRequestHandlerService;

    private final Class<EmailRequestKeySerde> keySerdeClass = EmailRequestKeySerde.class;
    private final Class<EmailRequestValueSerde> valueSerdeClass = EmailRequestValueSerde.class;

    private Map<String, Object> props;

    public EmailRequestKafkaStreamsConfig(final EmailRequestHandlerService emailRequestHandlerService) {
        this.emailRequestHandlerService = emailRequestHandlerService;
    }

    @Bean(name = EMAIL_REQUEST_STREAM)
    public StreamsBuilderFactoryBean changeDeviceStateResponseStreamsBuilder() {
        log.info("SetUp StreamsBuilderFactoryBean " + EMAIL_REQUEST_STREAM + " bean");
        props = KafkaPropertiesBuilderUtil.buildKafkaStreamProperties(
                keySerdeClass,
                valueSerdeClass,
                applicationId,
                bootstrapServer

        );

        return new StreamsBuilderFactoryBean(new KafkaStreamsConfiguration(props));
    }

    @Bean
    public KafkaApplicationStream<EmailRequestKey, EmailRequestValue> emailRequestKeyEmailRequestValueKafkaApplicationStream(){
        return new KafkaApplicationStream<EmailRequestKey, EmailRequestValue>() {

            private KafkaStreams streams;

            @Override
            public KStream<EmailRequestKey, EmailRequestValue> setKStream(final StreamsBuilder builder, final KStreamMessageHandler<EmailRequestKey, EmailRequestValue> handler) {
                KStream<EmailRequestKey, EmailRequestValue> kStream =
                        builder.stream(inputTopic);

                handler.handle(kStream);

                return kStream;
            }

            @Override
            public void configureKStream(final KStreamMessageHandler<EmailRequestKey, EmailRequestValue> handler) throws Exception {
                log.info("Configure " + EMAIL_REQUEST_STREAM + " kafka Stream");
                StreamsBuilder streamsBuilder = changeDeviceStateResponseStreamsBuilder().getObject();

                KStream<EmailRequestKey, EmailRequestValue> kStream = setKStream(streamsBuilder, handler);

                Topology topology = streamsBuilder.build();

                Properties properties = ConverterUtil.convertMapToProperties(props);

                streams = new KafkaStreams(topology, properties);
            }

            @Override
            public void startStream() throws Exception {
                configureKStream(emailRequestHandlerService.getHandler());
                log.info(EMAIL_REQUEST_STREAM + " Kafka Streams starting...");
                streams.start();
            }
        };
    }
}

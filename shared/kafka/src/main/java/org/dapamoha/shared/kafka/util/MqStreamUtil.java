package org.dapamoha.shared.kafka.util;

import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.dapamoha.shared.domain.util.ConverterUtil;
import org.dapamoha.shared.kafka.service.KStreamMessageHandler;
import org.dapamoha.shared.kafka.service.KafkaApplicationStream;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

@Slf4j
public class MqStreamUtil<K, V> {

    public KafkaApplicationStream<K,V> build(
            String inputTopic,
            String applicationId,
            String bootstrapServer,
            String streamName,
            StreamsBuilderFactoryBean entityEventStreamsBuilder,
            Class<?> keySerde,
            Class<?> valueSerde
    ){
        return new KafkaApplicationStream<K, V>() {
            KafkaStreams streams;
            @Override
            public KStream<K, V> setKStream(final StreamsBuilder builder, final KStreamMessageHandler<K,V> handler) {
                KStream<K, V> kStream =
                        builder.stream(inputTopic);

                handler.handle(kStream);

                return kStream;
            }

            @Override
            public void configureKStream(final KStreamMessageHandler<K,V> handler) throws Exception {
                log.info("Configure " + streamName + " kafka Stream");
                StreamsBuilder streamsBuilder = entityEventStreamsBuilder.getObject();

                KStream<K, V> kStream = setKStream(streamsBuilder, handler);

                Topology topology = streamsBuilder.build();

                Properties properties = ConverterUtil
                        .convertMapToProperties(
                                KafkaPropertiesBuilderUtil.buildKafkaStreamProperties(
                                        keySerde,
                                        valueSerde,
                                        applicationId,
                                        bootstrapServer)
                        );

                streams = new KafkaStreams(topology, properties);
            }

            @Override
            public void startStream() {
                log.info(streamName + " Kafka Streams starting...");
                streams.start();
            }
        };
    }
}

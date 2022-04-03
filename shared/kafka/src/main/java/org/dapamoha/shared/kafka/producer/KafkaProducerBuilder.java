package org.dapamoha.shared.kafka.producer;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.dapamoha.shared.kafka.util.KafkaPropertiesBuilderUtil;


public final class KafkaProducerBuilder<K, V> {

    public Producer<K, V> buildKafkaProducer(String bootstrapServer, String transactionalId){
        Producer<K, V> producer
                = new KafkaProducer<K, V>(
                KafkaPropertiesBuilderUtil
                        .buildBasicProducerConfig(bootstrapServer, transactionalId)
        );

        producer.initTransactions();

        return producer;
    }
}

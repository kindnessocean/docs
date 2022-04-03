package org.dapamoha.shared.kafka.util;


import java.util.concurrent.Future;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


public class ProducerMqUtil<K,V> {

    public void produce(Producer<K, V> producer, String topic, K key, V value){
        producer.beginTransaction();
        ProducerRecord<K, V> record =
                new ProducerRecord<K, V>(
                        topic,
                        key,
                        value
                );

        Future<RecordMetadata> future = producer.send(record);
        producer.flush();
        producer.commitTransaction();
    }
}

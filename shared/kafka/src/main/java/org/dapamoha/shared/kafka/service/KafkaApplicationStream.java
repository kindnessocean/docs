package org.dapamoha.shared.kafka.service;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

public interface KafkaApplicationStream<K, V> {

    KStream<K, V> setKStream(StreamsBuilder builder, KStreamMessageHandler<K, V> handler);

    void configureKStream(final KStreamMessageHandler<K,V> handler) throws Exception;

    void startStream() throws Exception;
}
package org.dapamoha.shared.kafka.service;

import org.apache.kafka.streams.kstream.KStream;

public interface KStreamMessageHandler<K, V> {
    KStream<?, ?> handle(KStream<K, V> kStream);
}

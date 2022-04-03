package org.dapamoha.shared.kafka.serde.emailRequest;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestKey;
import org.dapamoha.shared.kafka.serde.ClassDeSerializer;
import org.dapamoha.shared.kafka.serde.ClassSerializer;

public class EmailRequestKeySerde implements Serde<EmailRequestKey> {
    private final ClassDeSerializer<EmailRequestKey> deSerializer = new ClassDeSerializer<>(EmailRequestKey.class);
    private final ClassSerializer<EmailRequestKey> serializer = new ClassSerializer<>();

    @Override
    public Serializer<EmailRequestKey> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<EmailRequestKey> deserializer() {
        return deSerializer;
    }
}

package org.dapamoha.shared.kafka.serde.emailRequest;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestValue;
import org.dapamoha.shared.kafka.serde.ClassDeSerializer;
import org.dapamoha.shared.kafka.serde.ClassSerializer;

public class EmailRequestValueSerde implements Serde<EmailRequestValue> {
    private final ClassDeSerializer<EmailRequestValue> deSerializer = new ClassDeSerializer<>(EmailRequestValue.class);
    private final ClassSerializer<EmailRequestValue> serializer = new ClassSerializer<>();

    @Override
    public Serializer<EmailRequestValue> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<EmailRequestValue> deserializer() {
        return deSerializer;
    }
}

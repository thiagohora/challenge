package com.n26.challenge.infrastructure.jakcson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

public class SerializerTimestamp extends StdDeserializer<LocalDateTime> {

    protected SerializerTimestamp() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        Long time = jsonParser.readValueAs(Long.class);

        return Instant.ofEpochMilli(time).atZone(UTC).toLocalDateTime();
    }
}

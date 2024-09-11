package com.engapp.QuizService.deserializer;

import com.engapp.QuizService.utils.ReadingPart;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ReadingPartDeserializer extends JsonDeserializer<ReadingPart> {
    @Override
    public ReadingPart deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        if (node == null) {
            return null;
        }

        int key = node.asInt();

        return ReadingPart.getReadingPart(key);
    }
}

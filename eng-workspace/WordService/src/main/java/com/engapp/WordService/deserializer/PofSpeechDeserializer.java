package com.engapp.WordService.deserializer;

import com.engapp.WordService.utils.PofSpeech;
import com.engapp.WordService.utils.WordLevel;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class PofSpeechDeserializer extends JsonDeserializer<PofSpeech> {
    @Override
    public PofSpeech deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        if (node == null) {
            return null;
        }

        String text = node.textValue(); //gives word level "A1" or "B1" or other

        if (text == null) {
            return null;
        }

        return PofSpeech.getPofSpeech(text);
    }
}

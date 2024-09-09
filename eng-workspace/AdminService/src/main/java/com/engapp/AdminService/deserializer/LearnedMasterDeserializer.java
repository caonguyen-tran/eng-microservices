package com.engapp.AdminService.deserializer;

import com.engapp.AdminService.utils.LearnedMaster;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class LearnedMasterDeserializer extends JsonDeserializer<LearnedMaster> {

    @Override
    public LearnedMaster deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        if (node == null) {
            return null;
        }

        int key = node.asInt();

        return LearnedMaster.getLearnedMaster(key);
    }
}

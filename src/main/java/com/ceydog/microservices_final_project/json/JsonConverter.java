package com.ceydog.microservices_final_project.json;

import com.ceydog.microservices_final_project.City;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;

import java.io.IOException;

public class JsonConverter extends StdDeserializer<City> {
    @JsonCreator
    public JsonConverter(Class<?> vc) {
        super(vc);
    }


    public JsonConverter(JavaType valueType) {
        super(valueType);
    }

    public JsonConverter(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public City deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            double lat = (Double) ((DoubleNode) node.get("lat")).doubleValue();
            double lon = (Double) ((DoubleNode) node.get("lon")).doubleValue();

            return new City(lat,lon);
    }
}

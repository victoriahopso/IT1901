package mymovies.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import mymovies.core.AllUsers;

public class AllUsersDeserializer extends JsonDeserializer<AllUsers> {

    @Override
    public AllUsers deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // TODO Auto-generated method stub
        return null;
    }

}
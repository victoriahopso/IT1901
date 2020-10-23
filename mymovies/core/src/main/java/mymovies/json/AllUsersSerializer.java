package mymovies.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import mymovies.core.AllUsers;

public class AllUsersSerializer extends JsonSerializer<AllUsers> {

    @Override
    public void serialize(AllUsers value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // TODO Auto-generated method stub

    }
    
}
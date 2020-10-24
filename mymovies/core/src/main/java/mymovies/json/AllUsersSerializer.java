package mymovies.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import mymovies.core.AllUsers;
import mymovies.core.User;

public class AllUsersSerializer extends JsonSerializer<AllUsers> {

    @Override
    public void serialize(AllUsers allUsers, JsonGenerator gen, SerializerProvider prov) throws IOException {
        gen.writeStartObject();
        gen.writeArrayFieldStart("allUsers");
        for (User us : allUsers.getAllUsers()) {
            gen.writeObject(us);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
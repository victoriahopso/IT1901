package mymovies.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import mymovies.core.User;

public class UserSerializer extends JsonSerializer<User> {

    /**
     * format: {"name": "...", "genre": "...", "myMovies": "[...]"" }
     */
    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider prov) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("username", user.getUserName());
        gen.writeStringField("password", user.getPassword());
        //HUSK OGSÅ!! Skriv brukerens mymovies-objekt 
        gen.writeEndObject();

    }
}
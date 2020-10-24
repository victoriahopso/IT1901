package mymovies.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import mymovies.core.Film;
import mymovies.core.User;

public class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider prov) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("username", user.getUserName());
        gen.writeStringField("password", user.getPassword());
        gen.writeArrayFieldStart("myMovies");
        for (Film film : user.getMyMovies()) {
            gen.writeObject(film);
        }
        gen.writeEndArray();
        gen.writeEndObject();

    }
}
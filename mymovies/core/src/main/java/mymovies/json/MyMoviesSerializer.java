package mymovies.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import mymovies.core.Film;
import mymovies.core.MyMovies;

public class MyMoviesSerializer extends JsonSerializer<MyMovies> {

    /**
    * format: {"myMovies": [...] }
    */
    @Override
    public void serialize(MyMovies myMovies, JsonGenerator gen, SerializerProvider prov) throws IOException {
        gen.writeStartObject();
        gen.writeArrayFieldStart("myMovies");
        for (Film film : myMovies.getFilmer()){
            gen.writeObject(film);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }

}
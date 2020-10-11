package mymovies.json;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import mymovies.core.Film;

public class FilmSerializer extends JsonSerializer<Film> {
     /**
      * format: {"name": "...", "genre": "...", "rating": ... }
      */
    @Override
    public void serialize(Film film, JsonGenerator gen, SerializerProvider prov) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", film.getName());
        gen.writeStringField("genre", film.getGenre());
        gen.writeNumberField("rating", film.getRating());
        gen.writeEndObject();
    }

}
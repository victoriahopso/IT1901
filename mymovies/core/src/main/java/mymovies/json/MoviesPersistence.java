package mymovies.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import com.fasterxml.jackson.databind.ObjectMapper;

import mymovies.core.MyMovies;

public class MoviesPersistence {

    private ObjectMapper mapper;

    public MoviesPersistence() {
        mapper = new ObjectMapper();
        mapper.registerModule(new MoviesModule());
    }

    public MyMovies read(Reader reader) throws IOException {
        return mapper.readValue(reader, MyMovies.class);
    }

    public void write(MyMovies myMovies, Writer writer) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, myMovies);
    }

}
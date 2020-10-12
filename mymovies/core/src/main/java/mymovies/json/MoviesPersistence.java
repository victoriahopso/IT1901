package mymovies.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import com.fasterxml.jackson.databind.ObjectMapper;

import mymovies.core.MyMovies;

public class MoviesPersistence {

    private ObjectMapper mapper;

    /**
     * kontroller som initialiserer en ny mapper
     */
    public MoviesPersistence() {
        mapper = new ObjectMapper();
        mapper.registerModule(new MoviesModule());
    }

    /**
     * Metode som leser myMovies-objekt fra json-fil
     * 
     * @param reader Tar inn en reader
     * @return Returnerer ett myMovies-objekt
     * @throws IOException
     */
    public MyMovies read(Reader reader) throws IOException {
        return mapper.readValue(reader, MyMovies.class);
    }

    /**
     * Metode som skriver myMovies-objekt til json-fil
     * 
     * @param myMovies myMovies-objekt
     * @param writer   Tar inn en writer
     * @throws IOException
     */
    public void write(MyMovies myMovies, Writer writer) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, myMovies);
    }

}
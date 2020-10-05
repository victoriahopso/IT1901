package mymovies.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import mymovies.core.Film;
import mymovies.core.MyMovies;

@SuppressWarnings("serial")
class MoviesModule extends SimpleModule {

    private static final String NAME = "MyMovies";
    public MoviesModule() {
        super(NAME, Version.unknownVersion());
        addSerializer(Film.class, new FilmSerializer());
        addSerializer(MyMovies.class, new MyMoviesSerializer());
        addDeserializer(Film.class, new FilmDeserializer());
        addDeserializer(MyMovies.class, new MyMoviesDeserializer());
    }
}

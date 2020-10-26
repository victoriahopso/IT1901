package mymovies.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import mymovies.core.AllUsers;
import mymovies.core.Film;
import mymovies.core.User;

@SuppressWarnings("serial")
public class UsersModule extends SimpleModule {

    /**
     * Legger til serializers og deserializers
     */
    private static final String NAME = "Users";

    public UsersModule() {
        super(NAME, Version.unknownVersion());
        addSerializer(User.class, new UserSerializer());
        addDeserializer(User.class, new UserDeserializer());

        addSerializer(AllUsers.class, new AllUsersSerializer());
        addDeserializer(AllUsers.class, new AllUsersDeserializer());

        addSerializer(Film.class, new FilmSerializer());
        addDeserializer(Film.class, new FilmDeserializer());
    }

    //NY LOGIKK: En AllUsers inneholder mange User.
    //           En User inneholder en MyMovies. 
    //           En MyMovies inneholder flere Film
    //
    // LAGRE I SKYEN: AllUsers serialized 
    // HENTE HVILKEN SOM HELST INFO: deserialize AllUsers --> deserialize alt innhold. 
    
}
package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Klasse som har kontroll på opptill flere film-objekt
 */

public class MyMovies implements Iterable<Film> {

    private Collection<Film> movies = new ArrayList<Film>();
    User owner; 

    public Collection<Film> getFilmer() {
        return this.movies;
    }

    /**
     * Legger til film i collection "filmer"
     * @param film Film-objekt
     */
    public void addMovie(Film film){
        if (!movies.contains(film)){
            movies.add(film);
        }
    }

    /**
     * iterator over film-objekter
     * @return Iterator over film-objekter fra collection "filmer"
     */
    @Override
    public Iterator<Film> iterator() {
        return movies.iterator();
    }
    
    public Collection<Film> getMyMovies(){
        return movies.stream().collect(Collectors.toList());
    }

}
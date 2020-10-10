package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Klasse som har kontroll på opptill flere film-objekt
 */

public class MyMovies implements Iterable<Film> {

    private Collection<Film> filmer = new ArrayList<Film>();

    public Collection<Film> getFilmer() {
        return this.filmer;
    }

    public void setFilmer(Collection<Film> filmer) {
        this.filmer = filmer;
    }

    /**
     * Legger til film i collection "filmer"
     * @param film Film-objekt
     */
    public void addMovie(Film film){
        filmer.add(film);
    }

    /**
     * iterator over film-objekter
     * @return Iterator over film-objekter fra collection "filmer"
     */
    @Override
    public Iterator<Film> iterator() {
        return filmer.iterator();
    }
}
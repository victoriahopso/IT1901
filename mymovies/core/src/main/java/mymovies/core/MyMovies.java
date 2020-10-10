package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public class MyMovies implements Iterable<Film> {

    private Collection<Film> movies = new ArrayList<Film>();

    public Collection<Film> getFilmer() {
        return this.movies;
    }

    public void addMovie(Film film){
        if (!movies.contains(film)){
            movies.add(film);
        }
    }

    @Override
    public Iterator<Film> iterator() {
        return movies.iterator();
    }
    
    public Collection<Film> getMyMovies(){
        return movies.stream().collect(Collectors.toList());
    }

}
package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public class MyMovies implements Iterable<Film> {

    private Collection<Film> filmer = new ArrayList<Film>();

    public Collection<Film> getFilmer() {
        return this.filmer;
    }

    public void setFilmer(Collection<Film> filmer) {
        this.filmer = filmer;
    }

    public void addMovie(Film film){
        if (!filmer.contains(film)){
            filmer.add(film);
        }
    }

    @Override
    public Iterator<Film> iterator() {
        return filmer.iterator();
    }
    
    public Collection<Film> getMyMovies(){
        return filmer.stream().collect(Collectors.toList());
    }

}
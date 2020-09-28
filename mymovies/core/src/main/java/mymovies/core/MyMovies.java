package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;

public class MyMovies {

    private Collection<Film> filmer = new ArrayList<Film>();

    public Collection<Film> getFilmer() {
        return this.filmer;
    }

    public void setFilmer(Collection<Film> filmer) {
        this.filmer = filmer;
    }
}
package mymovies.core;


public class Film {

    private String name, genre;
    private int rating;

    public Film(String name, String genre, int rating){
        this.name=name;
        this.genre=genre;
        this.rating=rating;
    }
    
    public String getName(){
        return this.name;
    }
    public String getGenre(){
        return this.genre;
    }
    public int getRating(){
        return this.rating;
    }
    public String toString(){
        return "Film: " + this.name + ", Genre: " + this.genre + ", Rating: " + Integer.toString(this.rating);
    }
}
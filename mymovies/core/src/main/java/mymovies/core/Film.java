package mymovies.core;


public class Film extends MyMovies{
    private String name, genre, comment;
    private int rating;

    public Film(String name, String genre, int rating, String comment){
        this.name=name;
        this.genre=genre;
        this.rating=rating;
        this.comment=comment;
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
    public String getComment(){
        return this.comment;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setGenre(String genre){
        this.genre=genre;
    }
    public void setRating(int rating){
        this.rating=rating;
    }
    public void setComment(String comment){
        this.comment=comment;
    }

    public String toString(){
        return "Film: " + this.name + ", Genre: " + this.genre + ", Rating: " + Integer.toString(this.rating) + ", Comment: " + this.comment;
    }
}
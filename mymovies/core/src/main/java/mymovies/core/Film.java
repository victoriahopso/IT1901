package mymovies.core;

/**
 * Klasse som lager film-objekt
 */
public class Film extends MyMovies{
    private String name, genre;
    private int rating;

    /**
     * Konstruktør som tar inn informasjon om film_objektet
     * @param name Navn på filmen
     * @param genre Sjangeren til filmen
     * @param rating Ratingen til filmen
     */
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
    public void setName(String name){
        this.name=name;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    public void setRating(int rating){
        this.rating=rating;
    }
    /**
     * toString metode som returnerer all informasjon om filmen på en ryddig måte
     * @return String med informasjon om film
     */
    public String toString(){
        return "Film: " + this.name + ", Genre: " + this.genre + ", Rating: " + Integer.toString(this.rating);
    }
}
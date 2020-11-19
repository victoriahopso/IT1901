package mymovies.core;

public class Film {

  private final String name;
  private final String genre;
  private final int rating;

  /**
     * Konstruktør som tar inn informasjon om film_objektet.

     * @param name   Navn på filmen
     * @param genre  Sjangeren til filmen
     * @param rating Ratingen til filmen
     */
  public Film(String name, String genre, int rating) {
    this.name = name;
    this.genre = genre;
    this.rating = rating;
  }

  public String toString() {
    return "Film: " + this.name + ", Genre: " + this.genre 
            + ", Rating: " + Integer.toString(this.rating);
  }

  public String getName() {
    return this.name;
  }

  public String getGenre() {
    return this.genre;
  }

  public int getRating() {
    return this.rating;
  }
}
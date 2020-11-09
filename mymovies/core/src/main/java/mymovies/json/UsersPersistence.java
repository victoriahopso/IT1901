package mymovies.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.fasterxml.jackson.databind.ObjectMapper;

import mymovies.core.AllUsers;

public class UsersPersistence {

  private ObjectMapper mapper;

  /**
   * kontroller som initialiserer en ny mapper
   */
  public UsersPersistence() {
    mapper = new ObjectMapper();
    mapper.registerModule(new UsersModule());
  }

  /**
   * Metode som leser myMovies-objekt fra json-fil
   * 
   * @param reader Tar inn en reader
   * @return Returnerer ett myMovies-objekt
   * @throws IOException
   */
  public AllUsers read(Reader reader) {
    try {
      return mapper.readValue(reader, AllUsers.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Metode som skriver myMovies-objekt til json-fil
   * 
   * @param myMovies myMovies-objekt
   * @param writer   Tar inn en writer
   * @throws IOException
   */
  public void write(AllUsers allUsers, Writer writer) {
    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(writer, allUsers);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
package mymovies.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import mymovies.core.Film;
import mymovies.core.User;

public class UserDeserializer extends JsonDeserializer<User> {

  private FilmDeserializer filmDeserializer = new FilmDeserializer();

  /**
   * format: { "myMovies": [ ... ] }
   */
  @Override
    public User deserialize(JsonParser p, DeserializationContext ctxt) 
        throws IOException, JsonProcessingException {
    TreeNode treeNode = p.getCodec().readTree(p);
    return deserialize((JsonNode) treeNode);
  }

  public User deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) jsonNode;
      User user = new User(objectNode.get("username").asText(), 
                          objectNode.get("password").asText());

      JsonNode itemsNode = objectNode.get("myMovies");
      if (itemsNode instanceof ArrayNode) {
        for (JsonNode elementNode : ((ArrayNode) itemsNode)) {
          Film film = filmDeserializer.deserialize(elementNode);
          if (film != null) {
            user.addMovie(film);
          }
        }
      }
      return user;
    }
    return null;
  }
}
package mymovies.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import mymovies.core.Film;

public class FilmDeserializer extends JsonDeserializer<Film> {

    @Override
    public Film deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
       TreeNode treeNode = p.getCodec().readTree(p);
       if (treeNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) treeNode;
      Film film = new Film(objectNode.get("name").asText(), objectNode.get("genre").asText(), objectNode.get("rating").asInt(), objectNode.get("comment").asText());
      return film;
    }
    return null;
  }   

}
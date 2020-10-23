package mymovies.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import mymovies.core.Film;
import mymovies.core.MyMovies;
import mymovies.core.User;

public class UserDeserializer extends JsonDeserializer<User> {

    private FilmDeserializer filmDeserializer = new FilmDeserializer();

    /*
     * format: { "myMovies": [ ... ] }
     */
    @Override
    public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        return deserialize((JsonNode) treeNode);
    }

    public User deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            User user = new User(objectNode.get("username").asText(), objectNode.get("password").asText());

            TreeNode itemsNode = objectNode.get("myMovies");
            if (itemsNode instanceof ArrayNode) {
                for (TreeNode elementNode : ((ArrayNode) itemsNode)) {
                    Film film = filmDeserializer.deserialize((JsonNode) elementNode);
                    if (film != null) {
                        user.getMyMovies.add(film);
                    }
                }
                return user;
            }
        }
        return null;
    }
}
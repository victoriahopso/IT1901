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

public class MyMoviesDeserializer extends JsonDeserializer<MyMovies> {

    private FilmDeserializer filmDeserializer = new FilmDeserializer();

    /*
    * format: { "myMovies": [ ... ] }
    */
    @Override
    public MyMovies deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        if (treeNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) treeNode;
            MyMovies mymovies = new MyMovies();

            TreeNode itemsNode = objectNode.get("myMovies");
            if (itemsNode instanceof ArrayNode) {
                for (TreeNode elementNode : ((ArrayNode) itemsNode)) {
                    Film film = filmDeserializer.deserialize((JsonNode) elementNode);
                    if (film != null) {
                        mymovies.getFilmer().add(film);
                    }
                }
                return mymovies;
            }
        }
        return null;
    }
}

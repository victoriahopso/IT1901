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

    private FilmDeserializer filmdeserializer = new FilmDeserializer();

   @Override
    public MyMovies deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        if (treeNode instanceof ObjectNode) {
             ObjectNode objectNode = (ObjectNode) treeNode;
             MyMovies myMovies = new MyMovies();
             TreeNode itemsNode = objectNode.get("myMovies");
            if (itemsNode instanceof ArrayNode) {
                for (TreeNode elementNode : ((ArrayNode) itemsNode)) {
                    Film film = filmdeserializer.deserialize(p, ctxt);
                    if (film != null) {
                        myMovies.getFilmer().add(film);
                    }
                }
                return myMovies;
            }
        }
        return null;
}
}

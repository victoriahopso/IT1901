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
import mymovies.core.AllUsers;
import mymovies.core.User;

public class AllUsersDeserializer extends JsonDeserializer<AllUsers> {

  private UserDeserializer userDeserializer = new UserDeserializer();

  @Override
    public AllUsers deserialize(JsonParser p, DeserializationContext ctxt) 
        throws IOException, JsonProcessingException {
    TreeNode treeNode = p.getCodec().readTree(p);
    return deserialize((JsonNode) treeNode);
  }

  /**
   * Deserialiserer fra json til et AllUsers-objekt.

   * @param jsonNode    castes  til arraynode som kan brukes
   *     for å deserialisere et AllUsers-object
   * @return
   *     allUsers ombjekt hvis jsonformatet kan deserialiseres, null hvis ikke. 
   */
  public AllUsers deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) jsonNode;
      AllUsers allUsers = new AllUsers();
      JsonNode itemsNode = objectNode.get("allUsers");
      if (itemsNode instanceof ArrayNode) {
        for (JsonNode elementNode : ((ArrayNode) itemsNode)) {
          User user = userDeserializer.deserialize(elementNode);
          if (user != null) {
            allUsers.addUser(user);
          }
        }
      }
      return allUsers;
    }
    return null;
  }
}
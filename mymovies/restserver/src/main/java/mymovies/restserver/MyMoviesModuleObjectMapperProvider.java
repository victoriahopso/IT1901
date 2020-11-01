package mymovies.restserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import mymovies.json.UsersModule;


@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produce(MediaType.APPLICATION_JSON)
public class MyMoviesModuleObjectMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

public ObjectMapperResolver() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

  @Override
  public ObjectMapper getContext(final Class<?> type) {
    return mapper;
  }

}

//Jeg prøvde meg på noe på egenhånd, men det ble likevel veldig likt Hallvard sitt.
 //Jeg har ikke helt forstått hva denne klassen gjør eksakt, men det virker nesten som at den ikke er så viktig. Trenger mer diskusjon rundt dette
package mymovies.restserver;

import com.fasterxml.jackson.databind.Module;
import mymovies.json.UsersModule;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AllUsersApplication {

  @Bean
    public Module objectMapperModule() {
    return new UsersModule();
  }

  public static void main(String[] args) {
    SpringApplication.run(AllUsersApplication.class, args);
  }
}
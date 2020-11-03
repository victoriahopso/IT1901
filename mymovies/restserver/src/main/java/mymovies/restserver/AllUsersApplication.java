package mymovies.restserver;

import com.fasterxml.jackson.databind.Module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mymovies.json.UsersModule;

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
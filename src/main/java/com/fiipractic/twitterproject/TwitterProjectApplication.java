package com.fiipractic.twitterproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "Twitter API", version = "1.0.0"),
        servers = {@Server(url = "http://localhost:8081")},
        tags = {
                @Tag(name = "Reply"),
                @Tag(name = "Post"),
                @Tag(name = "Like"),
                @Tag(name = "Follow"),
                @Tag(name = "Auth"),
                @Tag(name = "User")

        }

)
@SecurityScheme(
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class TwitterProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterProjectApplication.class, args);
    }

}

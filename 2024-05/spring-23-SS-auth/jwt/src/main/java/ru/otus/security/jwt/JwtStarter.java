package ru.otus.security.jwt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtStarter {
    public static void main(String[] args){
        SpringApplication.run( JwtStarter.class, args );
    }
}

package com.example.authservice;

import com.example.authservice.entity.Movie;
import com.example.authservice.entity.User;
import com.example.authservice.feign.MovieServiceClient;
import com.example.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AuthServiceApplication {
    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void initUsers() {
        List<User> user = Stream.of(
                new User(1, "user1", "1234", "user1@gmail.com"),
                new User(2, "user2", "12345", "user2@gmail.com"),
                new User(3, "user3", "1234", "user3@gmail.com")
        ).collect(Collectors.toList());
        repository.saveAll(user);
    }
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate createTemplate() {
        return new RestTemplate();
    }

    /*@Bean
    CommandLineRunner start(MovieServiceClient movieServiceClient){
        return args ->{
            Movie movie =movieServiceClient.saveMovie(new Movie());
        };
    }*/
}

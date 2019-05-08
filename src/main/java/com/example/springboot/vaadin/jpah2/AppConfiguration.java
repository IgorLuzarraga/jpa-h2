package com.example.springboot.vaadin.jpah2;

import com.example.springboot.vaadin.jpah2.domain.Book;
import com.example.springboot.vaadin.jpah2.services.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public CommandLineRunner loadData(BookService bookService){
        return (args) -> {
            bookService.deleteAll();

            // save some books
            bookService.save(new Book("BDD IN ACTION", "John Ferguson Smart"));
            bookService.save(new Book("Clean Code", "Robert C. Martin"));
            bookService.save(new Book("Clean Architecture", "Robert C. Martin"));
            bookService.save(new Book("Redis IN ACTION", "Josiah L. Carlson"));
            bookService.save(new Book("Spring Boot IN ACTION", "Craig Walls"));
            bookService.save(new Book("Functional Programming in Java", "Venkat Subramaniam"));
        };
    }
}

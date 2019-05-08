package com.example.springboot.vaadin.jpah2.services;

import com.example.springboot.vaadin.jpah2.domain.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAll();
    public List<Book> findByTitle(String title);
    public List<Book> findByAuthor(String author);
    public Book save(Book book);
    public void delete(Book book);
    public void deleteAll();
    public Long count();
}

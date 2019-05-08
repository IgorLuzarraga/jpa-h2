package com.example.springboot.vaadin.jpah2.services;

import com.example.springboot.vaadin.jpah2.repositories.BookRepository;
import com.example.springboot.vaadin.jpah2.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByTitle(String title){
        return bookRepository.
                findByTitleStartsWithIgnoreCase(title);
    }

    @Override
    public List<Book>  findByAuthor(String author){
        return bookRepository
                .findByAuthorStartsWithIgnoreCase(author);
    }

    @Override
    public Book save(Book book){
        return bookRepository.save(book);
    }

    @Override
    public void delete(Book book){
        bookRepository.delete(book);
    }

    @Override
    public void deleteAll(){
        bookRepository.deleteAll();
    }

    @Override
    public Long count(){
        return bookRepository.count();
    }
}

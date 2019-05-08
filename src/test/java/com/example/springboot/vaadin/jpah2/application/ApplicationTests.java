package com.example.springboot.vaadin.jpah2.application;

import com.example.springboot.vaadin.jpah2.Application;
import com.example.springboot.vaadin.jpah2.domain.Book;
import com.example.springboot.vaadin.jpah2.services.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ApplicationTests {
    private static final String AUTHOR = "Robert C. Martin";

    @Autowired
    private BookService bookService;

    @Test
    public void whenTheApplicationIsStarted_ThenFillOutComponentsWithData() {
        long bookCount =  bookService.count();
        long expectedbookCount = 6;

        assertEquals(expectedbookCount, bookCount);
    }

    @Test
    public void whenTheApplicationIsStarted_ThenFindTwoUncleBobBooks() {
        List<Book> bookList;
        bookList = bookService.findByAuthor(AUTHOR);

        assertEquals(2, bookList.size());
    }
}


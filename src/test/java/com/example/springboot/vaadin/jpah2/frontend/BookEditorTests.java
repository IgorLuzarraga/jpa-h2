package com.example.springboot.vaadin.jpah2.frontend;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.then;

import com.example.springboot.vaadin.jpah2.domain.Book;
import com.example.springboot.vaadin.jpah2.repositories.BookRepository;
import com.example.springboot.vaadin.jpah2.services.BookService;
import com.example.springboot.vaadin.jpah2.services.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BookEditorTests {

    private static final String TITLE = "Test-Driven Java Development";
    private static final String AUTHOR = "V. Farcic / A. Garcia";

    @Mock
    IAddBooksToGrid addBooksToGrid;
    @Mock
    BookService bookService;

    BookEditor editor;

    @Before
    public void init() {

        editor = new BookEditor(bookService);

        editor.setAddBooksToGrid(addBooksToGrid);
    }

    @Test
    public void givenBook_WhenEditorSaveClicked_ThenStoreBook() {
        // given
        bookDataSetToForm();

        // when
        editor.save();

        // then
        then(bookService).should().save(argThat(bookMatchesEditorFields()));
        then(addBooksToGrid).should().onEdition();
    }

    @Test
    public void givenBook_WhenEditorDeleteClicked_ThenDeleteBook() {
        // given
        bookDataSetToForm();

        // when
        editor.delete();

        // then
        then(bookService).should().delete(argThat(bookMatchesEditorFields()));
        then(addBooksToGrid).should().onEdition();
    }

    private void bookDataSetToForm() {
        editor.editBook(Optional.ofNullable(new Book(TITLE, AUTHOR)));
    }

    private ArgumentMatcher<Book> bookMatchesEditorFields() {
        return book -> TITLE.equals(book.getTitle()) && AUTHOR.equals(book.getAuthor());
    }

}

package com.example.springboot.vaadin.jpah2.frontend;

import com.example.springboot.vaadin.jpah2.domain.Book;
import com.example.springboot.vaadin.jpah2.services.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@SpringComponent
@UIScope
public class BuildStarLayout extends VerticalLayout{
    private Grid<Book> grid;
    private TextField filterAuthor;
    private TextField filterTitle;
    private Button addNewBtn;
    private final String GRID_COLUMN_ONE = "id";
    private final String GRID_COLUMN_TWO = "title";
    private final String GRID_COLUMN_THREE = "author";
    private final String ADD_NEW_BUTTON_TEXT = "New book";
    private final String FILTER_AUTHOR_TEXT = "Filter by author";
    private final String FILTER_TITLE_TEXT = "Filter by title";
    private BookEditor bookEditor;
    private BookService bookService;

    @Autowired
    public BuildStarLayout(BookEditor bookEditor,
                           BookService bookService){
        this.bookEditor = bookEditor;
        this.bookService = bookService;

        buildLayaout();
        setUpComponents();
        addBooksToGrid();
    }

    public Grid getGrid(){
        return grid;
    }

    private void buildLayaout(){
        grid = new Grid<>(Book.class);
        filterAuthor = new TextField();
        filterTitle = new TextField();
        addNewBtn = new Button(ADD_NEW_BUTTON_TEXT, VaadinIcon.PLUS.create());

        HorizontalLayout filters = new HorizontalLayout(filterTitle, filterAuthor);
        HorizontalLayout actions = new HorizontalLayout(filters, addNewBtn);

        add(actions, grid);
    }

    private void setUpComponents(){
        setUpGrid();
        setUpFilters();
        setUpAddNewBtn();
        setUpEditor();
    }

    private void setUpGrid(){
        grid.setHeight("300px");

        // We don't show the id column because it'a a very long string
        //grid.setColumns(GRID_COLUMN_ONE, GRID_COLUMN_TWO, GRID_COLUMN_THREE);
        //grid.getColumnByKey(GRID_COLUMN_ONE).setWidth("50px").setFlexGrow(0);

        grid.setColumns(GRID_COLUMN_TWO, GRID_COLUMN_THREE);

        // Bind logic to component

        // Connect selected Book to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            bookEditor.editBook(Optional.ofNullable(e.getValue()));
            setVisible(false);
        });
    }

    private void setUpFilters(){
        filterTitle.setPlaceholder(FILTER_TITLE_TEXT);
        filterAuthor.setPlaceholder(FILTER_AUTHOR_TEXT);

        filterTitle.focus();

        // Bind logic to component

        // Show new list of books when user changes content
        filterTitle.setValueChangeMode(ValueChangeMode.EAGER);
        filterTitle.addValueChangeListener(e -> addBooksByTitleToGrid(e.getValue()));
        filterAuthor.setValueChangeMode(ValueChangeMode.EAGER);
        filterAuthor.addValueChangeListener(e -> addBooksByAuthorToGrid(e.getValue()));
    }

    private void setUpAddNewBtn(){
        addNewBtn.setWidth("250px");

        // Bind logic to component

        // Instantiate and edit new Book when new button is clicked
        addNewBtn.addClickListener(e -> {
            bookEditor.editBook(
                        Optional.ofNullable(new Book("", "")));
            setVisible(false);
        });
    }

    private void setUpEditor(){
        // Bind logic to component

        // send the behaviour to implement when the book editor
        // made changes (save, delete, cancel)
        bookEditor.setAddBooksToGrid(() -> {
            addBooksToGrid();
            resetComponents();
        });
    }

    private void resetComponents(){
        bookEditor.setVisible(false);
        filterTitle.clear();
        filterAuthor.clear();
        filterTitle.focus();
        setVisible(true);
    }

    public void addBooksToGrid(){
       grid.setItems(bookService.findAll());
    }

    public void addBooksByTitleToGrid(String title) {
        grid.setItems(bookService.findByTitle(title));
    }

    public void addBooksByAuthorToGrid(String author) {
        grid.setItems(bookService.findByAuthor(author));
    }
}

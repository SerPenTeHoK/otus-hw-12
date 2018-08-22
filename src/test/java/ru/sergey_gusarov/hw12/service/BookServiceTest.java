package ru.sergey_gusarov.hw12.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sergey_gusarov.hw12.domain.books.Author;
import ru.sergey_gusarov.hw12.domain.books.Book;
import ru.sergey_gusarov.hw12.domain.books.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@ComponentScan("ru.sergey_gusarov.hw12.service")
class BookServiceTest {
    @Autowired
    private  BookService bookService;

   // BookServiceTest(BookService bookService) { this.bookService = bookService; }


    private Book dummyBook3Genre1AuthorName3() {
        List<Genre> genres = new ArrayList<>(1);
        genres.add(new Genre("Genre1"));
        List<Author> authors = new ArrayList<>(1);
        authors.add(new Author("Author3"));
        Book book = new Book("Title3");
        book.setAuthors(authors);
        book.setGenres(genres);
        return book;
    }

    private Book dummyBookTitleGenre1AuthorName3(String title) {
        List<Genre> genres = new ArrayList<>(1);
        genres.add(new Genre("Genre1"));
        List<Author> authors = new ArrayList<>(1);
        authors.add(new Author("Author3"));
        Book book = new Book(title);
        book.setAuthors(authors);
        book.setGenres(genres);
        return book;
    }

    @BeforeEach
    private void reSetupSchema() {
        bookService.deleteAll();
        Book book = dummyBook3Genre1AuthorName3();
        List authors = Arrays.asList(book.getAuthors().get(0).getName());
        List genres = Arrays.asList(book.getGenres().get(0).getName());
        bookService.save(book.getTitle(), authors, genres);
    }

    @Test
    @DisplayName("Count")
    void bookCount() {
        Book book1 = dummyBookTitleGenre1AuthorName3("Title1");
        Book book2 = dummyBookTitleGenre1AuthorName3("Title2");
        bookService.save(book1.getTitle(), book1.getAuthors(), book1.getGenres());
        bookService.save(book2.getTitle(), book2.getAuthors(), book2.getGenres());
        long count = bookService.bookCount();
        assertEquals(3L, count);
    }

    @Test
    @DisplayName("Get by id")
    void bookGetById() {
        Book bookCreated = dummyBook3Genre1AuthorName3();
        List<Book> booksfromDbByTitle = bookService.bookGetByTitle(bookCreated.getTitle());
        Book fromDbByTitle = booksfromDbByTitle.get(0);
        Optional<Book> orionaBookfromDb = bookService.bookGetById(fromDbByTitle.getId());
        Book fromDb = orionaBookfromDb.get();
        assertEquals(bookCreated.getAuthors().get(0).getName(),
                fromDb.getAuthors().get(0).getName(), "Authors doesn't match");
        assertEquals(bookCreated.getGenres().get(0).getName(),
                fromDb.getGenres().get(0).getName(), "Genre doesn't match");
    }

    @Test
    @DisplayName("Delete by id")
    void bookDeleteById() {
        Book bookCreated = dummyBook3Genre1AuthorName3();
        List<Book> booksfromDbByTitle = bookService.bookGetByTitle(bookCreated.getTitle());
        Book fromDbByTitle = booksfromDbByTitle.get(0);
        Optional<Book> orionaBookfromDb = bookService.bookGetById(fromDbByTitle.getId());
        Book fromDb = orionaBookfromDb.get();
        bookService.bookDeleteById(fromDb.getId());
        long count = bookService.bookCount();
        assertEquals(0L, count);
    }

    @Test
    @DisplayName("List")
    void bookList() {
        List<Book> books = bookService.bookList();
        long count = books.size();
        assertEquals(1L, count);
        assertEquals(books.get(0).getTitle(), "Title3", "Title not valid");
    }
}
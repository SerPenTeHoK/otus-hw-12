package ru.sergey_gusarov.hw12.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sergey_gusarov.hw12.domain.books.Author;
import ru.sergey_gusarov.hw12.domain.books.Book;
import ru.sergey_gusarov.hw12.domain.books.Genre;
import ru.sergey_gusarov.hw12.repository.books.BookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class BookServiceImplTest {
    @Autowired
    private BookService bookService;

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

    @BeforeEach
    private void reSetupSchema() {
        bookService.deleteAll();
        Book book = dummyBook3Genre1AuthorName3();
        List authors = Arrays.asList(book.getGenres().get(0).getName());
        List genres = Arrays.asList(book.getAuthors().get(0).getName());
        bookService.save(book.getTitle(), authors, genres);
    }


    @Test
    void bookCount() {
    }

    @Test
    void bookGetById() {
    }

    @Test
    void bookGetByTitle() {
    }

    @Test
    void bookDeleteById() {
    }

    @Test
    void save() {
    }

    @Test
    void bookList() {
    }
}
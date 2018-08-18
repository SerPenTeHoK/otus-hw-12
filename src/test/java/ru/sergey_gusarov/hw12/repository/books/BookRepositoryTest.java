package ru.sergey_gusarov.hw12.repository.books;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sergey_gusarov.hw12.domain.books.Author;
import ru.sergey_gusarov.hw12.domain.books.Book;
import ru.sergey_gusarov.hw12.domain.books.BookComment;
import ru.sergey_gusarov.hw12.domain.books.Genre;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class BookRepositoryTest {
    private final static String COMMENT_FOR_ADD_1 = "comment1";

    @Autowired
    private BookRepository bookRepository;

    private Book dummyBook1Genre1Author2() {
        List<Genre> genres = new ArrayList<>(1);
        genres.add(new Genre("Genre1"));
        List<Author> authors = new ArrayList<>(2);
        authors.add(new Author("Author1"));
        authors.add(new Author("Author2"));
        Book book = new Book("Title1");
        book.setAuthors(authors);
        book.setGenres(genres);
        return book;
    }

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

    }

    @Test
    @DisplayName("Count")
    void count() {
        bookRepository.save(new Book("Title1"));
        bookRepository.save(new Book("Title2"));
        bookRepository.save(new Book("Title3"));
        long count = bookRepository.count();
        assertEquals(3L, count);
    }


}
package ru.sergey_gusarov.hw12.repository.books;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sergey_gusarov.hw12.domain.books.Author;
import ru.sergey_gusarov.hw12.domain.books.Book;
import ru.sergey_gusarov.hw12.domain.books.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

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
        bookRepository.deleteAll();
        bookRepository.save(dummyBook3Genre1AuthorName3());
    }

    @Test
    @DisplayName("Count")
    void count() {
        bookRepository.save(new Book("Title1"));
        bookRepository.save(new Book("Title2"));
        long count = bookRepository.count();
        assertEquals(3L, count);
    }

    @Test
    @DisplayName("Save")
    void save() {
        Book bookCreated = dummyBook3Genre1AuthorName3();
        List<Book> booksfromDb = bookRepository.findByTitle(bookCreated.getTitle());
        Book fromDb = booksfromDb.get(0);
        assertEquals(bookCreated.getAuthors().get(0).getName(),
                fromDb.getAuthors().get(0).getName(), "Authors doesn't match");
        assertEquals(bookCreated.getGenres().get(0).getName(),
                fromDb.getGenres().get(0).getName(), "Genre doesn't match");
    }

    @Test
    @DisplayName("Get by id")
    void getById() {
        Book bookCreated = dummyBook3Genre1AuthorName3();
        List<Book> booksfromDbByTitle = bookRepository.findByTitle(bookCreated.getTitle());
        Book fromDbByTitle = booksfromDbByTitle.get(0);
        Optional<Book> orionaBookfromDb = bookRepository.findById(fromDbByTitle.getId());
        Book fromDb = orionaBookfromDb.get();
        assertEquals(bookCreated.getAuthors().get(0).getName(),
                fromDb.getAuthors().get(0).getName(), "Authors doesn't match");
        assertEquals(bookCreated.getGenres().get(0).getName(),
                fromDb.getGenres().get(0).getName(), "Genre doesn't match");
    }

    @Test
    @DisplayName("Delete by id")
    void deleteById() {
        Book bookCreated = dummyBook3Genre1AuthorName3();
        List<Book> booksfromDbByTitle = bookRepository.findByTitle(bookCreated.getTitle());
        Book fromDbByTitle = booksfromDbByTitle.get(0);
        Optional<Book> orionaBookfromDb = bookRepository.findById(fromDbByTitle.getId());
        Book fromDb = orionaBookfromDb.get();
        bookRepository.deleteById(fromDb.getId());
        long count = bookRepository.count();
        assertEquals(0L, count);
    }



}
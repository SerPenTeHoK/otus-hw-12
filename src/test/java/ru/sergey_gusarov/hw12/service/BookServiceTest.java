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
import ru.sergey_gusarov.hw12.repository.author.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@ComponentScan("ru.sergey_gusarov.hw12.service")
class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorRepository authorRepository;

    private Book dummyBook3Genre1AuthorName3(boolean isSaveAuthors) {
        List<Genre> genres = new ArrayList<>(1);
        genres.add(new Genre("Genre1"));
        List<Author> authors = new ArrayList<>(1);
        authors.add(new Author("Author3"));
        authors.add(new Author("Author4"));
        if(isSaveAuthors)
            authors.forEach(author -> authorRepository.save(author));
        Book book = new Book("Title3");
        book.setAuthors(authors);
        book.setGenres(genres);
        return book;
    }

    private Book dummyBookTitleGenre1AuthorName3(String title, String authorName) {
        List<Genre> genres = new ArrayList<>(1);
        genres.add(new Genre("Genre1"));
        List<Author> authors = new ArrayList<>(1);
        authors.add(new Author(authorName));
        authors.forEach(author -> authorRepository.save(author));
        Book book = new Book(title);
        book.setAuthors(authors);
        book.setGenres(genres);
        return book;
    }

    @BeforeEach
    private void reSetup() {
        bookService.deleteAll();
        authorRepository.deleteAll();
        Book book = dummyBook3Genre1AuthorName3(true);
        bookService.save(book.getTitle(), book.getAuthors(), book.getGenres());
    }

    @Test
    @DisplayName("Count")
    void bookCount() {
        Book book1 = dummyBookTitleGenre1AuthorName3("Title1", "Author01");
        Book book2 = dummyBookTitleGenre1AuthorName3("Title2", "Author02");
        bookService.save(book1.getTitle(), book1.getAuthors(), book1.getGenres());
        bookService.save(book2.getTitle(), book2.getAuthors(), book2.getGenres());
        long count = bookService.bookCount();
        assertEquals(3L, count);
    }

    @Test
    @DisplayName("Get by id")
    void bookGetById() {
        Book bookCreated = dummyBook3Genre1AuthorName3(false);
        List<Book> booksFromDbByTitle = bookService.bookGetByTitle(bookCreated.getTitle());
        Book fromDbByTitle = booksFromDbByTitle.get(0);
        Optional<Book> optionalBookFromDb = bookService.bookGetById(fromDbByTitle.getId());
        Book fromDb = optionalBookFromDb.get();
        assertEquals(bookCreated.getAuthors().get(0).getName(),
                fromDb.getAuthors().get(0).getName(), "Authors doesn't match");
        assertEquals(bookCreated.getGenres().get(0).getName(),
                fromDb.getGenres().get(0).getName(), "Genre doesn't match");
    }

    @Test
    @DisplayName("Delete by id")
    void bookDeleteById() {
        Book bookCreated = dummyBook3Genre1AuthorName3(false);
        List<Book> booksFromDbByTitle = bookService.bookGetByTitle(bookCreated.getTitle());
        Book fromDbByTitle = booksFromDbByTitle.get(0);
        Optional<Book> optionalBookFromDb = bookService.bookGetById(fromDbByTitle.getId());
        Book fromDb = optionalBookFromDb.get();
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
    @Test
    @DisplayName("Add comment")
    void addComment() {
        List<Book> books = bookService.bookList();
        bookService.addComment(books.get(0).getId(), "Comment1");
        books = bookService.bookList();
        assertEquals(books.get(0).getBookComments().get(0).getText(), "Comment1", "Book.Comment s not stored");
    }

    @Test
    @DisplayName("Add comment")
    void findByAuthorName() {
        List<Book> books = bookService.bookList();
        Book book = books.get(0);
        List<Book> booksFromDb = bookService.findByAuthorName(book.getAuthors().get(0).getName());
    }
}
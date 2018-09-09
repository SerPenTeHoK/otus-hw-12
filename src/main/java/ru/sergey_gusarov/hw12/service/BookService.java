package ru.sergey_gusarov.hw12.service;

import ru.sergey_gusarov.hw12.domain.books.Author;
import ru.sergey_gusarov.hw12.domain.books.Book;
import ru.sergey_gusarov.hw12.domain.books.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {
    long count();

    Optional<Book> findById(String id);

    List<Book> findByTitle(String title);

    void deleteById(String id);

    Book add(String title, List<Author> authors, List<Genre> genres);

    Book save(Book book);

    List<Book> findAll();

    void deleteAll();

    void addComment(String id, String comment);

    List<Book> findByAuthorName(String authorName);
}

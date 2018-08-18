package ru.sergey_gusarov.hw12.service;

import ru.sergey_gusarov.hw12.domain.books.Author;
import ru.sergey_gusarov.hw12.domain.books.Book;
import ru.sergey_gusarov.hw12.domain.books.Genre;
import ru.sergey_gusarov.hw12.repository.books.BookRepository;

import java.util.List;
import java.util.Set;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(String title, List<Genre> genres, List<Author> authors) {
        Book book = new Book();
        book.setTitle(title);
        book.setGenres(genres);
        book.setAuthors(authors);
        bookRepository.save(book);
        return null;
    }
}

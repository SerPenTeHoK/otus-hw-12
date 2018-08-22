package ru.sergey_gusarov.hw12.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergey_gusarov.hw12.domain.books.Author;
import ru.sergey_gusarov.hw12.domain.books.Book;
import ru.sergey_gusarov.hw12.domain.books.Genre;
import ru.sergey_gusarov.hw12.repository.books.AuthorRepository;
import ru.sergey_gusarov.hw12.repository.books.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public long bookCount() {
        return bookRepository.count();
    }

    @Override
    public Optional<Book> bookGetById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> bookGetByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public void bookDeleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book save(String title, List<Author> authors, List<Genre> genres) {
        Book book = new Book();
        book.setTitle(title);
        authors.forEach(author -> {
            List<Author> authorList = authorRepository.findByName(author.getName());
            if (authorList.size() > 0)
                author.setId(authorList.get(0).getId());
            else
                authorRepository.save(author);
        });
        book.setGenres(genres);
        book.setAuthors(authors);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> bookList() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }
}

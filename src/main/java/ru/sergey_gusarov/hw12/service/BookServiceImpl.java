package ru.sergey_gusarov.hw12.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergey_gusarov.hw12.domain.books.Author;
import ru.sergey_gusarov.hw12.domain.books.Book;
import ru.sergey_gusarov.hw12.domain.books.Genre;
import ru.sergey_gusarov.hw12.repository.books.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    // Прокоментируйте, пожалуйста, почеиу
    // если делаю через конструктор и поле bookRepository с модификаторм final,
    // то у меня падает с циклической ссылкой
    /*
    public BookServiceImpl(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
    }
    */

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
    public Book save(String title, List<Author> authors,  List<Genre> genres) {
        Book book = new Book();
        book.setTitle(title);
        book.setGenres(genres);
        book.setAuthors(authors);
        bookRepository.save(book);
        return null;
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

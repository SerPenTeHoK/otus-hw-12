package ru.sergey_gusarov.hw12.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey_gusarov.hw12.repository.books.BookRepository;


@ShellComponent
public class BookShell {

    @Autowired
    private BookRepository bookRepository;

    @ShellMethod("Book count")
    public long bookCount() {
        return bookRepository.count();
    }

    @ShellMethod("Book get by id")
    public String bookGetById(@ShellOption String id) {
        return bookRepository.findById(id).toString();
    }

    @ShellMethod("Book get by id")
    public String bookGetByTitle(@ShellOption String title) {
        return bookRepository.findByTitle(title).toString();
    }

    @ShellMethod("Book delete by id")
    public void bookDeleteById(@ShellOption String id) {
        bookRepository.deleteById(id);
    }

    @ShellMethod("Book insert")
    @Transactional
    public void bookInsert(@ShellOption String title, @ShellOption String authorName, @ShellOption String genreName) {
        ;
    }

    @ShellMethod("Book list")
    public String bookList() {
        return bookRepository.findAll().toString();
    }
}


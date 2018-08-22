package ru.sergey_gusarov.hw12.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.sergey_gusarov.hw12.service.BookService;

import java.util.Arrays;
import java.util.List;


@ShellComponent
public class BookShell {
    private final BookService bookService;

    public BookShell(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("Book count")
    public long bookCount() {
        return bookService.bookCount();
    }

    @ShellMethod("Book get by id")
    public String bookGetById(@ShellOption String id) {
        return bookService.bookGetById(id).get().toString();
    }

    @ShellMethod("Book get by id")
    public String bookGetByTitle(@ShellOption String title) {
        return bookService.bookGetByTitle(title).toString();
    }

    @ShellMethod("Book delete by id")
    public void bookDeleteById(@ShellOption String id) {
        bookService.bookDeleteById(id);
    }

    @ShellMethod("Book insert")
    public void bookInsert(@ShellOption String title, @ShellOption String genreName, @ShellOption String authorName) {
        List genres = Arrays.asList(genreName);
        List authors = Arrays.asList(authorName);
        bookService.save(title, genres, authors);
    }

    @ShellMethod("Book list")
    public String bookList() {
        return bookService.bookList().toString();
    }
}


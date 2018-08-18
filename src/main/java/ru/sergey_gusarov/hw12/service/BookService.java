package ru.sergey_gusarov.hw12.service;

import ru.sergey_gusarov.hw12.domain.books.Author;
import ru.sergey_gusarov.hw12.domain.books.Book;
import ru.sergey_gusarov.hw12.domain.books.Genre;

import java.util.List;
import java.util.Set;

public interface BookService {
    Book save(String title, List<Genre> genres, List<Author> authors);
}

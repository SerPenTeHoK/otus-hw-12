package ru.sergey_gusarov.hw12.repository.books;

import ru.sergey_gusarov.hw12.domain.books.Book;

import java.util.List;

public interface AuthorRepositoryCustom {
    List<Book> getAuthorAllBookMethod(String authorName);
}

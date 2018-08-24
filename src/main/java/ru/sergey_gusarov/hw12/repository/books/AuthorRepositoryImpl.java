package ru.sergey_gusarov.hw12.repository.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.sergey_gusarov.hw12.domain.books.Book;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepositoryCustom {
    @Autowired
    private MongoOperations mongoOperations;

    @Override

    public List<Book> getAuthorAllBookMethod(String authorName) {
        //BasicQuery query = new BasicQuery("{ age : { $lt : 40 }, name : 'cat' }");
        Query query = new Query();
        query.addCriteria(Criteria.where("author").is(authorName));
        List<Book> books = mongoOperations.find(query, Book.class);
        return books;
    }
}

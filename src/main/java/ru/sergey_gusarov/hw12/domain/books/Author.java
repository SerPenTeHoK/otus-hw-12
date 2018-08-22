package ru.sergey_gusarov.hw12.domain.books;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "author")
public class Author {
    @Id
    private String id;
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                ", name='" + name + '\'' +
                '}';
    }
}

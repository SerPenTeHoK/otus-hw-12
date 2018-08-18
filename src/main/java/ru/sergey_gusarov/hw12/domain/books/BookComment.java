package ru.sergey_gusarov.hw12.domain.books;

public class BookComment {
    String text;

    public BookComment() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(60);
        sb.append("BookComment{ ")
                .append(", text='")
                .append(getText())
                .append("' book = ");
        return sb.toString();
    }
}

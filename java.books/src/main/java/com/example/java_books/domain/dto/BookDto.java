package com.example.java_books.domain.dto;

import com.example.java_books.domain.entities.Author;

public class BookDto {
    private String title;
    private String isbn;
    private Author author;

    public BookDto() {
    }

    public String getTitle() {
        return title;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public BookDto setAuthor(Author author) {
        this.author = author;
        return this;
    }
}

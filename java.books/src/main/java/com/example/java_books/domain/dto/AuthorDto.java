package com.example.java_books.domain.dto;

public class AuthorDto {

    private String name;

    public AuthorDto() {
    }

    public String getName() {
        return name;
    }

    public AuthorDto setName(String name) {
        this.name = name;
        return this;
    }
}

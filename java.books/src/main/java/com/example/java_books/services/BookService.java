package com.example.java_books.services;

import com.example.java_books.domain.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();
}

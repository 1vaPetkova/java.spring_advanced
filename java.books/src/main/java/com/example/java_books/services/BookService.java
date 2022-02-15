package com.example.java_books.services;

import com.example.java_books.domain.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDto> getAllBooks();
   Optional< BookDto> getBookById(Long id);
}

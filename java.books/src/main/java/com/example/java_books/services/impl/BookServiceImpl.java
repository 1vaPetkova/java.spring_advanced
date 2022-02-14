package com.example.java_books.services.impl;

import com.example.java_books.domain.dto.BookDto;
import com.example.java_books.domain.entities.Book;
import com.example.java_books.repositories.BookRepository;
import com.example.java_books.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return this.bookRepository
                .findAll()
                .stream()
                .map(book -> this.modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }
}

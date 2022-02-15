package com.example.java_books.services.impl;

import com.example.java_books.domain.dto.AuthorDto;
import com.example.java_books.domain.dto.BookDto;
import com.example.java_books.domain.entities.Book;
import com.example.java_books.repositories.BookRepository;
import com.example.java_books.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<BookDto> getBookById(Long id) {
        return this.bookRepository
                .findById(id)
                .map(this::asBook);
    }

    private BookDto asBook(Book book) {
        BookDto bookDto = this.modelMapper.map(book, BookDto.class);
        AuthorDto authorDto = this.modelMapper.map(book.getAuthor(), AuthorDto.class);
        bookDto.setAuthor(authorDto);
        return bookDto;
    }
}

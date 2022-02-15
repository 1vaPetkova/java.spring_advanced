package com.example.java_books.services.impl;

import com.example.java_books.domain.dto.AuthorDto;
import com.example.java_books.domain.dto.BookDto;
import com.example.java_books.domain.entities.Author;
import com.example.java_books.domain.entities.Book;
import com.example.java_books.repositories.AuthorRepository;
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
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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

    @Override
    public void deleteBookById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Long createBook(BookDto bookDto) {
        Author author = this.authorRepository
                .findByName(bookDto.getAuthor().getName())
                .orElseGet(() -> new Author().setName(bookDto.getAuthor().getName()));
        this.authorRepository.save(author);
        Book newBook = new Book().setAuthor(author).setTitle(bookDto.getTitle()).setIsbn(bookDto.getIsbn());

        return this.bookRepository.save(newBook).getId();
    }

    private BookDto asBook(Book book) {
        BookDto bookDto = this.modelMapper.map(book, BookDto.class);
        AuthorDto authorDto = this.modelMapper.map(book.getAuthor(), AuthorDto.class);
        bookDto.setAuthor(authorDto);
        return bookDto;
    }
}

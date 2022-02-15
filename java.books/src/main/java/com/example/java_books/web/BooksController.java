package com.example.java_books.web;

import com.example.java_books.domain.dto.BookDto;
import com.example.java_books.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> allBooks = this.bookService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
        Optional<BookDto> bookById = this.bookService.getBookById(id);
        if (bookById.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            return ResponseEntity.ok(bookById.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBookById(@PathVariable("id") Long id) {
        this.bookService.deleteBookById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto,
                                          UriComponentsBuilder uriComponentsBuilder) {
        //http://localhost:8080/books/id

        long bookId = this.bookService.createBook(bookDto);
        URI location = uriComponentsBuilder.path("/books/{id}").buildAndExpand(bookId).toUri();
        return ResponseEntity
                .created(location)
                .build();
    }
}

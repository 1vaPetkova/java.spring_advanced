package com.example.java_books.init;

import com.example.java_books.domain.entities.Author;
import com.example.java_books.domain.entities.Book;
import com.example.java_books.repositories.AuthorRepository;
import com.example.java_books.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BooksInit implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public BooksInit(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.bookRepository.count() == 0 && this.authorRepository.count() == 0) {
            initJovkov();
            initNikolaiHaitov();
            initDimitarDimov();
            initElinPelin();
            initVazov();
        }
    }

    private void initNikolaiHaitov() {
        initAuthor("Николай Хайтов", "Диви Разкази");
    }

    private void initDimitarDimov() {
        initAuthor("Димитър Димов", "Тютюн");
    }

    private void initElinPelin() {
        initAuthor("Елин Пелин", "Пижо и Пендо", "Ян Бибиян на луната", "Под манастирската лоза");
    }

    private void initVazov() {
        initAuthor("Иван Вазов", "Пряпорец и Гусла", "Под Игото", "Тъгите на България");
    }

    private void initJovkov() {
        initAuthor("Йордан Йовков", "Старопланински легенди", "Чифликът край границата");
    }

    private void initAuthor(String authorName, String... books) {
        Author author = new Author().setName(authorName);
        author = this.authorRepository.save(author);

        Set<Book> allBooks = new HashSet<>();

        for (String book : books) {
            Book aBook = new Book()
                    .setAuthor(author)
                    .setTitle(book)
                    .setIsbn(UUID.randomUUID().toString());
            allBooks.add(aBook);
        }

        author.setBooks(allBooks);

        this.bookRepository.saveAll(allBooks);
    }


}

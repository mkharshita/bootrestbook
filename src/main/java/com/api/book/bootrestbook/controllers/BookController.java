package com.api.book.bootrestbook.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = this.bookService.getAllBooks();
        if(books.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(books));
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable("id") int id) {
        return this.bookService.getBookById(id);
    }
    
    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) {
        Book b = this.bookService.addBook(book);
        return b;
    }
    
    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable("id") int id) {
        this.bookService.deleteBook(id);
        return;
    }    

    @PutMapping("/book/{id}")
    public Book updateBookById(@PathVariable("id") int id, @RequestBody Book book) {
        this.bookService.updateBookById(id,book);
        return book;
    }
}

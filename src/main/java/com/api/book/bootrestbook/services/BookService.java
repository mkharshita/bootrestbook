package com.api.book.bootrestbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks()
    {
        List<Book> books =  (List<Book>) this.bookRepository.findAll();
        return books;
    }

    public Book getBookById(int id)
    {
        Book book = null;
        try{
            //book = list.stream().filter(e->e.getId()==id).findFirst().get();
            this.bookRepository.findById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }

    public Book addBook(Book book){
        Book result = this.bookRepository.save(book);
        return result;
    }

    public void deleteBook(int id) {
        this.bookRepository.deleteById(id);
    }

    public void updateBookById(int id, Book book) {
        // list.stream().map(b -> {
        //     if(b.getId() == id){
        //         b.setAuthor(book.getAuthor());
        //         b.setTitle(book.getTitle());
        //     }   
        //     return book;
        // }).collect(Collectors.toList());
        book.setId(id);
        this.bookRepository.save(book);
    } 
}

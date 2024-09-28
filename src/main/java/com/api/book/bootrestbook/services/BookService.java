package com.api.book.bootrestbook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.book.bootrestbook.entities.Book;

@Service
public class BookService {

    private static List<Book> list = new ArrayList<>();
    
        static
        {
            list.add(new Book(1,"Java","Harshita"));
            list.add(new Book(2,"Python","Shruti"));
            list.add(new Book(3,"C++","Akshat"));
        }

    public List<Book> getAllBooks()
    {
        return list;
    }

    public Book getBookById(int id)
    {
        Book book = null;
        try{
            book = list.stream().filter(e->e.getId()==id).findFirst().get();
        } catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }

    public Book addBook(Book book){
        list.add(book);
        return book;
    }

    public void deleteBook(int id) {
        list = list.stream().filter(book -> book.getId() != id).collect(Collectors.toList());
    }

    public void updateBookById(int id, Book book) {
        list.stream().map(b -> {
            if(b.getId() == id){
                b.setAuthor(book.getAuthor());
                b.setTitle(book.getTitle());
            }   
            return book;
        }).collect(Collectors.toList());
    } 
}

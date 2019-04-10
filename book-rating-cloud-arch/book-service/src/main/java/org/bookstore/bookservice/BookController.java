/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bookstore.bookservice;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        Book b1 = new Book();
        b1.setTitle("Goals");
        b1.setAuthor("Brain Tracy");
        b1.setPublisher("Orelly");

        Book b2 = new Book();
        b2.setTitle("Seven Habits");
        b2.setAuthor("Brain Tracy");
        b2.setPublisher("Orelly");
        
        bookRepository.save(b1);
        bookRepository.save(b2);

    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBook(Long id) {
        return bookRepository.getOne(id);
    }

    @PostMapping("/book/save")
    public Book save(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/book/delete/{id}")
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

}

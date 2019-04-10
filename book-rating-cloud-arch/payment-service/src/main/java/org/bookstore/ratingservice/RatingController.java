/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bookstore.ratingservice;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */
@RestController
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @PostConstruct
    public void init() {
        Rating b1 = new Rating();
        b1.setBookId(1L);
        b1.setStars(5);

        Rating b2 = new Rating();
        b2.setBookId(2L);
        b2.setStars(3);

        ratingRepository.save(b1);
        ratingRepository.save(b2);

    }

    @GetMapping("/ratings")
    public List<Rating> getBooks() {
        return ratingRepository.findAll();
    }

    @GetMapping("/rating/{id}")
    public Rating getBook(Long id) {
        return ratingRepository.getOne(id);
    }

    @PostMapping("/rating/save")
    public Rating save(@RequestBody Rating book) {
        return ratingRepository.save(book);
    }

    @GetMapping("/rating/delete/{id}")
    public void delete(Long id) {
        ratingRepository.deleteById(id);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bookstore.ratingservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author Dell
 */
@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue
    private Long id;
    private Long bookId;
    private int stars;

}

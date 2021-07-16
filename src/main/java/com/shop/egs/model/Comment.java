package com.shop.egs.model;

import com.shop.egs.enums.Rate;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private Rate rate;
    @OneToOne
    private User user;
    @ManyToOne
    private Product product;

}

package com.shop.egs.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private BigDecimal price;
    private String description;
    @OneToMany( mappedBy = "product")
    private Set<Comment> comments;
    @ManyToOne
    private Category category;
}

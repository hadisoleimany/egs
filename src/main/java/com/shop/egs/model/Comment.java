package com.shop.egs.model;

import com.shop.egs.enums.Rate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @Enumerated(EnumType.ORDINAL)
    private Rate rate;
    @OneToOne
    private User user;
    @ManyToOne
    private Product product;

}

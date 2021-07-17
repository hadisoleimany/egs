package com.shop.egs.dto;

import com.shop.egs.enums.Rate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private String comment;
    private Rate rate;
    private UserDto user;
    private ProductDto product;
}

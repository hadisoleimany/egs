package com.shop.egs.service;

import com.shop.egs.dto.CategoryDto;
import com.shop.egs.dto.CommentDto;
import com.shop.egs.dto.ProductDto;
import com.shop.egs.dto.UserDto;
import com.shop.egs.model.Comment;
import com.shop.egs.model.User;
import com.shop.egs.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository rp;
    private final ProductService productService;
    private final UserService userService;

    public CommentService(CommentRepository rp, ProductService productService, UserService userService) {
        this.rp = rp;
        this.productService = productService;
        this.userService = userService;
    }

    public CommentDto saveComment(CommentDto dto) {
        Comment comment = rp.save(convertToComment(dto));
        return convertToCommentDto(comment);
    }

    public List<CommentDto> getAllCommentsByProductName(String productName) {
        return convertCommentListToCommentDtoList(rp.findAllByProductProductNameContains(productName));
    }

    private Comment convertToComment(CommentDto dto) {
        Comment comment = new Comment();
        comment.setComment(dto.getComment());
        comment.setRate(dto.getRate());
        comment.setProduct(productService.findByProductName(dto.getProduct().getProductName()));
        Long userId = userService.getUserIdByUserDto(dto.getUser().getUserName(), dto.getUser().getName(), dto.getUser().getCode());
        comment.setUser(new User(userId));
        return comment;
    }
    private CommentDto convertToCommentDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setComment(comment.getComment());
        dto.setRate(comment.getRate());
        dto.setProduct(new ProductDto(new CategoryDto(comment.getProduct().getCategory().getName()),comment.getProduct().getProductName()
        ,comment.getProduct().getPrice(),comment.getProduct().getDescription()));
        dto.setUser(new UserDto(comment.getUser()));
        return dto;
    }

    private List<CommentDto> convertCommentListToCommentDtoList(List<Comment> comments) {
        if (comments == null || comments.isEmpty()) {
            return new ArrayList<>();
        }
        return comments.stream().map(c -> new CommentDto(c.getComment(), c.getRate(), new UserDto(c.getUser()),
                new ProductDto(new CategoryDto(c.getProduct().getCategory().getName()),
                        c.getProduct().getProductName(), c.getProduct().getPrice(), c.getProduct().getDescription()))).collect(Collectors.toList());
    }
}

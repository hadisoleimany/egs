package com.shop.egs.controller;

import com.shop.egs.dto.CommentDto;
import com.shop.egs.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CommentController.ROUTE)
public class CommentController {
    final static String ROUTE="/comment";
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public CommentDto saveComment(@RequestBody CommentDto commentDto){
        return service.saveComment(commentDto);
    }

    @GetMapping("/getallcommentbyproductname")
    public List<CommentDto>getAllCommentByProductName(@RequestParam(name = "productname") String productName){
        return service.getAllCommentsByProductName(productName);
    }
}

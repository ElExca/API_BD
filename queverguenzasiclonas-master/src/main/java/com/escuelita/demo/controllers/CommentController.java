package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.request.CreateCommentRequest;
import com.escuelita.demo.controllers.dtos.response.GetCommentResponse;
import com.escuelita.demo.servicies.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private ICommentService service;

    @PostMapping
    public GetCommentResponse create(@RequestBody CreateCommentRequest request){
        return service.create(request);

    }
    @GetMapping("{id}")
    public GetCommentResponse get(Long id){
        return service.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
    @GetMapping
    public List<GetCommentResponse> list(){
    return service.list();
    }
    }
}

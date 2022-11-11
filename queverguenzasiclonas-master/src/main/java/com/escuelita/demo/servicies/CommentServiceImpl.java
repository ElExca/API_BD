package com.escuelita.demo.servicies;

import com.escuelita.demo.controllers.dtos.request.CreateCommentRequest;
import com.escuelita.demo.controllers.dtos.response.CreateCommentResponse;
import com.escuelita.demo.controllers.dtos.response.GetCommentResponse;
import com.escuelita.demo.entities.Comment;
import com.escuelita.demo.repositories.IComentRepository;
import com.escuelita.demo.servicies.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private IComentRepository repository;

 @Override
    public GetCommentResponse get(Long id){
     return from(id);
 }
    @Override
    public GetCommentResponse create(CreateCommentRequest request){
     Comment comment = from(request);
    return from(repository.save(comment));
    }

    @Override
    public void delete(Long id){
     repository.deleteById(id);
    }

    @Override
    public List<GetCommentResponse> list(){
     return repository.findAll().stream().map(this::from).collect(Collectors.toList());
    }
    private Comment from(CreateCommentRequest request){
     Comment comment = new Comment();
     comment.setUser(request.getUser());
     comment.setContent(request.getContent());
     comment.setDate(request.getDate());
     return comment;
    }

    private GetCommentResponse from(Comment comment){
    GetCommentResponse response= new GetCommentResponse();
    response.setId(comment.getId());
    response.setUser(comment.getUser());
    response.setContent(comment.getContent());
    response.setDate(comment.getDate());
    return response;
    }

    private GetCommentResponse from(Long idComment){
     return repository.findById(idComment)
             .map(this::from)
             .orElseThrow(()->new RuntimeException("This comment does not exist"));
    }







}

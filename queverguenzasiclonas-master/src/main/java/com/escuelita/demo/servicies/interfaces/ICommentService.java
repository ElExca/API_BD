package com.escuelita.demo.servicies.interfaces;

import com.escuelita.demo.controllers.dtos.request.CreateCommentRequest;
import com.escuelita.demo.controllers.dtos.response.CreateCommentResponse;
import com.escuelita.demo.controllers.dtos.response.GetCommentResponse;

import java.util.List;

public interface ICommentService {

GetCommentResponse get(Long id);

GetCommentResponse create(CreateCommentRequest request);

void delete(Long id);

List<GetCommentResponse> list();
}

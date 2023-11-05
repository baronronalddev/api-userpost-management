package com.bancom.ms.api.services;

import com.bancom.ms.api.dto.MessageDTO;
import com.bancom.ms.api.dto.PostUserDTO;
import com.bancom.ms.api.dto.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserPostService {

     Flux<UserDTO> listUserPost();
     Flux<PostUserDTO> listPostUser();
     Mono<MessageDTO> createUser(UserDTO userDTO);
     Mono<MessageDTO> createPostUser(PostUserDTO postUserDTO, Long idUser);

     Mono<MessageDTO> updateUser(UserDTO userDTO, Long id);
     Mono<MessageDTO> updatePost(PostUserDTO postUserDTO, Long idPost);

     Mono<MessageDTO> deleteUser(Long idUser);
     Mono<MessageDTO> deletePost(Long idPost);
}

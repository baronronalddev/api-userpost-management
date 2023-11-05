package com.bancom.ms.api.controllers;

import com.bancom.ms.api.dto.MessageDTO;
import com.bancom.ms.api.dto.PostUserDTO;
import com.bancom.ms.api.dto.UserDTO;
import com.bancom.ms.api.services.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserPostController {

    @Autowired
    private UserPostService userPostService;

    @GetMapping("/user-post")
    public Flux<UserDTO> getListUserPost() {
        return userPostService.listUserPost();
    }

    @GetMapping("/post-user")
    public Flux<PostUserDTO> getListPostUser() {
        return userPostService.listPostUser();
    }

    @PostMapping("/user-post")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MessageDTO> createUserPost(@RequestBody UserDTO userDTO) {
        return userPostService.createUser(userDTO);
    }

    @PostMapping("/post-user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MessageDTO> createPostUser(@RequestBody PostUserDTO postUserDTO, @PathVariable Long id) {
        return userPostService.createPostUser(postUserDTO,id);
    }

    @PutMapping("/user-post/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MessageDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        return userPostService.updateUser(userDTO,id);
    }

    @PutMapping("/post-user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MessageDTO> updatePost(@RequestBody PostUserDTO postUserDTO, @PathVariable Long id) {
        return userPostService.updatePost(postUserDTO,id);
    }

    @DeleteMapping("/user-post/{id}")
    Mono<MessageDTO>  deleteUser(@PathVariable Long id) {
        return userPostService.deleteUser(id);
    }

    @DeleteMapping("/post-user/{id}")
    Mono<MessageDTO>  deletePost(@PathVariable Long id) {
        return userPostService.deletePost(id);
    }

}

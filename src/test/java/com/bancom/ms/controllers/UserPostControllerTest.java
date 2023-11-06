package com.bancom.ms.controllers;

import com.bancom.ms.api.dto.PostDTO;
import com.bancom.ms.api.dto.PostUserDTO;
import com.bancom.ms.api.dto.UserDTO;
import com.bancom.ms.api.services.UserPostService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserPostControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private UserPostService userPostService;

    @Test
    public void uriListUser() {
       List<UserDTO> mockUser = this.createListUser();
       Mockito.when(userPostService.listUserPost()).thenReturn(Flux.fromIterable(mockUser));
       WebTestClient.ResponseSpec responseSpec = webClient
                .get()
                .uri("http://localhost:9898/api/v1/user-post")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        responseSpec.expectBody()
        .jsonPath("$[0].id").isEqualTo(mockUser.get(0).getId());

    }

    @Test
    public void uriListPost() {
        List<PostUserDTO> mockPost = this.createListPostUserDTO();
        Mockito.when(userPostService.listPostUser()).thenReturn(Flux.fromIterable(mockPost));
        WebTestClient.ResponseSpec responseSpec = webClient
                .get()
                .uri("http://localhost:9898/api/v1/post-user")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
        responseSpec.expectBody()
        .jsonPath("$[0].id").isEqualTo(mockPost.get(0).getId());
    }

    @Test
    public void uriCreateUser() {
       WebTestClient.ResponseSpec responseSpec = webClient
                .post()
                .uri("http://localhost:9898/api/v1/user-post")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(this.createUser())
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void uriCreatePost() {
        WebTestClient.ResponseSpec responseSpec = webClient
                .post()
                .uri("http://localhost:9898/api/v1/post-user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(this.createPostDTO())
                .exchange()
                .expectStatus().isCreated();
    }


    @Test
    public void uriUpdateUser() {
        UserDTO userUpdateDTO = new UserDTO();
        userUpdateDTO.setId(1L);
        userUpdateDTO.setCellPhone("943266736");
        userUpdateDTO.setName("Farfan");
        userUpdateDTO.setLastName("Luis");
        userUpdateDTO.setPassword("123456782");

        WebTestClient.ResponseSpec  responseSpec = webClient
                .put()
                .uri("http://localhost:9898/api/v1/user-post/1")
                .bodyValue(userUpdateDTO)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void uriUpdatePost() {
        PostUserDTO postUserDTO = new PostUserDTO();
        postUserDTO.setId(1L);
        postUserDTO.setText("text");
        WebTestClient.ResponseSpec  responseSpec = webClient
                .put()
                .uri("http://localhost:9898/api/v1/post-user/1")
                .bodyValue(postUserDTO)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void uriDeleteUser() {
        WebTestClient.ResponseSpec  responseSpec = webClient
                .delete()
                .uri("http://localhost:9898/api/v1/user-post/1")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void uriDeletePost() {
        WebTestClient.ResponseSpec  responseSpec = webClient
                .delete()
                .uri("http://localhost:9898/api/v1/post-user/1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void uriDeleteUser2() {
        WebTestClient.ResponseSpec  responseSpec = webClient
                .delete()
                .uri("http://localhost:9898/api/v1/user-post/1")
                .exchange()
                .expectStatus().isNotFound();
    }





    public List<UserDTO> createListUser() {
        List<UserDTO> listUserDTO =  new ArrayList<>();
        listUserDTO.add(this.createUserDTO());
        return listUserDTO;
    }

    public UserDTO createUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setCellPhone("943266736");
        userDTO.setName("Farfan");
        userDTO.setLastName("Luis");
        userDTO.setPassword("Peru123.");
        userDTO.setPosts(this.createListPostDTO());
        return userDTO;
    }

    public UserDTO createUser() {
        Random random = new Random();
        UserDTO userDTO = new UserDTO();
        userDTO.setCellPhone("1234567890");
        userDTO.setName( String.valueOf(random.nextInt(100)));
        userDTO.setLastName(String.valueOf(random.nextInt(100)));
        userDTO.setPassword("12345678");
        return userDTO;
    }

    public List<PostDTO> createListPostDTO() {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(1L);
        postDTO.setText("mensaje para Doe");
        List<PostDTO> listPostDTO = new ArrayList<>();
        listPostDTO.add(postDTO);
        return listPostDTO;
    }

    public List<PostUserDTO> createListPostUserDTO() {
        PostUserDTO postDTO = new PostUserDTO();
        postDTO.setId(1L);
        postDTO.setText("mensaje para Doe");
        List<PostUserDTO> listPostDTO = new ArrayList<>();
        listPostDTO.add(postDTO);
        return listPostDTO;
    }

    public PostUserDTO createPostDTO() {
        PostUserDTO postDTO = new PostUserDTO();
        postDTO.setText("mensaje para Doe");
        return postDTO;
    }

}

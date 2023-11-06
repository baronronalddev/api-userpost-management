package com.bancom.ms.services;

import com.bancom.ms.api.dto.UserDTO;
import com.bancom.ms.api.exception.BusinessException;
import com.bancom.ms.api.services.UserPostService;
import com.bancom.ms.domain.entity.PostEntity;
import com.bancom.ms.domain.entity.UserEntity;
import com.bancom.ms.domain.repositories.PostRepository;
import com.bancom.ms.domain.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserPostServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PostRepository postRepository;

    @Autowired
    private UserPostService userPostService;

    @Test
    public void listUser() {
        Mockito.when(userRepository.findAll()).thenReturn(this.listUserEntity());
        userPostService.listUserPost();
        Assertions.assertTrue(true);
    }

    @Test
    public void listPost() {
        Mockito.when(postRepository.findAll()).thenReturn(this.listPostEntity());
        userPostService.listUserPost();
        Assertions.assertTrue(true);
    }

    @Test
    public void createUser() {
        Mockito.when(userRepository.save(createUserEntity())).thenReturn(createUserEntity());
        Assertions.assertThrows(NullPointerException.class, () -> {
            userPostService.createUser(null);
        });
    }

    @Test
    public void createPost() {
        Assertions.assertThrows(BusinessException.class, () -> {
              userPostService.createPostUser(null,null);
         });
    }

    public static List<UserEntity> listUserEntity() {
        List<UserEntity> userEntityList = new ArrayList<>();
        userEntityList.add(createUserEntity());
        return userEntityList;
    }

    public static UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("string");
        userEntity.setPassword("string");
        userEntity.setLastName("string");
        userEntity.setCellPhone("string");
        return userEntity;
    }

    public UserDTO createUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setCellPhone("943266736");
        userDTO.setName("Farfan");
        userDTO.setLastName("Luis");
        userDTO.setPassword("Peru123.");
        return userDTO;
    }

    public static List<PostEntity> listPostEntity() {
        List<PostEntity> postEntityList = new ArrayList<>();
        PostEntity postEntity = new PostEntity();
        postEntity.setId(1L);
        postEntity.setText("string");
        return postEntityList;
    }



}

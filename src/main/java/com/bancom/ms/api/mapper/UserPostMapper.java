package com.bancom.ms.api.mapper;

import com.bancom.ms.api.dto.PostDTO;
import com.bancom.ms.api.dto.UserDTO;
import com.bancom.ms.domain.entity.PostEntity;
import com.bancom.ms.domain.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserPostMapper {

    public UserDTO userEntityToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setCellPhone(userEntity.getCellPhone());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setPosts(postEntityToPostDTO(userEntity));
        return userDTO;
    }

    public UserEntity userDTOToUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setCellPhone(userDTO.getCellPhone());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setActive(true);
        userEntity.setCreatedAt(LocalDateTime.now());
        return userEntity;
    }

    public List<PostDTO> postEntityToPostDTO(UserEntity userEntity) {
        return userEntity.getPosts().stream()
                .map(postEntity -> {
                    PostDTO postDTO = new PostDTO();
                    postDTO.setId(postEntity.getId());
                    postDTO.setText(postEntity.getText());
                    return  postDTO;
                }).collect(Collectors.toList());
    }

}

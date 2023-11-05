package com.bancom.ms.api.mapper;

import com.bancom.ms.api.dto.PostDTO;
import com.bancom.ms.api.dto.PostUserDTO;
import com.bancom.ms.domain.entity.PostEntity;
import com.bancom.ms.domain.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class PostUserMapper {

    public PostUserDTO postEntityToPostUserDTO(PostEntity postEntity) {
        PostUserDTO postUserDTO = new PostUserDTO();
        postUserDTO.setId(postEntity.getId());
        postUserDTO.setText(postEntity.getText());
        postUserDTO.setUserId(postEntity.getUserEntity().getId());
        return postUserDTO;
    }

}

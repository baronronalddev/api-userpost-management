package com.bancom.ms.api.services.impl;

import com.bancom.ms.api.dto.MessageDTO;
import com.bancom.ms.api.dto.PostUserDTO;
import com.bancom.ms.api.dto.UserDTO;
import com.bancom.ms.api.exception.BusinessException;
import com.bancom.ms.api.mapper.PostUserMapper;
import com.bancom.ms.api.mapper.UserPostMapper;
import com.bancom.ms.api.services.UserPostService;
import com.bancom.ms.domain.entity.PostEntity;
import com.bancom.ms.domain.entity.UserEntity;
import com.bancom.ms.domain.enums.ErrorMessageEnum;
import com.bancom.ms.domain.repositories.PostRepository;
import com.bancom.ms.domain.repositories.UserRepository;

import com.bancom.ms.domain.util.UserPostUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class UserPostServiceImpl implements UserPostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserPostMapper userPostMapper;

    @Autowired
    private PostUserMapper postUserMapper;

    @Override
    public Flux<UserDTO> listUserPost() {
        return Flux.fromIterable(userRepository.findAll())
                   .map(userPostMapper::userEntityToUserDTO);
    }

    @Override
    public Flux<PostUserDTO> listPostUser() {
        return Flux.fromIterable(postRepository.findAll())
                   .map(postUserMapper::postEntityToPostUserDTO);
    }

    @Override
    public Mono<MessageDTO> createUser(UserDTO userDTO) {

        validParamsUserPostDTO(userDTO);

        if(userRepository.existsName(userDTO.getName()) && userRepository.existsLastName(userDTO.getLastName())) {
            throw new BusinessException(ErrorMessageEnum.MESSAGE_EXITS_USER);
        }

        UserEntity userEntity = userRepository.save(userPostMapper.userDTOToUserEntity(userDTO));

        return Mono.just(
                new MessageDTO(UserPostUtil.messageCreatedUser(userEntity.getId())));
    }

    @Override
    public Mono<MessageDTO> createPostUser(PostUserDTO postUserDTO, Long idUser) {

        Optional<UserEntity> userEntityOp = Optional.ofNullable(userRepository.findById(idUser)
                .orElseThrow(() -> new BusinessException(ErrorMessageEnum.MESSAGE_NOT_EXITS_USER)));


        PostEntity postEntity = new PostEntity();
        postEntity.setText(postUserDTO.getText());
        postEntity.setCreatedAt(LocalDateTime.now());

        UserEntity userEntity = userEntityOp.get();
        userEntity.setId(idUser);
        postEntity.setUserEntity(userEntity);

        postRepository.save(postEntity);

        return Mono.just(
                new MessageDTO(UserPostUtil.messageCreatedPost(postEntity.getId())));
    }

    @Override
    public Mono<MessageDTO> updateUser(UserDTO userDTO, Long id) {

        Optional<UserEntity> userEntityOp = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorMessageEnum.MESSAGE_NOT_EXITS_USER)));

        validParamsUserPostDTO(userDTO);

        Long idBd = userEntityOp.get().getId();
        Long idRequest = userDTO.getId();

        if(idBd != idRequest) {
            throw new BusinessException(ErrorMessageEnum.MESSAGE_ID_NOT_EQUALS);
        } else {
            userEntityOp.get().setId(userDTO.getId());
            userEntityOp.get().setCellPhone(userDTO.getCellPhone());
            userEntityOp.get().setName(userDTO.getName());
            userEntityOp.get().setLastName(userDTO.getLastName());
            userEntityOp.get().setPassword(userDTO.getPassword());
            userEntityOp.get().setUpdatedAt(LocalDateTime.now());
            userEntityOp.get().setActive(false);
            userRepository.save(userEntityOp.get());

            return Mono.just(
                    new MessageDTO(UserPostUtil.messageUpdateUser(userDTO.getId())));
        }
    }

    @Override
    public Mono<MessageDTO> updatePost(PostUserDTO postUserDTO, Long idPost) {

        Optional<PostEntity> postEntityOp = Optional.ofNullable(postRepository.findById(idPost)
                .orElseThrow(() -> new BusinessException(ErrorMessageEnum.MESSAGE_NOT_EXITS_POST)));

        Long idBd = postEntityOp.get().getId();
        Long idRequest = postUserDTO.getId();
        if(idBd != idRequest) {
            throw new BusinessException(ErrorMessageEnum.MESSAGE_ID_NOT_EQUALS);
        } else {
            postEntityOp.get().setId(postUserDTO.getId());
            postEntityOp.get().setText(postUserDTO.getText());
            postEntityOp.get().setUpdatedAt(LocalDateTime.now());
            postRepository.save(postEntityOp.get());

            return Mono.just(
                    new MessageDTO(UserPostUtil.messageUpdateUser(postUserDTO.getId())));
        }
    }

    @Override
    public Mono<MessageDTO> deleteUser(Long idUser) {

        Optional<UserEntity> userEntityOp = Optional.ofNullable(userRepository.findById(idUser)
                .orElseThrow(() -> new BusinessException(ErrorMessageEnum.MESSAGE_NOT_EXITS_USER)));

        userRepository.deleteById(userEntityOp.get().getId());

        return Mono.just(
                new MessageDTO(UserPostUtil.messageDeleteUser(userEntityOp.get().getId())));
    }

    @Override
    public Mono<MessageDTO> deletePost(Long idPost) {
        Optional<PostEntity> postEntityOp = Optional.ofNullable(postRepository.findById(idPost)
                .orElseThrow(() -> new BusinessException(ErrorMessageEnum.MESSAGE_NOT_EXITS_POST)));

        postRepository.deleteById(postEntityOp.get().getId());

        return Mono.just(
                new MessageDTO(UserPostUtil.messageDeletePost(postEntityOp.get().getId())));
    }

    public static void validParamsUserPostDTO(UserDTO userDTO) {
        if(StringUtils.isAllBlank(userDTO.getName())) {
            throw new BusinessException(ErrorMessageEnum.MESSAGE_FIELD_IS_REQUERID_NAME);
        } else if(StringUtils.isAllBlank(userDTO.getLastName())) {
            throw new BusinessException(ErrorMessageEnum.MESSAGE_FIELD_IS_REQUERID_LASTNAME);
        } else if(StringUtils.isAllBlank(userDTO.getPassword())) {
            throw new BusinessException(ErrorMessageEnum.MESSAGE_FIELD_IS_REQUERID_PASSWORD);
        }
    }

}

package com.bancom.ms.api.dto;

import com.bancom.ms.domain.entity.PostEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String cellPhone;
    private String name;
    private String lastName;
    private String password;
    private List<PostDTO> posts;

}

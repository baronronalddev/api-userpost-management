package com.bancom.ms.api.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostUserDTO {

    private Long id;
    private String text;
    private Long userId;

}

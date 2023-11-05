package com.bancom.ms.api.dto;

import com.bancom.ms.domain.enums.ErrorMessageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessageDTO {

    private Integer code;

    private String message;

    private Object data;

    public ResultMessageDTO(ErrorMessageEnum messageConstants, String message) {
        this.code = messageConstants.idMessage();
        this.message = message;
    }
}

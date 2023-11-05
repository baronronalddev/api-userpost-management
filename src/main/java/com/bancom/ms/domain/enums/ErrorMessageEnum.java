package com.bancom.ms.domain.enums;

import org.springframework.http.HttpStatus;

public enum ErrorMessageEnum {

    MESSAGE_FIELD_IS_REQUERID_NAME(1,"El campo (name) es obligatorio.", HttpStatus.BAD_REQUEST),
    MESSAGE_FIELD_IS_REQUERID_LASTNAME(1,"El campo (lastName) es obligatorio.", HttpStatus.BAD_REQUEST),
    MESSAGE_FIELD_IS_REQUERID_PASSWORD(1,"El campo (password) es obligatorio.", HttpStatus.BAD_REQUEST),

    MESSAGE_EXITS_USER(2,"El usuario ya existe.",HttpStatus.BAD_REQUEST),
    MESSAGE_NOT_EXITS_USER(3,"El usuario no existe.",HttpStatus.NOT_FOUND),
    MESSAGE_NOT_EXITS_POST(4,"El post no existe.",HttpStatus.NOT_FOUND),

    MESSAGE_ID_NOT_EQUALS(5, "Los id no son iguales.",HttpStatus.BAD_REQUEST);
    private Integer idMessage;
    private String codeMessage;
    private HttpStatus httpStatus;

    ErrorMessageEnum(Integer idMessage, String codeMessage, HttpStatus httpStatus) {
        this.idMessage = idMessage;
        this.codeMessage = codeMessage;
        this.httpStatus = httpStatus;
    }

    public static ErrorMessageEnum getById(Integer id) {
        ErrorMessageEnum value = null;
        for (ErrorMessageEnum e : values()) {
            if (e.idMessage.equals(id)) {
                value = e;
                break;
            }
        }
        return value;
    }

    public Integer idMessage() {
        return idMessage;
    }

    public String codeMessage() {
        return codeMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}

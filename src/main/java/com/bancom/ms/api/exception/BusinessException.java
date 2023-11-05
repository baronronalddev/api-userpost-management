package com.bancom.ms.api.exception;

import com.bancom.ms.domain.enums.ErrorMessageEnum;

import java.util.List;

public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private ErrorMessageEnum enumMessage;
    private List<String> messages;

    public BusinessException(ErrorMessageEnum enumMessage,List<String> messages) {
        this.enumMessage = enumMessage;
        this.messages = messages;
    }

    public BusinessException(ErrorMessageEnum enumMessage) {
        this.enumMessage = enumMessage;
    }

    public ErrorMessageEnum getEnumMessage() {
        return enumMessage;
    }

    public void setEnumMessage(ErrorMessageEnum enumMessage) {
        this.enumMessage = enumMessage;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}

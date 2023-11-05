package com.bancom.ms.api.exception;

import com.bancom.ms.api.dto.ResultMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private static final Integer ERROR_GENERIC_CODE = -1;

    @ExceptionHandler({ BusinessException.class })
    @ResponseBody
    public ResponseEntity<ResultMessageDTO> handleException(BusinessException objBusinessException ) {
        ResultMessageDTO resultMessageRs =  new ResultMessageDTO();
        resultMessageRs.setCode(objBusinessException.getEnumMessage().idMessage());
        resultMessageRs.setMessage(objBusinessException.getEnumMessage().codeMessage());
        return new ResponseEntity<>(resultMessageRs, objBusinessException.getEnumMessage().getHttpStatus());
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object handleException2(Exception e) {
        e.printStackTrace();
        ResultMessageDTO resultMessageRs =  new ResultMessageDTO();
        resultMessageRs.setCode(ERROR_GENERIC_CODE);
        resultMessageRs.setMessage(e.getMessage());
        return resultMessageRs;
    }
}

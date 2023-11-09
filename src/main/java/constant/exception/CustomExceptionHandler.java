package constant.exception;

import constant.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(GeneralException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.createError(e.getCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(GeneralException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.createError(e.getCode()));
    }
}


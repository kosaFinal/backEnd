package constant.exception;

import constant.enums.CustomResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GeneralException extends RuntimeException{

    CustomResponseCode code;
}

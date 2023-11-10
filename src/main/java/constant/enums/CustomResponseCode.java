package constant.enums;

import lombok.Getter;

@Getter
public enum CustomResponseCode {

    //성공(1000)
    SUCCESS(true,1000,"요청에 성공했습니다."),

    //bad request(2000)

    //unauthorized(권한 없을 때)(3000)

    //not found(4000)
    USER_NOT_FOUND(false,4000,"해당 유저를  찾을 수 없습니다.");

    //internal server error(기타 에러)(5000)


    private final boolean isSuccess;
    private final int code;
    private final String message;


    CustomResponseCode(boolean isSuccess,int code, String message) {
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }
}

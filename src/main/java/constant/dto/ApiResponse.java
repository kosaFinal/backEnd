package constant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import constant.enums.CustomResponseCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess","code","message","data"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private Boolean isSuccess;
    private int code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;



    public static ApiResponse<String> createSuccessWithNoContent(CustomResponseCode code){
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), null);
    }

    public static <T> ApiResponse<T> createSuccess(T data, CustomResponseCode code){
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), data);
    }

    public static ApiResponse<String> createError(CustomResponseCode code){
        return new ApiResponse<>(false, code.getCode(), code.getMessage(),null);
    }

    public ApiResponse(boolean isSuccess, int code, String message, T data) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

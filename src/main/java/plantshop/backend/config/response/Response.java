package plantshop.backend.config.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@AllArgsConstructor
@Getter
public class Response<T> {
    private Boolean isSuccess;
    private String code;
    private String message;
    private T data;

    public static Response success(SuccessResponseInfo successResponseInfo){
        return new Response(true, successResponseInfo.getCode(), successResponseInfo.getMessage(), null);
    }

    public static <T> Response<T> success(SuccessResponseInfo successResponseInfo, T data){
        return new Response<>(true, successResponseInfo.getCode(), successResponseInfo.getMessage(), data);
    }

    public static Response failure(FailureResponseInfo failureResponseInfo) {
        return new Response(false, failureResponseInfo.getCode(), failureResponseInfo.getMessage(), null);
    }

    public static Response failure(String code, String message) {
        return new Response(false, code, message, null);
    }
}

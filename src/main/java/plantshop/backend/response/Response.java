package plantshop.backend.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@AllArgsConstructor
@Getter
public class Response<T> {
    private Boolean isSuccess;
    private String code;
    private String message;
    private T data;

    public static Response success(SuccessInfo successInfo){
        return new Response(true, successInfo.getCode(), successInfo.getMessage(), null);
    }

    public static <T> Response<T> success(SuccessInfo successInfo, T data){
        return new Response<>(true, successInfo.getCode(), successInfo.getMessage(), data);
    }

    public static Response failure(FailureInfo failureInfo) {
        return new Response(false, failureInfo.getCode(), failureInfo.getMessage(), null);
    }

    public static Response failure(String code, String message) {
        return new Response(false, code, message, null);
    }
}

package plantshop.backend.domain.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import plantshop.backend.domain.post.dto.request.WritePostRequestDto;
import plantshop.backend.domain.post.service.PostService;
import plantshop.backend.response.BaseResponse;
import plantshop.backend.response.DataResponse;
import plantshop.backend.response.SuccessInfo;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
@Tag(name = "게시글 API", description = "게시글 API 문서입니다.")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 작성 API", description = "게시글 정보를 입력해주세요.")
    @PostMapping()
    public BaseResponse writePost(@Valid @RequestBody WritePostRequestDto writePostRequestDto){
        postService.writePost(writePostRequestDto);
        return new BaseResponse(SuccessInfo.WRITE_POST);
    }

    @Operation(summary = "게시글 목록 가져오기 API", description = "게시글 목록 가져오기.")
    @GetMapping()
    public DataResponse<List<GetPostListResponseDto>> getPostList(){
        return new DataResponse<>(SuccessInfo.GET_POST_LIST, postService.getPostList());
    }
}

package plantshop.backend.domain.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.post.entity.Post;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPostListResponseDto {
    @Schema(description = "글 아이디", defaultValue = "1")
    private Long id;
    @Schema(description = "글 제목", defaultValue = "안녕하세요.")
    private String title;
    @Schema(description = "작성자", defaultValue = "이우성")
    private String writer;

    public static GetPostListResponseDto from(Post post){
        return GetPostListResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .writer(post.getMember().getName())
                .build();
    }
}

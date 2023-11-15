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
public class GetPostResponseDto {
    @Schema(description = "게시글 아이디", defaultValue = "1")
    private Long id;
    @Schema(description = "작성자 이름", defaultValue = "이우성")
    private String writer;
    @Schema(description = "게시글 제목", defaultValue = "안녕하세요")
    private String title;
    @Schema(description = "게시글 내용", defaultValue = "반갑습니다.")
    private String content;

    public static GetPostResponseDto from(Post post) {
        return GetPostResponseDto.builder()
                .id(post.getId())
                .writer(post.getMember().getName())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}

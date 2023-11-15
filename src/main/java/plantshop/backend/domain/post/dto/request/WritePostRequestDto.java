package plantshop.backend.domain.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.post.entity.Post;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WritePostRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    @Schema(description = "글 제목", defaultValue = "안녕하세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    @Schema(description = "글 내용", defaultValue = "반갑습니다.")
    private String content;

    public Post toEntity(Member member){
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .member(member)
                .build();
    }
}

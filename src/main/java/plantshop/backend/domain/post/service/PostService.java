package plantshop.backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.member.service.MemberService;
import plantshop.backend.domain.post.dto.request.WritePostRequestDto;
import plantshop.backend.domain.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    public void writePost(WritePostRequestDto writePostRequestDto) {
        Member member = memberService.getCurrentMember();
        postRepository.save(writePostRequestDto.toEntity(member));
    }
}

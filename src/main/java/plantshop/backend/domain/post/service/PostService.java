package plantshop.backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.member.service.MemberService;
import plantshop.backend.domain.post.controller.GetPostListResponseDto;
import plantshop.backend.domain.post.dto.request.WritePostRequestDto;
import plantshop.backend.domain.post.entity.Post;
import plantshop.backend.domain.post.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    public void writePost(WritePostRequestDto writePostRequestDto) {
        Member member = memberService.getCurrentMember();
        postRepository.save(writePostRequestDto.toEntity(member));
    }

    @Transactional
    public List<GetPostListResponseDto> getPostList() {
        return postRepository.findAll()
                .stream()
                .map(GetPostListResponseDto::from)
                .collect(Collectors.toList());
    }
}

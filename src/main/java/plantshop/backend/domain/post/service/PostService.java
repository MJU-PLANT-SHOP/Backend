package plantshop.backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.member.service.MemberService;
import plantshop.backend.domain.post.dto.response.GetPostListResponseDto;
import plantshop.backend.domain.post.dto.request.WritePostRequestDto;
import plantshop.backend.domain.post.dto.response.GetPostResponseDto;
import plantshop.backend.domain.post.entity.Post;
import plantshop.backend.domain.post.repository.PostRepository;
import plantshop.backend.exception.GlobalException;
import plantshop.backend.response.FailureInfo;

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

    @Transactional
    public GetPostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new GlobalException(FailureInfo.NOT_EXISTENT_POST));
        return GetPostResponseDto.from(post);
    }
}

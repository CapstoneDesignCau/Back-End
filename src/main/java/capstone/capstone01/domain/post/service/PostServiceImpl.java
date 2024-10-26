package capstone.capstone01.domain.post.service;

import capstone.capstone01.domain.post.domain.Post;
import capstone.capstone01.domain.post.domain.repository.PostRepository;
import capstone.capstone01.domain.post.dto.request.PostCreateRequestDto;
import capstone.capstone01.domain.post.dto.response.PostResponseDto;
import capstone.capstone01.domain.post.dto.response.PostSummaryResponseDto;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.enums.UserRole;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specific.PostException;
import capstone.capstone01.global.exception.specific.UserException;
import capstone.capstone01.global.util.converter.PostConverter;
import capstone.capstone01.global.util.value.StaticValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Long createPost(String email, PostCreateRequestDto postCreateRequestDto) {
        User user = findUserByEmail(email);
        Post post = PostConverter.toPost(postCreateRequestDto, user);

        postRepository.save(post);
        return post.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto getPost(String email, Long id) {
        User user = findUserByEmail(email);
        Post post = findImagePostById(id);

        if (user.getRole() == UserRole.ADMIN || post.getIsOpen() || post.getWriter().getEmail().equals(email)) {
            return PostConverter.toPostResponseDto(post);
        } else {
            throw new PostException(ErrorStatus.POST_READ_NOT_ALLOWED);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostSummaryResponseDto> getTopPosts() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(StaticValue.RECENT_DAYS);
        Pageable pageable = PageRequest.of(0, StaticValue.TOP_POSTS_LIMIT);
        List<Post> topPosts = postRepository.findTopPostsByLikeCount(startDate, pageable);

        return topPosts.stream()
                .map(PostConverter::toPostSummaryResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostSummaryResponseDto> getPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAllByOrderByCreatedAtDesc(pageable);
        List<PostSummaryResponseDto> postSummaryResponseDtos = posts.stream()
                .map(PostConverter::toPostSummaryResponseDto)
                .collect(Collectors.toList());
        return new PageImpl<>(postSummaryResponseDtos, pageable, posts.getTotalElements());
    }


    @Override
    public void deletePost(String email, Long id) {
        User user = findUserByEmail(email);
        Post post = findImagePostById(id);

        if (user.getRole() == UserRole.ADMIN || post.getWriter().getEmail().equals(email)) {
            post.delete(true);
            postRepository.save(post);
        } else {
            throw new PostException(ErrorStatus.POST_DELETE_NOT_ALLOWED);
        }
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
    }

    private Post findImagePostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostException(ErrorStatus.POST_NOT_FOUND));
    }
}
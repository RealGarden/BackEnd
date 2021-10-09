package com.example.backend.service;

import com.example.backend.domain.member.MemberRepository;
import com.example.backend.entity.member.Member;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {

    public static final String NOT_FOUND_MESSAGE = "유저를 찾을수 없습니다.";
    public static final String EMAIL_DUPLICATE_MESSAGE = "중복된 이메일입니다.";

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public Member findById(final Long idx) {
        return memberRepository.findById(idx).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));
    }

    @Transactional(readOnly = true)
    public Member findByEmail(final String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));
    }

//    @Transactional(readOnly = true)
//    public MemberResponse findUserResponseById(final Long id) {
//        return MemberResponse.from(findById(id));
//    }

    public Member save(final UserRequest userRequest) {
        try {
            checkEmailDuplicate(userRequest.getEmail());
            return memberRepository.save(userRequest.toEntity());
        } catch (Exception e) {
            throw new SignUpException(e.getMessage());
        }
    }

    private void checkEmailDuplicate(String email) { //이메일 중복
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException(EMAIL_DUPLICATE_MESSAGE);
        }
    }

    public void delete(final Long userId, final Long sessionUserId) { //유저 삭제
        matchId(userId, sessionUserId);
        try {
            memberRepository.deleteById(userId);
        } catch (Exception e) {
            throw new MemberDeleteException();
        }
    }

    private void matchId(final Long userId, final Long sessionUserId) {
        if (userId == null || !userId.equals(sessionUserId)) {
            throw new UserMismatchException();
        }
    }

    public Page<UserResponse> findByName(final String name, final Pageable pageable) {
        return memberRepository.findAllByNameIsContaining(name, pageable)
                .map(UserResponse::from);
    }

    public List<UserResponse> findAllUsersWithoutCurrentUser(final Long id) {
        return memberRepository.findAll().stream()
                .filter(user -> !user.matchId(id))
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    public List<UserResponse> findUserResponseOfFriendsById(final Long id) {
        return memberRepository.findFriendsByUserId(id).stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    public boolean existsById(final Long id) {
        return memberRepository.existsById(id);
    }
}
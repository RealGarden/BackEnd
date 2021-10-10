package com.example.backend.service.member;

import com.example.backend.domain.member.MemberRepository;
import com.example.backend.domain.member.MemberRequestDto;
import com.example.backend.domain.member.MemberResponseDto;
import com.example.backend.entity.member.Member;
import com.example.backend.exception.member.SignUpException;
import com.example.backend.exception.member.MemberMismatchException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {

    public static final String NOT_FOUND_MESSAGE = "유저를 찾을수 없습니다.";
    public static final String EMAIL_DUPLICATE_MESSAGE = "중복된 이메일입니다.";

    private MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member findById(final String id) {
        return memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));
    }

    @Transactional
    public Member findByEmail(final String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));
    }

    @Transactional //수정필요
    public MemberResponseDto findUserResponseById(final String id) {
        return MemberResponseDto.from(findById(id));
    }

    public Member save(final MemberRequestDto memberRequestDto) { //멤버만드는거같은뎅
        try {
            checkEmailDuplicate(memberRequestDto.getEmail());
            return memberRepository.save(memberRequestDto.toEntity());
        } catch (Exception e) {
            throw new SignUpException(e.getMessage());
        }
    }

    private void checkEmailDuplicate(String email) { //이메일 중복
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException(EMAIL_DUPLICATE_MESSAGE);
        }
    }

//    public void delete(final Long userId, final Long sessionUserId) { //유저 삭제
//        matchId(userId, sessionUserId);
//        try {
//            memberRepository.deleteById(userId);
//        } catch (Exception e) {
//            throw new MemberDeleteException();
//        }
//    }

    private void matchId(final Long userId, final Long sessionUserId) {
        if (userId == null || !userId.equals(sessionUserId)) {
            throw new MemberMismatchException();
        }
    }


    public List<MemberResponseDto> findAllUsersWithoutCurrentUser(final Long id) {
        return memberRepository.findAll().stream()
                .filter(user -> !user.matchId(id))
                .map(MemberResponseDto::from)
                .collect(Collectors.toList());
    }

    public List<MemberResponseDto> findUserResponseOfFriendsById(final String id) {
        return memberRepository.findFriendsByUserId(id).stream()
                .map(MemberResponseDto::from)
                .collect(Collectors.toList());
    }

    public boolean existsById(final Long id) {
        return memberRepository.existsById(id);
    }
}
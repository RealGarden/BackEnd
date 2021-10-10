package com.example.backend.service.member;

import com.example.backend.exception.member.MemberMismatchException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class IntroductionService {


    private static final String NOT_FOUND_USER_MESSAGE = "유저를 찾을 수 없습니다.";
    private static final String NULL_USER_MESSAGE = "사용자 ID가 Null입니다.";

    private final IntroductionRepository introductionRepository;
    private final MemberService memberService;

    public IntroductionService(final IntroductionRepository introductionRepository, final UserService userService) {
        this.introductionRepository = introductionRepository;
        this.MemberService = memberService;
    }

    public Introduction save(Long userId) {
        checkUserExistence(userId);
        return introductionRepository.save(Introduction.builder().userId(userId).build());
    }

    @Transactional(readOnly = true)
    public Introduction findByUserId(Long userId) {
        return introductionRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_INTRODUCTION_MESSAGE));
    }

    public IntroductionResponse findIntroductionResponseByUserId(Long userId) {
        return IntroductionResponse.from(findByUserId(userId));
    }

    public IntroductionResponse update(IntroductionRequest introductionRequest, Long sessionUserId) {
        checkSessionUserId(introductionRequest.getUserId(), sessionUserId);
        checkUserExistence(sessionUserId);
        Introduction introduction = findByUserId(sessionUserId);
        introduction.update(introductionRequest.toEntity());
        return IntroductionResponse.from(introduction);
    }

    public void delete(Long userId) {
        Introduction introduction = findByUserId(userId);
        introductionRepository.delete(introduction);
    }

    private void checkSessionUserId(Long introductionUserId, Long sessionUserId) {
        if (introductionUserId == null || sessionUserId == null) {
            throw new MemberMismatchException(NULL_USER_MESSAGE);
        }
        if (!introductionUserId.equals(sessionUserId)) {
            throw new MemberMismatchException(MISMATCH_USER_MESSAGE);
        }
    }

    private void checkUserExistence(Long userId) {
        if (!userService.existsById(userId)) {
            throw new EntityNotFoundException(NOT_FOUND_USER_MESSAGE);
        }
    }
}
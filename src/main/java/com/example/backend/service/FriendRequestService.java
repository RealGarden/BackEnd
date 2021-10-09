package com.example.backend.service;

import com.example.backend.domain.friend.FriendRequestCreate;
import com.example.backend.domain.friend.FriendRequestRepository;
import com.example.backend.domain.friend.FriendRequestResponse;
import com.example.backend.entity.member.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service
@Transactional
public class FriendRequestService {

    public static final String ALREADY_FRIEND_ASK_EXIST_MESSAGE = "이미 친구 요청을 보냈습니다.";
    public static final String ALREADY_OTHER_FRIEND_ASK_EXIST_MESSAGE = "상대방이 친구 요청을 보냈습니다.";
    public static final String NOT_FOUND_FRIEND_ASK_MESSAGE = "친구 요청을 찾을 수 없습니다.";
    public static final String MISMATCHED_USER_MESSAGE = "유저가 일치하지 않습니다.";

    private final FriendRequestRepository friendRequestRepository;
    private final MemberService memberService;

    public FriendRequestService(final FriendRequestRepository friendRequestRepository, final MemberService memberService) {
        this.friendRequestRepository = friendRequestRepository;
        this.memberService = memberService;
    }

    public FriendRequestService() {
    }

    public FriendRequestResponse save(final Long senderId, final FriendRequestCreate friendAskCreate) {
        Member sender = memberService.findById(senderId);
        Member receiver = memberService.findById(friendAskCreate.getReceiverId());

        checkFriendAskExist(senderId, friendAskCreate.getReceiverId(), ALREADY_FRIEND_ASK_EXIST_MESSAGE);
        checkFriendAskExist(friendAskCreate.getReceiverId(), senderId, ALREADY_OTHER_FRIEND_ASK_EXIST_MESSAGE);

        FriendAsk friendAsk = friendRequestRepository.save(friendAskCreate.toEntity(senderId));
        return FriendAskResponse.from(friendAsk, sender, receiver);
    }

    private void checkFriendAskExist(final Long senderId, final Long receiverId, final String message) {
        if (friendRequestRepository.findBySenderIdAndReceiverId(senderId, receiverId).isPresent()) {
            throw new FriendAskFailException(message);
        }
    }

    @Transactional(readOnly = true)
    public List<FriendAskResponse> findAllByReceiverId(final Long id) {
        return friendRequestRepository.findAllByReceiverId(id).stream()
                .map(friendAsk -> FriendAskResponse.from(friendAsk,
                        memberService.findById(friendAsk.getSenderId()),
                        memberService.findById(friendAsk.getReceiverId())))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FriendAsk findById(final Long friendAskId) {
        return friendRequestRepository.findById(friendAskId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_FRIEND_ASK_MESSAGE));
    }

    public void delete(final FriendAsk friendAsk) {
        friendRequestRepository.delete(friendAsk);
    }

    public void deleteById(final Long friendAskId, final Long userSessionId) {
        FriendAsk friendAsk = findById(friendAskId);
        checkUserId(friendAsk, userSessionId);
        friendRequestRepository.delete(friendAsk);
    }

    private void checkUserId(final FriendAsk friendAsk, final Long userSessionId) {
        if (!friendAsk.matchUserId(userSessionId)) {
            throw new MismatchedUserException(MISMATCHED_USER_MESSAGE);
        }
    }

    public void deleteBySenderIdOrReceiverId(final Long userId) {
        friendRequestRepository.deleteBySenderIdOrReceiverId(userId, userId);
    }
}
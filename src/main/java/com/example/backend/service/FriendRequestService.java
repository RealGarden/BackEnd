package com.example.backend.service;

import com.example.backend.domain.friend.FriendRequestCreate;
import com.example.backend.domain.friend.FriendRequestRepository;
import com.example.backend.domain.friend.FriendRequestResponse;
import com.example.backend.entity.friend.FriendRequest;
import com.example.backend.entity.member.Member;
import com.example.backend.exception.friend.FriendAskFailException;
import com.example.backend.service.member.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FriendRequestService {

    public static final String ALREADY_FRIEND_ASK_EXIST_MESSAGE = "이미 친구 요청을 보냈습니다.";
    public static final String ALREADY_OTHER_FRIEND_ASK_EXIST_MESSAGE = "상대방이 친구 요청을 보냈습니다.";
    public static final String NOT_FOUND_FRIEND_ASK_MESSAGE = "친구 요청을 찾을 수 없습니다.";
    public static final String MISMATCHED_USER_MESSAGE = "유저가 일치하지 않습니다.";

    private FriendRequestRepository friendRequestRepository;
    private MemberService memberService;

    public FriendRequestService(final FriendRequestRepository friendRequestRepository, final MemberService memberService) {
        this.friendRequestRepository = friendRequestRepository;
        this.memberService = memberService;
    }


    public FriendRequestResponse save(final String senderId, final FriendRequestCreate friendRequestCreate) {
        Member sender = memberService.findById(senderId);
        Member receiver = memberService.findById(friendRequestCreate.getReceiver().getId());

        checkFriendAskExist(senderId, friendRequestCreate.getReceiver().getMemberIdx(), ALREADY_FRIEND_ASK_EXIST_MESSAGE);
        checkFriendAskExist(friendRequestCreate.getReceiver().getMemberIdx(), senderId, ALREADY_OTHER_FRIEND_ASK_EXIST_MESSAGE);

        FriendRequest friendRequest = friendRequestRepository.save(friendRequestCreate.toEntity(sender));
        return FriendRequestResponse.from(friendRequest, sender, receiver);
    }

    private void checkFriendAskExist(final Long senderId, final Long receiverId, final String message) {
        if (friendRequestRepository.findBySenderIdAndReceiverId(senderId, receiverId).isPresent()) {
            throw new FriendAskFailException(message);
        }
    }

    @Transactional(readOnly = true)
    public List<FriendRequestResponse> findAllByReceiverId(final Long id) {
        return friendRequestRepository.findAllByReceiverId(id).stream()
                .map(friendRequest -> FriendRequestResponse.from(friendRequest,
                        memberService.findById(friendRequest.getSenderId().getMemberIdx()),
                        memberService.findById(friendRequest.getReceiverId().getMemberIdx())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FriendRequest findById(final Long friendRequestId) {
        return friendRequestRepository.findById(friendRequestId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_FRIEND_ASK_MESSAGE));
    }

    public void delete(final FriendRequest friendRequest) {
        friendRequestRepository.delete(friendRequest);
    }

    public void deleteById(final Long friendRequestId, final Long userSessionId) {
        FriendRequest friendRequest = findById(friendRequestId);
        checkUserId(friendRequest, userSessionId);
        friendRequestRepository.delete(friendRequest);
    }

    private void checkUserId(final FriendRequest friendRequest, final Long userSessionId) {
        if (!friendRequest.matchUserId(userSessionId)) {
            throw new MismatchedUserException(MISMATCHED_USER_MESSAGE);
        }
    }

    public void deleteBySenderIdOrReceiverId(final Long userId) {
        friendRequestRepository.deleteBySenderIdOrReceiverId(userId, userId);
    }
}
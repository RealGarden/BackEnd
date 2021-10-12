package com.example.backend.service;

import com.example.backend.domain.friend.FriendRelationCreate;
import com.example.backend.domain.friend.FriendRelationResponse;
import com.example.backend.domain.friend.FriendRepository;
import com.example.backend.entity.friend.FriendRelationship;
import com.example.backend.entity.friend.FriendRequest;
import com.example.backend.entity.member.Member;
import com.example.backend.exception.friend.AlreadyFriendException;
import com.example.backend.exception.friend.MismatchedMemberException;
import com.example.backend.service.member.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FriendRelationService {

    public static final String NOT_FOUND_FRIEND_MESSAGE = "친구를 찾을 수 없습니다.";
    public static final String MISMATCHED_USER_MESSAGE = "유저가 일치하지 않습니다.";
    public static final String ALREADY_FRIEND_MESSAGE = "이미 친구입니다.";

    private  FriendRequestService friendRequestService;
    private MemberService memberService;
    private  FriendRepository friendRepository;

    public FriendRelationService(final FriendRepository friendRepository, final FriendRequestService friendRequestService,
                         final MemberService memberService) {
        this.friendRepository = friendRepository;
        this.friendRequestService = friendRequestService;
        this.memberService = memberService;
    }

    public List<FriendRelationship> save(final FriendRelationCreate friendCreate) {
        FriendRequest friendRequest = friendRequestService.findById(friendCreate.getFriendRelationId());

        checkAlreadyFriend(friendRequest.getSenderId().getMemberIdx(), friendRequest.getReceiverId().getMemberIdx());

        friendRequestService.delete(friendRequest);
        return friendRepository.saveAll(friendRequest.createBidirectionalFriends());
    }

    @Transactional(readOnly = true)
    public List<FriendRelationResponse> findAllFriendResponseByRelatingUserId(final Long id) {
        return friendRepository.findAllByUserId(id).stream()
                .map(friend -> FriendRelationResponse.from(friend, memberService.findById(friend.getFriendId().getMemberIdx())))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FriendRelationship findById(final Long id) {
        return friendRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_FRIEND_MESSAGE));
    }

    public void deleteById(final Long friendId, final Long memberSessionId) {
        FriendRelationship friend = friendRepository.findById(friendId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_FRIEND_MESSAGE));
        FriendRelationship reverseFriend = friendRepository
                .findByUserIdAndFriendId(friend.getUserId().getMemberIdx(), friend.getFriendId().getMemberIdx())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_FRIEND_MESSAGE));

        checkUserId(memberSessionId, friend, reverseFriend);

        friendRepository.delete(friend);
        friendRepository.delete(reverseFriend);
    }

    private void checkUserId(final Long memberSessionId, final FriendRelationship friend, final FriendRelationship reverseFriend) {
        if (!(friend.matchUserId(memberSessionId) && reverseFriend.matchFriendId(memberSessionId))) {
            throw new MismatchedMemberException(MISMATCHED_USER_MESSAGE);
        }
    }

    public void checkAlreadyFriend(final Long relatingId, final Long relatedId) {
        if (isAlreadyFriend(relatingId, relatedId)) {
            throw new AlreadyFriendException(ALREADY_FRIEND_MESSAGE);
        }
    }

    public boolean isAlreadyFriend(Long relatingId, Long relatedId) {
        return friendRepository.findByUserIdAndFriendId(relatingId, relatedId).isPresent();
    }

    public void deleteByRelatedUserIdOrRelatingUserId(final Long userId) {
        friendRepository.deleteByFriendIdOrUserId(userId, userId);
    }
}
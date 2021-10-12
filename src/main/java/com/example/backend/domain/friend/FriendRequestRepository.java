package com.example.backend.domain.friend;

import com.example.backend.entity.friend.FriendRequest;
import com.example.backend.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {
    List<FriendRequest>findAllByReceiverId(Long receiverId);
    Optional<FriendRequest> fiendAllByReceiverId(Long receiverId);

    void deleteBySenderIdOrReceiverId(Long senderId,Long receiverId);

    Optional<FriendRequest> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}

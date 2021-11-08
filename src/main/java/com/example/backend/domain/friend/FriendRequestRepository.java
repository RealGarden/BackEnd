package com.example.backend.domain.friend;

import com.example.backend.entity.friend.FriendRequest;
import com.example.backend.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {
    List<FriendRequest>findAllByReceiverId(Long receiverId);


    void deleteBySenderIdOrReceiverId(Long senderId,Long receiverId);

    Optional<FriendRequest> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}

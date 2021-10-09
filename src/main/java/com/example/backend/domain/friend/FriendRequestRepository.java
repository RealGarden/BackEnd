package com.example.backend.domain;

import com.example.backend.entity.FriendRequest;
import com.example.backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {
    List<FriendRequest>findAllByReceiverId(Member receiverId);
    Optional<FriendRequest> fiendAllByReceiverId(Member receiverId);

    void deleteBySenderIdOrReceiverId(Member senderId,Member receiverId);

}

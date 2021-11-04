package com.example.backend.domain.friend;

import com.example.backend.entity.friend.FriendRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<FriendRelationship, Long> {

    List<FriendRelationship> findAllByUserId(Long userId);

    Optional<FriendRelationship> findByUserIdAndFriendId(Long userId, Long friendId);

    void deleteByFriendIdOrUserId(Long friendId, Long userIds);


}
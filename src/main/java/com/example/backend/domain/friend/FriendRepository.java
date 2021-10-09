package com.example.backend.domain.friend;

import com.example.backend.entity.friend.FriendRelationship;
import com.example.backend.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<FriendRelationship, Long> {

    List<FriendRelationship> findAllByUserId(Member userId);

    Optional<FriendRelationship> findByUserIdAndFriendId(Member userId, Member friendId);

    void deleteByUserIdOrFriendId(Member friendId, Member userIds);


}
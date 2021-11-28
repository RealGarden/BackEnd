package com.example.backend.domain.friend;

import com.example.backend.entity.friend.FriendRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<FriendRelationship, Long> {

    List<FriendRelationship> findAllByUserId(String userId);

    Optional<FriendRelationship> findByUserIdAndFriendId(String userId, String friendId);

    void deleteByFriendIdOrUserId(Long friendId, Long userId);

    @Query("select f.friendId from FriendRelationship f where f.friendId = :friendId")
    Optional<FriendRelationship> findByUserId(@Param("friendId")String friendId);
}
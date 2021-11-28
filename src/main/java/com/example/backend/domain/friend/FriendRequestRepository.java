package com.example.backend.domain.friend;

import com.example.backend.entity.friend.FriendRequest;
import com.example.backend.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {
    List<FriendRequest>findAllByReceiverId(String receiverId);


    void deleteBySenderIdOrReceiverId(String senderId,String receiverId);

    Optional<FriendRequest> findBySenderIdAndReceiverId(String senderId, String receiverId);

    @Query("select f.receiverId from FriendRequest f where f.receiverId = :userId")
    Optional<FriendRequest> findByUserId(@Param("userId")String friendRequestId);
}

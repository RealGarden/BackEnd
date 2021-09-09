package com.example.backend.domain;

import com.example.backend.entity.FriendRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<FriendRelationship, Long> {

}
package com.example.backend.controller.friend;

import com.example.backend.domain.friend.FriendRelationCreate;
import com.example.backend.domain.friend.FriendRelationResponse;
import com.example.backend.domain.friend.FriendRequestCreate;
import com.example.backend.domain.friend.FriendRequestResponse;
import com.example.backend.service.friend.FriendRelationService;
import com.example.backend.service.friend.FriendRequestService;
import com.example.backend.service.member.LoginMember;
import com.example.backend.entity.member.MemberSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendApiController {

    private final FriendRequestService friendRequestService;
    private final FriendRelationService friendRelationService;

    public FriendApiController(final FriendRequestService friendRequestService, final FriendRelationService friendRelationService) {
        this.friendRequestService = friendRequestService;
        this.friendRelationService = friendRelationService;
    }

    @GetMapping("/requests")
    public ResponseEntity<List<FriendRequestResponse>> listRequest(@LoginMember MemberSession memberSession) {
        return ResponseEntity.ok(friendRequestService.findAllByReceiverId(memberSession.getId()));
    }

    @PostMapping("/requests")
    public ResponseEntity<FriendRequestResponse> createRequest(@RequestBody FriendRequestCreate friendRequestCreate,
                                                           @LoginMember MemberSession memberSession) {
        friendRelationService.checkAlreadyFriend(friendRequestCreate.getReceiver().getId(), memberSession.getId());
        return ResponseEntity.created(null)
                .body(friendRequestService.save(memberSession.getId(), friendRequestCreate));
    }

    @DeleteMapping("/requests/{id}")
    public ResponseEntity deleteRequest(@PathVariable String id, @LoginMember MemberSession memberSession) {
        friendRequestService.deleteById(id, memberSession.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FriendRelationResponse>> list(@LoginMember MemberSession memberSession) {
        return ResponseEntity.ok(friendRelationService.findAllFriendResponseByRelatingUserId(memberSession.getId()));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody FriendRelationCreate friendCreate) {
        friendRelationService.save(friendCreate);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id, @LoginMember MemberSession memberSession) {
        friendRelationService.deleteById(id, memberSession.getId());
        return ResponseEntity.noContent().build();
    }
}
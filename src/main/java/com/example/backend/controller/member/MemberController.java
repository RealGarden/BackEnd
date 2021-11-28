package com.example.backend.controller.member;

import com.example.backend.domain.member.MemberRepository;
import com.example.backend.domain.member.MemberRequestDto;
import com.example.backend.domain.member.MemberResponseDto;
import com.example.backend.domain.member.MyPageResponse;
import com.example.backend.service.member.LoginMember;
import com.example.backend.service.member.MemberCreateService;
import com.example.backend.service.member.MemberService;
import com.example.backend.entity.member.MemberSession;
import com.example.backend.service.member.MyPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final MemberCreateService memberCreateService;
    private final MemberRepository memberRepository;
    private final MyPageService myPageService;

    public MemberController(final MemberService memberService,
                            final MemberCreateService memberCreateService,
                            final MemberRepository memberRepository,
                            final MyPageService myPageService
                            ) {
        this.memberService = memberService;
        this.memberCreateService=memberCreateService;
        this.memberRepository=memberRepository;
        this.myPageService = myPageService;

    }

    @GetMapping("/signup")
    public String signup() {
        return "/api/signup";
    }


    @PostMapping("/signup")
    public ResponseEntity<MemberRequestDto> signup(
            @Valid @RequestBody MemberRequestDto memberRequestDto
            ) {
       MemberResponseDto memberResponseDto=memberCreateService.create(memberRequestDto);
        URI uri = linkTo(MemberController.class).slash(memberResponseDto.getId()).toUri();
       return ResponseEntity.created(uri).body(memberRequestDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> show(@PathVariable String id) {
        return ResponseEntity.ok(memberService.findUserResponseById(id));

    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> list(@LoginMember MemberSession memberSession) {
        return ResponseEntity.ok(memberService.findAllUsersWithoutCurrentUser(memberSession.getId()));
    }

    @GetMapping("/{id}/mypage")
    public ResponseEntity<MyPageResponse> myPage(@PathVariable String id) {
        return ResponseEntity.ok(myPageService.findUserResponseById(id));
    }

}

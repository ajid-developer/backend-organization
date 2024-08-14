package com.bvk.test.backend_organization.controllers;

import com.bvk.test.backend_organization.dtos.responses.ResponseData;
import com.bvk.test.backend_organization.entities.Member;
import com.bvk.test.backend_organization.services.FileService;
import com.bvk.test.backend_organization.services.MemberService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final FileService fileService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseData<Member>> saveMember(
            @RequestParam("name") @NotBlank String name,
            @RequestParam("position") @NotBlank String position,
            @RequestParam("report") @NotBlank String report,
            @RequestParam("image") MultipartFile image
    ) throws IOException {

        String fileName = fileService.saveImage(image);
        Member member = Member.builder()
                .name(name)
                .position(position)
                .report(report)
                .image(fileName)
                .build();

        Member memberSaved = memberService.saveMember(member);

        return ResponseEntity.ok(ResponseData.<Member>builder()
                .message("Successfully")
                .data(memberSaved)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Member>> getMember(@PathVariable("id") Long id) {
        Member member = memberService.findMemberById(id).orElseThrow();
        return ResponseEntity.ok(ResponseData.<Member>builder()
                .message("Successfully")
                .data(member)
                .build());
    }
}

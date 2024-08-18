package com.bvk.test.backend_organization.controllers;

import com.bvk.test.backend_organization.dtos.responses.PageResponse;
import com.bvk.test.backend_organization.dtos.responses.ResponseData;
import com.bvk.test.backend_organization.entities.Member;
import com.bvk.test.backend_organization.enums.ResponseCode;
import com.bvk.test.backend_organization.services.FileService;
import com.bvk.test.backend_organization.services.MemberService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMessage())
                .data(memberSaved)
                .build());
    }

    @GetMapping
    public ResponseEntity<ResponseData<PageResponse<Member>>> getMembers(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam("page") int page) {

        Pageable pageable = PageRequest.of(page - 1, 10);
        return ResponseEntity.ok(ResponseData.<PageResponse<Member>>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMessage())
                .data(memberService.findAllMemberContainsName(search, pageable))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Member>> getMember(@PathVariable("id") Long id) {
        Member member = memberService.findMemberById(id).orElseThrow();
        return ResponseEntity.ok(ResponseData.<Member>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMessage())
                .data(member)
                .build());
    }
}

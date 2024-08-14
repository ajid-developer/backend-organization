package com.bvk.test.backend_organization.services;

import com.bvk.test.backend_organization.entities.Member;
import com.bvk.test.backend_organization.repositories.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }
}

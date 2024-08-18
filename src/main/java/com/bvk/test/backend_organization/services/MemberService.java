package com.bvk.test.backend_organization.services;

import com.bvk.test.backend_organization.dtos.responses.PageResponse;
import com.bvk.test.backend_organization.entities.Member;
import com.bvk.test.backend_organization.repositories.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    public PageResponse<Member> findAllMemberContainsName(String name, Pageable pageable) {

        Page<Member> memberPage = memberRepository.findByMemberSearch(name, pageable);
        return PageResponse.<Member>builder()
                .content(memberPage.getContent())
                .total(memberPage.getTotalElements())
                .build();
    }
}

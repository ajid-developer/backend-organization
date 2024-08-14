package com.bvk.test.backend_organization.repositories;

import com.bvk.test.backend_organization.entities.Member;
import com.bvk.test.backend_organization.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}

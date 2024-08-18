package com.bvk.test.backend_organization.repositories;

import com.bvk.test.backend_organization.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT *  FROM members WHERE name ILIKE %:name% OR position ILIKE %:name% OR position ILIKE %:name% OR report ILIKE %:name%",
            countQuery = "SELECT COUNT(*)  FROM members WHERE name ILIKE %:name% OR position ILIKE %:name% OR report ILIKE %:name%",
            nativeQuery = true)
    Page<Member> findByMemberSearch(@Param("name") String name, Pageable pageable);
}

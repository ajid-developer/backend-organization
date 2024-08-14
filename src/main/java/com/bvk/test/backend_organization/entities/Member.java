package com.bvk.test.backend_organization.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_id_seq")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String report;
    @Column(nullable = false)
    private String image;

    public String getImage() {
        return "path/" + image;
    }
}

package com.bvk.test.backend_organization.dtos.responses;

import com.bvk.test.backend_organization.entities.Member;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageResponse <T> {
    private List<T> content;
    private Long total;
}

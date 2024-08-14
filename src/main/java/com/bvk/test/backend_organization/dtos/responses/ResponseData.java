package com.bvk.test.backend_organization.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseData <T>{
    private String message;
    private T data;
}

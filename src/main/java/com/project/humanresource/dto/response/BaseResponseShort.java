package com.project.humanresource.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseResponseShort<T> {
    String message;
    Integer code;
    T data;
}

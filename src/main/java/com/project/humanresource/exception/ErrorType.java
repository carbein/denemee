package com.project.humanresource.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorType {


    BADREQUEST(4002, "The provided parameters are invalid.", BAD_REQUEST),
    PASSWORD_MISMATCH(4003, "The entered passwords do not match.", BAD_REQUEST),
    EMAIL_PASSWORD_ERROR(4004, "Incorrect username or password.", BAD_REQUEST),
    CATEGORY_ALREADY_EXISTS(4005, "This category is already registered.", BAD_REQUEST),
    INVALID_TOKEN(4999, "Invalid token information.", FORBIDDEN),
    USER_NOT_FOUND(2004, "User not found.", INTERNAL_SERVER_ERROR),
    INTERNAL_SERVER(5000, "An unexpected error occurred on the server.", INTERNAL_SERVER_ERROR),
    EMPLOYEE_NOT_FOUND(5001, "Employee not found.", BAD_REQUEST);


    int code;
    String message;
    HttpStatus httpStatus;

}

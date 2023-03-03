package com.vk.controller;

import com.vk.constants.StatusCode;
import com.vk.module.EligibilityRequest;
import com.vk.module.EligibilityResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@ControllerAdvice
@Slf4j
@Setter
public class ControllerAdvisor implements RequestBodyAdvice {

    private @Valid @NotNull @Size(min = 1, max = 10) String eligibilityRequest;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> errorObjects = ex.getBindingResult().getFieldErrors();
        List<StatusCode> errors = new ArrayList<>();

        errorObjects.forEach(i -> {
            StatusCode statusCode = StatusCode.INVALID_FIELD_VALUE;
            if (i.getRejectedValue() == null) {
                statusCode = StatusCode.MANDATORY_FIELD_MISSING;
                log.error(getLogMessage(statusCode.getLogMessage(), i));
            } else {
                log.error(getLogMessage(statusCode.getLogMessage(), i));
            }
            errors.add(statusCode);

        });
        Collections.sort(errors);

        ResponseEntity<Object> errorMessage;
        try {
            errorMessage = generateException(errors, HttpStatus.BAD_REQUEST);
        } catch (NullPointerException nullEx) {
            errorMessage = generateException(errors, HttpStatus.BAD_REQUEST);
        }
        return errorMessage;
    }

    public ResponseEntity<Object> generateException(List<StatusCode> errors, HttpStatus status) {
        var responseHeader = new EligibilityResponse();
        return generateException(errors, status, responseHeader);
    }
    public ResponseEntity<Object> generateException(List<StatusCode> errors, HttpStatus status, EligibilityResponse eligibilityResponse) {
        List<Map<String, String>> errorList = new ArrayList<>();
        errors.forEach(error -> errorList.add(error.getJsonError()));
        Map<String, Object> errorResponse = new LinkedHashMap<>();
        eligibilityResponse.setErrors(errorList);
        errorResponse.put("responseHeader", eligibilityResponse);
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_PROBLEM_JSON).body(errorResponse);
    }

    public EligibilityResponse generateResponseHeader(EligibilityResponse request) {
        var responseHeader = new EligibilityResponse();
        responseHeader.setRequestId(request.getRequestId());

        return responseHeader;
    }

    public String getLogMessage(String statusCodeLogMessage, FieldError fieldError) {
        StringBuilder logMessage = new StringBuilder(statusCodeLogMessage);
        logMessage.append(" - ");
        logMessage.append(fieldError.getField());
        logMessage.append(": ");
        logMessage.append(fieldError.getRejectedValue());
        return logMessage.toString();
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        if (body instanceof EligibilityRequest eligibilityRequest) {
            this.eligibilityRequest = eligibilityRequest.getRequestId();
        }
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }
}

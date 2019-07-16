package com.gearstation.gateway.resource.exception;

import com.gearstation.gateway.resource.exception.exception.TechnicalMessage;
import com.gearstation.gateway.resource.model.vo.ExceptionResponseVo;
import com.gearstation.common.exception.category.TechnicalExceptionCategory;
import com.gearstation.common.exception.exception.FunctionalException;
import com.gearstation.common.exception.exception.RemoteException;
import com.gearstation.common.exception.exception.TechnicalException;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.springframework.http.HttpStatus.*;

/**
 * Description: Exception interceptor <br>
 * Copyright © 2019 www.gear-station.com <br>
 * CreateTime: 2019-07-16 23:27 <br>
 *
 * @author packy <br>
 * @version 1.0.1 <br>
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ControllerAdvice
public class ExceptionInterceptor {
//
//    private final ExceptionBuilder exceptionBuilder;
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> methodArgumentNotValidException(final MethodArgumentNotValidException exception) {
//        final BindingResult result = exception.getBindingResult();
//        Map<String, String> fieldErrorsMap = result.getFieldErrors()
//                .stream()
//                .filter(fieldError -> isNotBlank(fieldError.getDefaultMessage()))
//                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
//        return new ResponseEntity<>(fieldErrorsMap, new HttpHeaders(), BAD_REQUEST);
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException exception) {
//        return new ResponseEntity<>("Not Allowed", new HttpHeaders(), METHOD_NOT_ALLOWED);
//    }
//
//    @ExceptionHandler(TechnicalException.class)
//    public ResponseEntity<Object> handleTechnicalException(final TechnicalException technicalException) {
//        TechnicalExceptionResponseBuilder technicalExceptionResponseBuilder =
//                Try.of(() -> TechnicalExceptionResponseBuilder.valueOf(technicalException.getTechnicalExceptionCategory().name())).getOrElse(() -> TechnicalExceptionResponseBuilder.DEFAULT_EXCEPTION);
//        return technicalExceptionResponseBuilder.buildFromTechnicalException.apply(technicalException);
//    }
//
//    @ExceptionHandler(FunctionalException.class)
//    public ResponseEntity<Object> handleFunctionalException(final FunctionalException functionalException) {
//        FunctionalExceptionResponseBuilder functionalExceptionResponseBuilder =
//                Try.of(() -> FunctionalExceptionResponseBuilder.valueOf(functionalException.getFunctionalExceptionCategory().name())).getOrElse(() -> FunctionalExceptionResponseBuilder.DEFAULT_EXCEPTION);
//        return functionalExceptionResponseBuilder.buildFromFunctionalException.apply(functionalException);
//    }
//
//    @ExceptionHandler(RemoteException.class)
//    public ResponseEntity<Object> handleRemoteException(final RemoteException remoteException) {
//        RemoteExceptionResponseBuilder remoteExceptionResponseBuilder =
//                Try.of(() -> RemoteExceptionResponseBuilder.valueOf(remoteException.getRemoteExceptionCategory().name())).getOrElse(() -> RemoteExceptionResponseBuilder.DEFAULT_EXCEPTION);
//        return remoteExceptionResponseBuilder.buildFromRemoteException.apply(remoteException);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleException(final Exception exception) {
//        return TechnicalExceptionResponseBuilder.DEFAULT_EXCEPTION
//                .buildFromTechnicalException.apply(
//                        exceptionBuilder.buildTechnicalException(TechnicalExceptionCategory.INTERNAL_SERVER_EXCEPTION,
//                                TechnicalMessage.INTERNAL_SERVER_ERROR, exception));
//    }
//
//    private static ResponseEntity<Object> buildFunctionalExceptionResponse(
//            final HttpStatus httpStatus,
//            final FunctionalException functionalException) {
//        return buildResponse(httpStatus, functionalException.getErrorCode(), functionalException.getErrorMessage(), functionalException);
//    }
//
//    private static ResponseEntity<Object> buildTechnicalExceptionResponse(
//            final HttpStatus httpStatus,
//            final TechnicalException technicalException) {
//        return buildResponse(httpStatus, technicalException.getErrorCode(), technicalException.getErrorMessage(), technicalException);
//    }
//
//    private static ResponseEntity<Object> buildRemoteExceptionResponse(
//            final RemoteException remoteException) {
//        return buildResponse(remoteException.getHttpStatus(), remoteException.getErrorCode(), remoteException.getErrorMessage(), remoteException);
//    }
//
//    private static ResponseEntity<Object> buildResponse(
//            final HttpStatus httpStatus,
//            final String errorCode,
//            final String errorMessage,
//            final Throwable rootCause) {
//        rootCause.printStackTrace();
//        ExceptionResponseVo exceptionResponse = ExceptionResponseVo
//                .builder()
//                .errorCode(errorCode)
//                .errorMessage(errorMessage)
//                .rootCause(rootCause)
//                .build();
//        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), httpStatus);
//    }
//
//    @AllArgsConstructor
//    @Getter
//    private enum TechnicalExceptionResponseBuilder {
//        HANDLE_FEIGN_EXCEPTION_FAILED_EXCEPTION(technicalException -> buildTechnicalExceptionResponse(INTERNAL_SERVER_ERROR, technicalException)),
//        INTERNAL_SERVER_EXCEPTION(technicalException -> buildTechnicalExceptionResponse(INTERNAL_SERVER_ERROR, technicalException)),
//        DEFAULT_EXCEPTION(technicalException -> buildTechnicalExceptionResponse(INTERNAL_SERVER_ERROR, technicalException));
//        private Function<TechnicalException, ResponseEntity<Object>> buildFromTechnicalException;
//    }
//
//    @AllArgsConstructor
//    @Getter
//    private enum FunctionalExceptionResponseBuilder {
//        LOGIC_VALIDATION_EXCEPTION(functionalException -> buildFunctionalExceptionResponse(BAD_REQUEST, functionalException)),
//        ENTITY_NOT_FOUND_EXCEPTION(functionalException -> buildFunctionalExceptionResponse(NOT_FOUND, functionalException)),
//        METHOD_NOT_ALLOWED_EXCEPTION(functionalException -> buildFunctionalExceptionResponse(METHOD_NOT_ALLOWED, functionalException)),
//        BAD_REQUEST_EXCEPTION(functionalException -> buildFunctionalExceptionResponse(BAD_REQUEST, functionalException)),
//        DEFAULT_EXCEPTION(functionalException -> buildFunctionalExceptionResponse(INTERNAL_SERVER_ERROR, functionalException));
//        private Function<FunctionalException, ResponseEntity<Object>> buildFromFunctionalException;
//    }
//
//    @AllArgsConstructor
//    @Getter
//    private enum RemoteExceptionResponseBuilder {
//        FEIGN_CLIENT_INVOCATION_EXCEPTION(ExceptionInterceptor::buildRemoteExceptionResponse),
//        DEFAULT_EXCEPTION(ExceptionInterceptor::buildRemoteExceptionResponse);
//        private Function<RemoteException, ResponseEntity<Object>> buildFromRemoteException;
//    }
}
package com.gearstation.gateway.resource.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gearstation.common.exception.category.RemoteExceptionCategory;
import com.gearstation.common.exception.category.TechnicalExceptionCategory;
import com.gearstation.common.exception.message.RemoteMessage;
import com.gearstation.gateway.resource.exception.ExceptionBuilder;
import com.gearstation.gateway.resource.exception.exception.TechnicalMessage;
import com.gearstation.gateway.resource.model.vo.ExceptionResponseVo;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Description: Feign client exception decoder <br>
 * Copyright © 2019 www.gear-station.com <br>
 * CreateTime: 2019-07-16 23:27 <br>
 *
 * @author packy <br>
 * @version 1.0.1 <br>
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeignExceptionDecoder implements ErrorDecoder {

    final ExceptionBuilder exceptionBuilder;

    @Override
    public Exception decode(final String methodKey, final Response response) {
        ExceptionResponseVo exceptionResponseVo = Try.of(() -> new ObjectMapper().readValue(Util.toString(response.body().asReader()), ExceptionResponseVo.class))
                .getOrElseThrow(() -> exceptionBuilder.buildTechnicalException(TechnicalExceptionCategory.HANDLE_FEIGN_EXCEPTION_FAILED_EXCEPTION,
                        TechnicalMessage.HANDLE_FEIGN_EXCEPTION_FAILED));
        RemoteMessage feignInvocationMessage = RemoteMessage.builder()
                .httpStatus(HttpStatus.valueOf(response.status()))
                .errorCode(exceptionResponseVo.getErrorCode())
                .errorMessage(exceptionResponseVo.getErrorMessage())
                .build();
        return exceptionBuilder.buildRemoteException(RemoteExceptionCategory.FEIGN_CLIENT_INVOCATION_EXCEPTION, feignInvocationMessage);
    }
}

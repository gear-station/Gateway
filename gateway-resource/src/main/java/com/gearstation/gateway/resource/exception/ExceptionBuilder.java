package com.gearstation.gateway.resource.exception;

import com.gearstation.common.exception.category.FunctionalExceptionCategory;
import com.gearstation.common.exception.category.RemoteExceptionCategory;
import com.gearstation.common.exception.category.TechnicalExceptionCategory;
import com.gearstation.common.exception.exception.FunctionalException;
import com.gearstation.common.exception.exception.RemoteException;
import com.gearstation.common.exception.exception.TechnicalException;
import com.gearstation.common.exception.message.CommonMessage;
import com.gearstation.common.exception.message.RemoteMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Description: Exception builder <br>
 * Copyright © 2019 www.gear-station.com <br>
 * CreateTime: 2019-07-16 23:27 <br>
 *
 * @author packy <br>
 * @version 1.0.1 <br>
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionBuilder {

    private final MessageSource messageSource;

    public TechnicalException buildTechnicalException(final TechnicalExceptionCategory technicalExceptionCategory,
                                                      final CommonMessage commonMessage,
                                                      final Object... parameters) {
        return buildTechnicalException(technicalExceptionCategory, commonMessage, null, parameters);
    }

    public TechnicalException buildTechnicalException(final TechnicalExceptionCategory technicalExceptionCategory,
                                                      final CommonMessage commonMessage,
                                                      final Throwable rootCause,
                                                      final Object... parameters) {
        return TechnicalException.builder()
                .technicalExceptionCategory(technicalExceptionCategory)
                .errorCode(getI18nCode(commonMessage))
                .errorMessage(getI18nMessage(commonMessage, parameters))
                .rootCause(rootCause)
                .build();
    }

    public FunctionalException buildFunctionalException(final FunctionalExceptionCategory functionalExceptionCategory,
                                                        final CommonMessage commonMessage,
                                                        final Object... parameters) {
        return buildFunctionalException(functionalExceptionCategory, commonMessage, null, parameters);
    }

    public FunctionalException buildFunctionalException(final FunctionalExceptionCategory functionalExceptionCategory,
                                                        final CommonMessage commonMessage,
                                                        final Throwable rootCause,
                                                        final Object... parameters) {
        return FunctionalException.builder()
                .functionalExceptionCategory(functionalExceptionCategory)
                .errorCode(getI18nCode(commonMessage))
                .errorMessage(getI18nMessage(commonMessage, parameters))
                .rootCause(rootCause)
                .build();
    }

    public RemoteException buildRemoteException(final RemoteExceptionCategory remoteExceptionCategory,
                                                final RemoteMessage remoteMessage) {
        return buildRemoteException(remoteExceptionCategory, remoteMessage, null);
    }

    public RemoteException buildRemoteException(final RemoteExceptionCategory remoteExceptionCategory,
                                                final RemoteMessage remoteMessage,
                                                final Throwable rootCause) {
        return RemoteException.builder()
                .remoteExceptionCategory(remoteExceptionCategory)
                .httpStatus(remoteMessage.getHttpStatus())
                .errorCode(remoteMessage.getErrorCode())
                .errorMessage(remoteMessage.getErrorMessage())
                .rootCause(rootCause)
                .build();
    }

    private String getI18nCode(final CommonMessage commonMessage, final Object... parameters) {
        return messageSource.getMessage(commonMessage.getErrorCodeKey(), parameters, LocaleContextHolder.getLocale());
    }

    private String getI18nMessage(final CommonMessage commonMessage, final Object... parameters) {
        return messageSource.getMessage(commonMessage.getErrorMessageKey(), parameters, LocaleContextHolder.getLocale());
    }

}

package com.gearstation.gateway.resource.exception.exception;

import com.gearstation.common.exception.message.CommonMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: Technical exception message <br>
 * Copyright © 2019 www.gear-station.com <br>
 * CreateTime: 2019-07-16 23:27 <br>
 *
 * @author packy <br>
 * @version 1.0.1 <br>
 */
@AllArgsConstructor
@Getter
public enum TechnicalMessage implements CommonMessage {
    INTERNAL_SERVER_ERROR("com.gearstation.gateway.technical.internal.server.error.code",
            "com.gearstation.gateway.technical.internal.server.error.msg"),
    HANDLE_FEIGN_EXCEPTION_FAILED("com.gearstation.gateway.technical.handle.feign.exception.failed.error.code",
            "com.gearstation.gateway.technical.handle.feign.exception.failed.error.msg");
    private String errorCodeKey;
    private String errorMessageKey;
}

package com.gearstation.gateway.resource.exception.exception;

import com.gearstation.common.exception.message.CommonMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: Functional exception message <br>
 * Copyright © 2019 www.gear-station.com <br>
 * CreateTime: 2019-07-16 23:27 <br>
 *
 * @author packy <br>
 * @version 1.0.1 <br>
 */
@AllArgsConstructor
@Getter
public enum FunctionalMessage implements CommonMessage {
    DUMMY_ERROR("",
            "");
    private String errorCodeKey;
    private String errorMessageKey;
}

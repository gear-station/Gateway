package com.gearstation.gateway.resource.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: Exception response entity <br>
 * Copyright © 2019 www.gear-station.com <br>
 * CreateTime: 2019-07-16 23:27 <br>
 *
 * @author packy <br>
 * @version 1.0.1 <br>
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder()
public class ExceptionResponseVo {
    private String errorCode;
    private String errorMessage;
    @JsonIgnore
    private Throwable rootCause;
}

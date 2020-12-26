package com.gearstation.gateway.resource.config.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Description:  <br>
 * Copyright © 2019 www.gear-station.com <br>
 * CreateTime: 2019-08-06 05:55 <br>
 *
 * @author packy <br>
 * @version 1.0.1 <br>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class  AuthRequest {
    private String username;
    private String password;
}



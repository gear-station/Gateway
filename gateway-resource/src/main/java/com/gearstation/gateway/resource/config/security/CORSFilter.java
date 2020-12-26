package com.gearstation.gateway.resource.config.security;

import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * Description: CORSFilter <br>
 * Copyright © 2019 www.gear-station.com <br>
 * CreateTime: 2019-08-06 07:00 <br>
 *
 * @author packy <br>
 * @version 1.0.1 <br>
 */
public class CORSFilter implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
    }
}

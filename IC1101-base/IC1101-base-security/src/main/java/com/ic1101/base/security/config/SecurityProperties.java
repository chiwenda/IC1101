package com.ic1101.base.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cwd
 * @date 6/9/22 6:20 PM
 */
@ConfigurationProperties(prefix = "ic1101.security")
@Data
public class SecurityProperties {

    private String tokenHeader;


}

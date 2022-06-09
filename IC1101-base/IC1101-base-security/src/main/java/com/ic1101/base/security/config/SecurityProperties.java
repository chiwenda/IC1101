package com.ic1101.base.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author cwd
 * @date 6/9/22 6:20 PM
 */
@ConfigurationProperties(prefix = "ic1101.security")
@Data
public class SecurityProperties {
}

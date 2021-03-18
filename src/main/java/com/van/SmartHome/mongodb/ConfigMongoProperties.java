package com.van.SmartHome.mongodb;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 *
 */
@Data
@ConfigurationProperties("config.mongo")
public class ConfigMongoProperties {
    private String host = "47.98.170.158";
    private int port = 27017;
    private String db = "base_config";
    private String table = "V01";
    private String username = "config";
    private String password = "config";
    private String name;

}

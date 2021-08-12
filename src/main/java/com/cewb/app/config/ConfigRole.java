package com.cewb.app.config;

import org.springframework.stereotype.Component;

@Component("R")
abstract public class ConfigRole {

    /**
     * Roles
     */
    public final static String ROLE_ADMIN = "ADMIN";

    public final static String ROLE_EDITOR = "USER";

    public final static String ROLE_USER = "USER";

}

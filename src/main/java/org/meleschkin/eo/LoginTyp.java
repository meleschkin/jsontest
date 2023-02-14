package org.meleschkin.eo;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LoginTyp {
    UNPW("unpw", "1"),

    UNPW_OKTA("unpw_okta", "1"),

    MAGIC("magic", "5"),

    URL("url", null),

    WEBLOGON("weblogon", "2"),

    ADMIN("admin", null),

    SINGLE_SIGN_ON("sso", "1"),

    USER("user", "1"),

    ADFS("adfs", "1"),

    OKTA("okta", "1"),

    LOGIN_ORG_DRITTE("dritte", "1");

    final String val;

    final String zugangsweg;

    LoginTyp(String val, String zw) {
        this.val = val;
        this.zugangsweg = zw;
    }

    @JsonValue
    public String val() {
        return val;
    }

    public String zugangsweg() {
        return zugangsweg;
    }
}

package org.meleschkin.eo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Login {
    private LoginTyp typ;
    private String user;
    private String password;
}

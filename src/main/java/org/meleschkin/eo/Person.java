package org.meleschkin.eo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.LocalDate;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Person implements Serializable {

    private String vorname;
    private String name;
    private LocalDate gebdatum;
}

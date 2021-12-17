package org.meleschkin.eo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.LocalDate;

@Getter
@Setter
@ToString
public class Person {

    private String vorname;
    private String name;
    private LocalDate gebdatum;
}

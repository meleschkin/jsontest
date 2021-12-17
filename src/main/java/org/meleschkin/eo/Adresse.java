package org.meleschkin.eo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Adresse {

    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;
}

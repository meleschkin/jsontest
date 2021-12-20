package org.meleschkin.eo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Adresse implements Serializable {

    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;
}

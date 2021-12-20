package org.meleschkin.eo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Familie implements Serializable {

    private Adresse adr;
    private List<Person> personen = new ArrayList<>();
}

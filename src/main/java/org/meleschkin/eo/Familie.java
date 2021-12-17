package org.meleschkin.eo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Familie {

    Adresse adr;
    List<Person> personen = new ArrayList<>();
}

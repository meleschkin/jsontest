package org.meleschkin.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class Configuration {

    private String mongoClientURI;
    private String database;
    private String collection;
    private String mydatabase;
    private String mycollection;
}

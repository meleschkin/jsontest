package org.meleschkin.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.joda.time.LocalDate;
import org.meleschkin.eo.Adresse;
import org.meleschkin.eo.Familie;
import org.meleschkin.eo.Person;

import java.io.ByteArrayOutputStream;

@Log4j2
public class JacksonTest {

    public static void main(String[] args) {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);
        try {
            Familie meleschkin = getFamilie();
            log.info(meleschkin.toString());
            log.info(jsonFamilie(meleschkin));
            log.info(yamlFamilie(meleschkin));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static Familie getFamilie() {
        Person andrei = new Person();
        andrei.setVorname("Andrei");
        andrei.setName("Meleschkin");
        LocalDate ga = new LocalDate(1962, 5, 23);
        andrei.setGebdatum(ga);
        Person ramona = new Person();
        ramona.setVorname("Ramona");
        ramona.setName("Meleschkin");
        LocalDate gr = new LocalDate(1963, 4, 11);
        ramona.setGebdatum(gr);
        Adresse adr = new Adresse();
        adr.setStrasse("Pletschmühlenweg");
        adr.setHausnummer("128");
        adr.setPlz("50259");
        adr.setOrt("Pulheim");
        Familie meleschkin = new Familie();
        meleschkin.setAdr(adr);
        meleschkin.getPersonen().add(andrei);
        meleschkin.getPersonen().add(ramona);
        return meleschkin;
    }

    @SneakyThrows
    public static String jsonFamilie(Familie familie) {
        if (familie == null) {
            return "";
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(familie);
    }

    @SneakyThrows
    public static String yamlFamilie(Familie familie) {
        if (familie == null) {
            return "";
        }
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String yaml;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            mapper.writeValue(bos, familie);
            yaml = bos.toString();
        }
        return yaml;
    }

}

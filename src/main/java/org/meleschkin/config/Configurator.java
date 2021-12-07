package org.meleschkin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.BasicConfigurator;

import java.io.File;

@Log4j
public class Configurator {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        try {
            Configuration config = readConfigFile();
            log.info(config.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @SneakyThrows
    public static Configuration readConfigFile() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return mapper.readValue(new File("src/main/resources/config.yml"), Configuration.class);
    }
}

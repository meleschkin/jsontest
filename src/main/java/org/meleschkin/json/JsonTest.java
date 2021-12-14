package org.meleschkin.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import java.io.BufferedReader;
import java.io.FileReader;

@Log4j2
public class JsonTest {

    public static void main(String[] args) {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);
        try {
            String input = readFile("src/main/resources/test.json");
            log.info(input);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(input);
            log.info("GK-Wert: " + jsonNode.get("analysisResult").get("gk").asText());
            log.info("Ort: " + jsonNode.get("reportParameter").get("pointLocationParameters").get("cityName").asText());
            log.info("RefNum: " + jsonNode.get("refNum").asText());
            double test = 10.481928082;
            String sTest = "Test=" + test;
            log.info(sTest);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @SneakyThrows
    private static String readFile(String file) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        }
    }
}

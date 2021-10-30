package org.meleschkin.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonTest {
    private static final Logger LOG = LogManager.getLogger(JsonTest.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        try {
            String input = readFile("src/main/resources/test.json");
            LOG.info(input);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(input);
            LOG.info("GK-Wert: " + jsonNode.get("analysisResult").get("gk").asText());
            LOG.info("Ort: " + jsonNode.get("reportParameter").get("pointLocationParameters").get("cityName").asText());
            LOG.info("RefNum: " + jsonNode.get("refNum").asText());
            double test = 10.481928082;
            String sTest = "Test=" + test;
            LOG.info(sTest);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
}

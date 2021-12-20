package org.meleschkin.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoNamespace;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.bson.Document;
import org.meleschkin.config.Configuration;
import org.meleschkin.config.MyConfigurator;
import org.meleschkin.eo.Familie;
import org.meleschkin.json.JacksonTest;

import java.util.Base64;

@Log4j2
public class MongoCRUDTest {

    public static void main(String[] args) {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);
        try {
            Configuration config = MyConfigurator.readConfigFile();
            log.info(config.toString());
            byte[] clientBytes = Base64.getDecoder().decode(config.getMongoClientURI());
            String clientURI = new String(clientBytes);
            log.info(clientURI);
            MongoClientURI mongoClientURI = new MongoClientURI(clientURI);
            try (MongoClient mongoClient = new MongoClient(mongoClientURI)) {
                MongoDatabase database = mongoClient.getDatabase(config.getMydatabase());
                log.info(database.getName());
                MongoCollection<Document> collection = database.getCollection(config.getMycollection());
                MongoNamespace mn = collection.getNamespace();
                log.info(mn.toString());
                Familie meleschkin = JacksonTest.getFamilie();
                log.info(meleschkin.toString());
                String json = JacksonTest.jsonFamilieSimple(meleschkin);
                log.info(json);
                Document doc = Document.parse(json);
                collection.insertOne(doc);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

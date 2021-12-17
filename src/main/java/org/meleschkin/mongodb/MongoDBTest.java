package org.meleschkin.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.MongoNamespace;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.meleschkin.config.Configuration;
import org.meleschkin.config.MyConfigurator;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Log4j2
public class MongoDBTest {

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
            MongoCredential mcu = mongoClientURI.getCredentials();
            if (mcu != null) {
                log.info(mcu.toString());
                log.info(mcu.getUserName());
                log.info(mcu.getPassword());
            }
            try (MongoClient mongoClient = new MongoClient(mongoClientURI)) {
                MongoCredential mc = mongoClient.getCredential();
                if (mc != null) {
                    log.info(mc.toString());
                    log.info(mc.getUserName());
                    log.info(mc.getPassword());
                }
                log.info("Databases");
                List<String> databases = mongoClient.listDatabaseNames().into(new ArrayList<>());
                for (String db : databases) {
                    log.info(db);
                }
                MongoDatabase database = mongoClient.getDatabase(config.getDatabase());
                log.info(database.getName());
                log.info("Collections in " + database.getName());
                List<String> collections = database.listCollectionNames().into(new ArrayList<>());
                for (String col : collections) {
                    log.info(col);
                }
                MongoCollection<Document> collection = database.getCollection(config.getCollection());
                MongoNamespace mn = collection.getNamespace();
                log.info(mn.toString());
                log.info("Count: " + collection.countDocuments());
                Document doc = collection.find().first();
                if (doc != null) {
                    JsonWriterSettings.Builder builder = JsonWriterSettings.builder().indent(true);
                    String json = doc.toJson(builder.build());
                    log.info(json);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

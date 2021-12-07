package org.meleschkin.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.MongoNamespace;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.BasicConfigurator;
import org.bson.Document;

@Log4j
public class MongoDBTest {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        try {
            MongoClientURI mongoClientURI = new MongoClientURI("mongodb+srv://meleschkin:sesamavm123@cluster0.pjfca.mongodb.net");
            MongoCredential mcu = mongoClientURI.getCredentials();
            if (mcu != null) {
                log.info(mcu.toString());
                log.info(mcu.getUserName());
                log.info(mcu.getPassword());
            }
            MongoClient mongoClient = new MongoClient(mongoClientURI);
            MongoCredential mc = mongoClient.getCredential();
            if (mc != null) {
                log.info(mc.toString());
                log.info(mc.getUserName());
                log.info(mc.getPassword());
            }
            MongoDatabase database = mongoClient.getDatabase("sample_analytics");
            log.info(database.getName());
            MongoCollection<Document> collection = database.getCollection("customers");
            MongoNamespace mn = collection.getNamespace();
            log.info(mn.toString());
            log.info("Count: " + collection.countDocuments());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

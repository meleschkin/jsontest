package org.meleschkin.mongodb;

import com.mongodb.*;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.BasicConfigurator;

import java.util.List;

@Log4j
public class MongoDBTest {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        try {
            MongoClientURI mongoClientURI = new MongoClientURI("mongodb://meleschkin:sesamavm123@cluster0.pjfca.mongodb.net");
            MongoCredential mcu = mongoClientURI.getCredentials();
            if (mcu != null) {
                log.info(mcu.toString());
                log.info(mcu.getUserName());
                log.info(mcu.getPassword());
            }
            MongoClient mongoClient = new MongoClient(mongoClientURI);
            List<MongoCredential> mcn = mongoClient.getCredentialsList();
            for (MongoCredential mc : mcn) {
                log.info(mc.toString());
                log.info(mc.getUserName());
                log.info(mc.getPassword());
            }
            DB database = mongoClient.getDB("sample_analytics");
            log.info(database.toString());
            DBCollection collection = database.getCollection("customers");
            log.info(collection.toString());
            //  log.info("Count: " + collection.getCount());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

package com.van.SmartHome.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.van.SmartHome.jackson.ObjectMapperFactory;
import com.van.SmartHome.mongodb.ConfigMongoProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.MongoClientOptions.builder;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Slf4j
@Data
@NoArgsConstructor
public class Config {

    private PublicConfigProperties publik;
    private Map<String, Object> project = new HashMap<>();
    private String httpServerUri;
    private String appName;

    public Config(PublicConfigProperties publik, Map<String, Object> project) {
        this.publik = publik;
        this.project = project;
    }

    public Config(MongoTemplate template, String table, String appName) {
        this.publik = new PublicConfigProperties(  "81.69.99.55"
        ,27017
        , "admin"
        ,"123456");

    }


    public PublicConfigProperties getPublic() {
        return publik;
    }

    public void setPublic(Map<String, Object> project) {
        this.project = project;
    }

    /**
     * @return example "http://127.0.0.1:8080/"
     */
    public String getHttpServerUri() {
        if (this.httpServerUri == null) {
            this.httpServerUri = "http://" + publik.getApiServerIP() + ":" + publik.getApiServerPort() + "/";
        }
        return this.httpServerUri;
    }



    public MongoClient createMongoClient() {
        String host = getPublic().getMongodbHost();
        int port = getPublic().getMongodbPort();
        log.info("!!!host addr:{},port:{}",host,port);
        log.info("!!!hostpublik:{}",publik);

        String adminDbName = "admin";
        String username = publik.getMongodbUsername();
        String password = publik.getMongodbPassword();
        ArrayList<MongoCredential> credentials = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(username)) {
            MongoCredential credential = MongoCredential.createCredential(username, adminDbName, password.toCharArray());
            credentials.add(credential);
        }
        //MongoClientOptions options = builder().connectionsPerHost(10).build();
        MongoClientOptions options = builder().connectionsPerHost(10).build();
        return new MongoClient(new ServerAddress(host, port), credentials, options);
    }

    public Map<String, Object> findConfig(MongoTemplate template, String name, String coll) {
        ConfigDocument doc = template.findOne(query(where("name").is(name)), ConfigDocument.class, coll);
        if (doc != null) {
            return doc.getConfig();
        }
        return new HashMap<>();
    }
}

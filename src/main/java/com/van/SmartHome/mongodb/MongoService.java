package com.van.SmartHome.mongodb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Mongo;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.lang.NonNull;

import java.io.InputStream;

public abstract class MongoService {

    @Autowired
    @Qualifier("mongo")
    protected Mongo mongo;

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected MongoFactory factory;

    public Mongo getMongo() {
        return mongo;
    }

    public void setMongo(Mongo mongo) {
        this.mongo = mongo;
    }

    public boolean exist(ObjectId oId, String key, Object value, String collection) {
        Query query = Query.query(Criteria.where(key).is(value));
        return exist(oId, query, collection);
    }

    public boolean exist(ObjectId oId, Query query, String collection) {
        return this.factory.getMongoTemplate(oId).count(query, collection) > 0;
    }

    public boolean exist(String dbName, Query query, String collection) {
        return this.factory.getMongoTemplate(dbName).count(query, collection) > 0;
    }

    public InputStream openDownloadStream(String schoolId, @NonNull GridFSFile file) {
        return factory.getGridFSBucket(schoolId).openDownloadStream(file.getId());
    }

    public GridFsResource getGridFSResource(String schoolId, GridFSFile file) {
        return new GridFsResource(file, openDownloadStream(schoolId, file));
    }
}

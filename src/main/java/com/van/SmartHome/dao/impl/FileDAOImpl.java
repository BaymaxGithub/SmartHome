package com.van.SmartHome.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.van.SmartHome.dao.FileDAO;
import com.van.SmartHome.mongodb.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Slf4j
@Service
public class FileDAOImpl extends MongoService implements FileDAO {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public GridFSFile getFile(String schoolId, ObjectId id) {
        return factory.getGridFsTemplate(schoolId).findOne(query(where("_id").is(id)));
    }




    @Override
    public Map<String, Object> addFile(String schoolId, String filename,String suffix, InputStream in) {
        GridFsTemplate fsTemplate = factory.getGridFsTemplate(schoolId);

        Map<String, Object> map = new HashMap<>();

        //GridFsTemplate fsTemplate = getGridFsTemplate(oid);
        Document metadata = new Document();
        metadata.put("type", "presentation");
        metadata.put("suffix", suffix);
        ObjectId fileId = fsTemplate.store(in, filename, "application/octet-stream", metadata);
        map.put("_id", fileId);

        return map;
    }




    @Override
    public GridFsResource getResource(String schoolId, GridFSFile file) {
        return super.getGridFSResource(schoolId, file);
    }
}

package com.van.SmartHome.dao;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.InputStream;
import java.util.Map;

public interface FileDAO {

    public GridFSFile getFile(String schoolId, ObjectId ID);



    Map<String, Object> addFile(String schoolId, String filename, String suffix, InputStream in);



    GridFsResource getResource(String schoolId, GridFSFile file);
}

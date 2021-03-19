package com.van.SmartHome.dao.impl;


import com.van.SmartHome.dao.UserPicureDAO;
import com.van.SmartHome.model.UserPicture;
import com.van.SmartHome.mongodb.Collections;
import com.van.SmartHome.mongodb.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class UserPictureDAOImpl extends MongoService implements UserPicureDAO {

    private String collectionName = Collections.USER_PICTURE;



    @Override
    public void addUserPicture(String dbName, UserPicture userPicture){
        MongoTemplate template = factory.getMongoTemplate(dbName);
        template.save(userPicture,collectionName);

    }





}


package com.van.SmartHome.dao.impl;

import com.van.SmartHome.dao.UserDAO;
import com.van.SmartHome.model.User;
import com.van.SmartHome.mongodb.Collections;
import com.van.SmartHome.mongodb.MongoService;
import com.van.SmartHome.util.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import static org.springframework.data.mongodb.core.query.Criteria.where;


@Component
@Slf4j
public class UserDAOImpl extends MongoService implements UserDAO {

    private String collectionName = Collections.USER;



    @Override
    public void addUser(String dbName, User user){
        MongoTemplate template = factory.getMongoTemplate(dbName);
        template.save(user,collectionName);

    }

    @Override
    public User getUserByField(String dbName,String field, Object value){
        MongoTemplate template = factory.getMongoTemplate(dbName);
        Query query = Query.query(Criteria.where(field).is(value));
        return template.findOne(query,User.class,collectionName);
    }

    @Override
    public User getUserByFieldTwo(String dbName,String field1, Object value1,
                                  String field2, Object value2){
        MongoTemplate template = factory.getMongoTemplate(dbName);
        Query query = Query.query(Criteria.where(field1).is(value1).and(field2).is(value2));
        return template.findOne(query,User.class,collectionName);
    }




}


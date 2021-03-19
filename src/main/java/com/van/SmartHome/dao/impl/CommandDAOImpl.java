package com.van.SmartHome.dao.impl;

import com.van.SmartHome.dao.CommandDAO;
import com.van.SmartHome.model.Command;
import com.van.SmartHome.mongodb.Collections;
import com.van.SmartHome.mongodb.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CommandDAOImpl extends MongoService implements CommandDAO {

    private String collectionName = Collections.COMMAND;



    @Override
    public void addCommand(String dbName, Command command){
        MongoTemplate template = factory.getMongoTemplate(dbName);
        template.save(command,collectionName);

    }




}


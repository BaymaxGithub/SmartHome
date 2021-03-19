package com.van.SmartHome.controller;


import com.van.SmartHome.dao.CommandDAO;
import com.van.SmartHome.dao.UserDAO;
import com.van.SmartHome.model.BasicResult;
import com.van.SmartHome.model.Command;
import com.van.SmartHome.model.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.van.SmartHome.mongodb.DBNames.USE_DB;


@RestController
@Slf4j
@RequestMapping("api")
public class CommandController {


    private final String dbName =USE_DB;

    @Autowired
    CommandDAO commandDAO;

    /**
     * 接受用户的指令，保存到数据库，再发送到设备
     */
    @PostMapping("/command")
    @ResponseBody
    public Object registerUser(

            @RequestParam(value = "uid",required = true) ObjectId uid,
            @RequestParam(value = "commandContent",required = true) String commandContent

    ) {

        log.info("!!!!api/command uid:{} commandContent:{}"
                ,uid,commandContent);

        Command command = new Command();
        command.setUid(uid);
        command.setCommandContent(commandContent);
        //设置时间
        Instant now = Instant.now();
        command.setCreateTime(now.getEpochSecond());


        commandDAO.addCommand(dbName,command);
        //todo  发送mqtt消息到设备


        return BasicResult.of(command);
    }




}

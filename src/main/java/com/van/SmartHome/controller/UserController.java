package com.van.SmartHome.controller;


import com.van.SmartHome.dao.UserDAO;
import com.van.SmartHome.model.BasicResult;
import com.van.SmartHome.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.van.SmartHome.mongodb.DBNames.USE_DB;


@RestController
@Slf4j
@RequestMapping("api")
public class UserController {


    private final String dbName =USE_DB;

    @Autowired
    UserDAO userDAO;

    /**
     * accessToken接口权限，默认"smart"
     *  用户注册
     *  需要邮箱，电话，用户名三个数据
     *  返回一个uid
     */
    @PostMapping("/user")
    @ResponseBody
    public Object registerUser(
           // @RequestParam(value = "accessToken",required = true) String accessToken,
            @RequestParam(value = "email",required = true) String email,
            @RequestParam(value = "phone",required = true) String phone,
            @RequestParam(value = "userName",required = true) String userName,
            @RequestParam(value = "password",required = true) String password

    ) {

        log.info("!!!!api/user  email:{},phone:{},userName:{}，password:{}"
                ,email,phone,userName,password);

        User userCheck = userDAO.getUserByField(dbName,"email",email);
        if (userCheck!=null){
            Map map = new HashMap();
            map.put("error","用户已经存在，请直接登录");
            return BasicResult.of(map);
        }

        User user = new User();
        user.setEmail(email);
        user.setPhone(phone);
        user.setUserName(userName);
        user.setPassword(password);
        //设置时间
        Instant now = Instant.now();
        user.setCreateTime(now.getEpochSecond());
        user.setUpdateTime(now.getEpochSecond());

        userDAO.addUser(dbName,user);

        return BasicResult.of(user);
    }


    /**
     * 用户登录
     */
    @GetMapping("/user/login")
    @ResponseBody
    public Object userLogin(

            @RequestParam(value = "email",required = true) String email,
            @RequestParam(value = "password",required = true) String password

    ) {

        log.info("!!!!api/user  email:{},phone:{},password:{}"
                ,email,password);

        User user = userDAO.getUserByFieldTwo(dbName,"email",email,"password",password);
        if (user==null){
            Map map = new HashMap();
            map.put("error","用户不存在或密码不正确！");
            return BasicResult.of(map);
        }

        return BasicResult.of(user);
    }


}

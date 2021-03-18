package com.van.SmartHome.dao;


import com.van.SmartHome.model.User;

public interface UserDAO{
    public void addUser(String dbName, User user);
    public User getUserByField(String dbName,String field, Object value);
    public User getUserByFieldTwo(String dbName,String field1, Object value1,
                                  String field2, Object value2);

}

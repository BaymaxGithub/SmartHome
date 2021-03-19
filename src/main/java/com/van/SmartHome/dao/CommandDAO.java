package com.van.SmartHome.dao;


import com.van.SmartHome.model.Command;
import com.van.SmartHome.model.User;

public interface CommandDAO {
    public void addCommand(String dbName, Command command);

}

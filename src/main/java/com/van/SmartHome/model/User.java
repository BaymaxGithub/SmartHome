package com.van.SmartHome.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class User {

    @Id
    @JsonProperty("_id")
    private ObjectId id;

    private String userName;
    private String email;
    private String phone;
    private String password;

    private Long createTime;
    private Long updateTime;
}

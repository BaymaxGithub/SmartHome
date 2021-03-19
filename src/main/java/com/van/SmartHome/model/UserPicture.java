package com.van.SmartHome.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class UserPicture {

    @Id
    @JsonProperty("_id")
    private ObjectId id;

    private ObjectId uid;
    private ObjectId fileId;

    private Long uploadTime;
}

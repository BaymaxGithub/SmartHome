package com.van.SmartHome.mongodb;


public class StudyRoomQuery extends MongoQueryBuilder {

    public static StudyRoomQuery start() {
        return new StudyRoomQuery();
    }



    public StudyRoomQuery name(String name) {
        regex("name", name);
        return this;
    }


}
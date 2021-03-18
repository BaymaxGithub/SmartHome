package com.van.SmartHome.mongodb;


public class StudentQuery extends MongoQueryBuilder {

    public static StudentQuery start() {
        return new StudentQuery();
    }

    public StudentQuery className(String className) {
        regex("className", className);
        return this;
    }

    public StudentQuery name(String name) {
        regex("name", name);
        return this;
    }


    public StudentQuery phone(String phone) {
        regex("phone", phone);
        return this;
    }

    public StudentQuery studentNo(String studentNo) {
        regex("studentNo", studentNo);
        return this;
    }
    public StudentQuery studentId(String studentId) {
        regex("studentId", studentId);
        return this;
    }

    public StudentQuery email(String email) {
        regex("email", email);
        return this;
    }


}
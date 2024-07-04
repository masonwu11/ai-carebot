package com.aicarebot.api.daos;

import java.util.ArrayList;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String email;
    private String name;
    private String password;
    private Integer age;
    private String gender; 
    private ArrayList<Request> requests;
    private ArrayList<Record> records;
}

package com.aicarebot.api;

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
    private Record record;
    private ArrayList<Request> requests;
    private ArrayList<Record> records;
}

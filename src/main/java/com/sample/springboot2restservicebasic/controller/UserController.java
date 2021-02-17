package com.sample.springboot2restservicebasic.controller;

import com.sample.springboot2restservicebasic.model.User;
import com.sample.springboot2restservicebasic.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository studentRepository;



}
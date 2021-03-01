package com.example.controller;

import com.example.dao.KuserRepository;
import com.example.entity.Kuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KuserTestController {

    @Autowired
    KuserRepository kuserRepository;

    @RequestMapping("/user.json")
    public Kuser ntdevUser(@RequestParam(name = "username") String userName) {

        Kuser kuser = kuserRepository.findByUserName(userName);

        return kuser;
    }

}

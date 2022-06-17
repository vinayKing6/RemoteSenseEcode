package com.example.firstSpringBoot.model;

import com.example.firstSpringBoot.dao.User;
import com.example.firstSpringBoot.dao.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RegisterCheck {

    public Map<String,String> map=new HashMap<String,String>();
    private UserRepository userRepository;

    public RegisterCheck(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public boolean checkForm(User data){
        map.put("name","");
        map.put("email","");
        map.put("password","");

        if (userRepository.findByName(data.getName())!=null){
            map.put("name","用户名已存在");
            return false;
        }

        if (data.getName().getBytes(StandardCharsets.UTF_8).length<3
        ||data.getName().getBytes(StandardCharsets.UTF_8).length>10){
            map.put("name","请输入3-10位用户名");
            return false;
        }


        if (data.getEmail().indexOf("@")==-1){
            map.put("email","请输入正确的邮箱");
            return false;
        }

        if (data.getPassword().getBytes(StandardCharsets.UTF_8).length<=8||
        data.getPassword().getBytes(StandardCharsets.UTF_8).length>=15){
            map.put("password","请输入8-15位密码");
            return false;
        }

        return true;
    }
}

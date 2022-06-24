package com.example.firstSpringBoot.web;

import com.example.firstSpringBoot.dao.User;
import com.example.firstSpringBoot.dao.UserRepository;
import com.example.firstSpringBoot.model.ClassGeo;
import com.example.firstSpringBoot.model.DeChange;
import com.example.firstSpringBoot.model.DeTarget;
import com.example.firstSpringBoot.model.GetTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/product", produces = "application/json")
@CrossOrigin(origins = "*")
public class RestfulController {

    @Resource
    private UserRepository userRepository;

    @Autowired
    EntityLinks entityLinks;

    private String AbsoluteFilePath = System.getProperty("user.dir");


    @PostMapping("/DeChangeUpLoadFiles")
    public Map<String, Object> DeChangeUpLoadFiles(
            @RequestParam("image") MultipartFile image1,
            @RequestParam("type") String type,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user) {
        String relativeFilePath = "/product/DeChange";
        String AbsoluteFilePath=this.AbsoluteFilePath+File.separator+"/"+"target/classes/static/product/DeChange";
        try {
            List<String> list = new ArrayList<>();
            long timeMillis = System.currentTimeMillis();
            Map<String, Object> map = new HashMap<String, Object>();

            String prefix = user.getName();//get user name
            String suffix = image1.getOriginalFilename().split("\\.")[1];//get file type
            String newfilename = prefix + timeMillis + type + "." + suffix;//get format new file name : username+timemills+type+filetype
            File newFile = new File(AbsoluteFilePath + File.separator + type + File.separator + newfilename);
            File relativePath = new File(relativeFilePath + File.separator + type + File.separator + newfilename);

            System.out.println(newFile.toPath());
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }

            image1.transferTo(newFile);
            list.add("uploaded/" + newfilename);
            map.put("src", relativePath);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/GetTargetUpLoadFiles")
    public Map<String, Object> GetTargetUpLoadFiles(
            @RequestParam("image") MultipartFile image1,
            @RequestParam("type") String type,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user) {
        String relativeFilePath = "/product/GetTarget";
        String AbsoluteFilePath=this.AbsoluteFilePath+File.separator+"/"+"target/classes/static/product/GetTarget";
        try {
            List<String> list = new ArrayList<>();
            long timeMillis = System.currentTimeMillis();
            Map<String, Object> map = new HashMap<String, Object>();

            String prefix = user.getName();//get user name
            String suffix = image1.getOriginalFilename().split("\\.")[1];//get file type
            String newfilename = prefix + timeMillis + type + "." + suffix;//get format new file name : username+timemills+type+filetype
            File newFile = new File(AbsoluteFilePath + File.separator + type + File.separator + newfilename);
            File relativePath = new File(relativeFilePath + File.separator + type + File.separator + newfilename);

            System.out.println(newFile.toPath());
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }

            image1.transferTo(newFile);
            list.add("uploaded/" + newfilename);
            map.put("src", relativePath);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/DeTargetUpLoadFiles")
    public Map<String, Object> DeTargetUpLoadFiles(
            @RequestParam("image") MultipartFile image1,
            @RequestParam("type") String type,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user) {
        String relativeFilePath = "/product/DeTarget";
        String AbsoluteFilePath=this.AbsoluteFilePath+File.separator+"/"+"target/classes/static/product/DeTarget";
        try {
            List<String> list = new ArrayList<>();
            long timeMillis = System.currentTimeMillis();
            Map<String, Object> map = new HashMap<String, Object>();

            String prefix = user.getName();//get user name
            String suffix = image1.getOriginalFilename().split("\\.")[1];//get file type
            String newfilename = prefix + timeMillis + type + "." + suffix;//get format new file name : username+timemills+type+filetype
            File newFile = new File(AbsoluteFilePath + File.separator + type + File.separator + newfilename);
            File relativePath = new File(relativeFilePath + File.separator + type + File.separator + newfilename);

            System.out.println(newFile.toPath());
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }

            image1.transferTo(newFile);
            list.add("uploaded/" + newfilename);
            map.put("src", relativePath);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/ClassGeoUpLoadFiles")
    public Map<String, Object> ClassGeoUpLoadFiles(
            @RequestParam("image") MultipartFile image1,
            @RequestParam("type") String type,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user) {
        String relativeFilePath = "/product/ClassGeo";
        String AbsoluteFilePath=this.AbsoluteFilePath+File.separator+"/"+"target/classes/static/product/ClassGeo";
        try {
            List<String> list = new ArrayList<>();
            long timeMillis = System.currentTimeMillis();
            Map<String, Object> map = new HashMap<String, Object>();

            String prefix = user.getName();//get user name
            String suffix = image1.getOriginalFilename().split("\\.")[1];//get file type
            String newfilename = prefix + timeMillis + type + "." + suffix;//get format new file name : username+timemills+type+filetype
            File newFile = new File(AbsoluteFilePath + File.separator + type + File.separator + newfilename);
            File relativePath = new File(relativeFilePath + File.separator + type + File.separator + newfilename);

            System.out.println(newFile.toPath());
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }

            image1.transferTo(newFile);
            list.add("uploaded/" + newfilename);
            map.put("src", relativePath);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/DeChange")
    public Map<String, Object> DeChange(@RequestParam("image1") String image1,
                                        @RequestParam("image2") String image2,
                                        SessionStatus sessionStatus,
                                        @AuthenticationPrincipal User user) {
        Map<String, Object> map = new HashMap<>();
        DeChange deChange = new DeChange(image1 , image2 ,  user.getName());
        String pre = deChange.getPre();
        map.put("src", pre);
        return map;
    }

    @PostMapping("/GetTarget")
    public Map<String, Object> GetTarget(@RequestParam("image1") String image1,
                                        SessionStatus sessionStatus,
                                        @AuthenticationPrincipal User user) {
        Map<String, Object> map = new HashMap<>();
        GetTarget getTarget = new GetTarget(image1  ,  user.getName());
        String pre = getTarget.getPre();
        map.put("src", pre);
        return map;
    }

    @PostMapping("/DeTarget")
    public Map<String, Object> DeTarget(@RequestParam("image1") String image1,
                                         @RequestParam("classes") String classes,
                                         SessionStatus sessionStatus,
                                         @AuthenticationPrincipal User user) {
        Map<String, Object> map = new HashMap<>();
        DeTarget deTarget = new DeTarget(image1, user.getName(),classes);
        String pre = deTarget.getPre();
        map.put("src", pre);
        return map;
    }

    @PostMapping("/ClassGeo")
    public Map<String, Object> ClassGeo(@RequestParam("image1") String image1,
                                        SessionStatus sessionStatus,
                                        @AuthenticationPrincipal User user) {
        Map<String, Object> map = new HashMap<>();
        ClassGeo classGeo = new ClassGeo(image1, user.getName());
        String pre = classGeo.getPre();
        map.put("src", pre);
        return map;
    }


}

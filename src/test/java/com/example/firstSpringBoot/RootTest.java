package com.example.firstSpringBoot;

import org.junit.jupiter.api.Test;

public class RootTest {

    @Test
    public void showRoot(){
        System.out.println(System.getProperty("user.dir"));
    }
}

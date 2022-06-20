package com.example.firstSpringBoot;

import com.example.firstSpringBoot.model.RuntimePython;
import org.junit.jupiter.api.Test;

public class RootTest {

    @Test
    public void showRoot(){
        Thread thread=new Thread(new RuntimePython());
        thread.start();
    }
}

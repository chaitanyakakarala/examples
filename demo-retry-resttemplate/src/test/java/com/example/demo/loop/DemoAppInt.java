package com.example.demo.loop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = DemoApplication1.class)
public class DemoAppInt {

    @Test
    public void test(){
        //As we are running method from post construct..
        //we don't have to autowire any class and call method
        //post construct methods will be called by default along with springrunner annonation
        //load on startup

        Assert.assertTrue(true);
    }

}

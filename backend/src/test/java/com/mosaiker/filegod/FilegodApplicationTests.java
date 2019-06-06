package com.mosaiker.filegod;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilegodApplicationTests {

    @Test
    public void contextLoads() {
        JSONObject test = new JSONObject();
        test.put("a", "1");
        String a = test.getString("a");
        String b = test.getString("b");
        System.out.println(a);
        System.out.println(b);
    }

}

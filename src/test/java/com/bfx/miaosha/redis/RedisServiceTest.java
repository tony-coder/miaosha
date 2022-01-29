package com.bfx.miaosha.redis;

import com.bfx.miaosha.pojo.domain.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {
    @Autowired
    private RedisService redisService;

    @Test
    public void get() {
        User user = redisService.get(UserKey.getById(), "1", User.class);
        Assert.assertNotNull(user);
        System.out.print(user);
    }

    @Test
    public void set() {
        User user = User.builder().id(1).name("111").build();
        redisService.set(UserKey.getById(), "1", user);  // UserKey:id1

    }
}
package com.huyan.entity;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huyan.ApplicationTests;
import com.huyan.responsitory.UserRepository;


/**
 * @author 胡琰
 * @date 2019/4/2 7:40
 */
public class UserRepositoryTest extends ApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne() {
        User user = userRepository.findOne(1L);
        Assert.assertEquals("huyan", user.getName());
    }
}

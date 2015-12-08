package com.miratech.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Roman Zolotarov on 8/12/15.
 */
public class SayServiceImplTest {

    SayService sayService = new SayServiceImpl();

    @Test
    public void testSayHello() throws Exception {
        String name = "Test";
        String greeting = sayService.sayHello(name);
        Assert.assertNotNull(greeting);
        Assert.assertTrue(greeting.contains("Test"));
    }
}
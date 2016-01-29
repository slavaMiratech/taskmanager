package com.miratech.training;

import com.miratech.service.SayService;
import com.miratech.service.SayServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by MaksimPs on 28.01.2016.
 */
public class ManagerImplTest {

    String defName = "ManagerTest";
    Manager manager = new ManagerImpl(defName);

    @Test
    public void test() throws Exception {
        String name = manager.getName();
        Assert.assertNotNull(name);
        Assert.assertTrue(name.contains(defName));
    }
}
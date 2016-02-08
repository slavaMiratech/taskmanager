package com.miratech.training;

import com.miratech.service.SayService;
import com.miratech.service.SayServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

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
        Assert.assertTrue(name.equals(defName));
    }

    @Test
    public void testAddRemoveTask() throws Exception {
        Task task = new TaskImpl("task1", "summ", "descr", Priority.MAJOR, 10, new Date());
        Assert.assertNotNull(task);
        manager.assignTask(task);
        Assert.assertTrue(manager.getTaskList().contains(task));
        manager.removeTask(task);
        Assert.assertFalse(manager.getTaskList().contains(task));
    }

    @Test
    public void testAddRemoveEmployee() throws Exception {
        Employee emp = new Employee() {};
        Assert.assertNotNull(emp);
        manager.hireEmployee(emp);
        Assert.assertTrue(manager.getEmployeeList().contains(emp));
        manager.fireEmployee(emp);
        Assert.assertFalse(manager.getEmployeeList().contains(emp));
    }
}
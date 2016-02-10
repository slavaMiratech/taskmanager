package com.miratech.training;

import com.miratech.service.SayService;
import com.miratech.service.SayServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Properties;

import static java.lang.Thread.sleep;

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
        Task task = new TaskImpl();//("task1", "summ", "descr", Priority.MAJOR, 10, new Date());
        task.setDescription("TestTask");
        Assert.assertNotNull(task);
        manager.assignTask(task);
        Assert.assertTrue(manager.getTaskList().contains(task));
        manager.removeTask(task);
        Assert.assertFalse(manager.getTaskList().contains(task));
    }

    @Test
    public void testAddRemoveEmployee() throws Exception {
        Employee emp = new EmployeeImp();
        Assert.assertNotNull(emp);
        manager.hireEmployee(emp);
        Assert.assertTrue(manager.getEmployeeList().contains(emp));
        manager.fireEmployee(emp);
        Assert.assertFalse(manager.getEmployeeList().contains(emp));
    }


    @Test
    public void testWaitTaskComplete() throws Exception {
        Task task = new TaskImpl();
        task.setDescription("TestTask");

        Employee emp = new EmployeeImp();
        emp.setEmployee(
                new Properties() { { setProperty("name", "TestEmployee"); setProperty("mainLanguage", "Java"); } });

        manager.hireEmployee(emp);
        Assert.assertTrue(manager.getEmployeeList().contains(emp));

        manager.assignTask(task);
        Assert.assertTrue(manager.getTaskList().contains(task));

        manager.assignTaskToEmployee(task, emp);

        task.setStatus(ManagerImpl.taskInProcess);
        emp.setEmployeeStatus(ManagerImpl.employeeIsBusy);

//        (new Thread() { public void run() { manager.waitTaskComplete(task);}}).start();
        new Thread( ()->{ manager.waitTaskComplete(task); }).start();
//        (new Thread() { public void run() { manager.waitEmployee(emp);}}).start();
        new Thread( ()->{ manager.waitEmployee(emp); }).start();

        sleep(5000);
        task.setStatus(ManagerImpl.taskCompleted);
        sleep(2000);
        emp.setEmployeeStatus(ManagerImpl.employeeIsFree);
        manager.removeTaskFromEmployee(task, emp);

        manager.removeTask(task);
        Assert.assertFalse(manager.getTaskList().contains(task));

        manager.fireEmployee(emp);
        Assert.assertFalse(manager.getEmployeeList().contains(emp));
    }

}
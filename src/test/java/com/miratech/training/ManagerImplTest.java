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
        final Task task = new TaskImpl();
        task.setDescription("TestTask");

        // Чужой класс EmployeeImp прописан не полностью
        // Чтобы проверить тестирование - воспользуемся анонимным классом-потомком с необходимыми реализованными методами
        final Employee emp = new EmployeeImp() {
            EmployeeStatus status;
            @Override
            public EmployeeStatus getEmployeeStatus() {
                return status;
            }

            @Override
            public void setEmployeeStatus(EmployeeStatus employeeStatus) { status = employeeStatus; };
        };

        // Создаем работника
        emp.setEmployee(
                new Properties() { { setProperty("name", "TestEmployee"); setProperty("mainLanguage", "Java"); } });

        // Менеджер нанимает работника
        manager.hireEmployee(emp);
        Assert.assertTrue(manager.getEmployeeList().contains(emp));

        // Менеджер получает задачу
        manager.assignTask(task);
        Assert.assertTrue(manager.getTaskList().contains(task));

        // И поручает эту задачу работнику
        manager.assignTaskToEmployee(task, emp);

        // Задача в процессе
        task.setStatus(ManagerImpl.taskInProcess);
        // Pаботник загружен работой
        emp.setEmployeeStatus(ManagerImpl.employeeIsBusy);

        // Запускаем поток на ожидание, когда завершится задача
        (new Thread( new Runnable() {
            @Override
            public void run() {
                manager.waitTaskComplete(task);
            }
        })).start();
//        new Thread( ()->{ manager.waitTaskComplete(task); }).start();

        // Запускаем второй поток на ожидание, когда освободится работник
        (new Thread( new Runnable() {
            @Override
            public void run() {
                manager.waitEmployee(emp);
            }
        })).start();
//        new Thread( ()->{ manager.waitEmployee(emp); }).start();

        // работник выполняет задачу - менеджер в ожидании
        sleep(5000);
        // задача выполнена
        task.setStatus(ManagerImpl.taskCompleted);
        emp.setEmployeeStatus(ManagerImpl.employeeIsFree);
        // подчищаем хвосты
        sleep(1000);
        manager.removeTaskFromEmployee(task, emp);

        // удаляем задание
        manager.removeTask(task);
        Assert.assertFalse(manager.getTaskList().contains(task));

        // заданий больше нет, увольняем работника
        manager.fireEmployee(emp);
        Assert.assertFalse(manager.getEmployeeList().contains(emp));
    }

}
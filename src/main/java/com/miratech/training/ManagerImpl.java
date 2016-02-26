package com.miratech.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by MaksimPs on 28.01.2016.
 */
public class ManagerImpl implements Manager {
    private Logger log = LoggerFactory.getLogger(ManagerImpl.class);

    List<Task> tasks = new LinkedList<>();
    List<Employee> emps = new LinkedList<>();

    // Для поддержки статусов нужны перечисления (enum).
    // К сожалению, для TaskStatus и EmployeeStatus коллеги использовали интерфейсы, а не перечисления (enum)
    // Интерфейсы не содержат состояния
    // Для тестирования выкручиваемся так:
    //    В качестве хранения состояния используем объекты-метки (mark) на основании анонимных классов, наследуемых от требуемых интерфейсов
    public static final TaskStatus taskInProcess = new TaskStatus() { /* InProcess */};
    public static final TaskStatus taskCompleted = new TaskStatus() { /* Completed */};

    public static EmployeeStatus employeeIsBusy = new EmployeeStatus() { /* Busy */};
    public static EmployeeStatus employeeIsFree = new EmployeeStatus() { /* Free */};

    static int maxId = 0;

    Integer id;
    String name;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public ManagerImpl() {
        this("Manager :ID");
    }

    public ManagerImpl(String name) {
        maxId++;
        this.id = maxId;
        this.name = name;
        this.name = this.name.replaceAll(":ID", this.id.toString());
        log.info("Manager " + this.name + " has created\n");
    }

    // добавить задачу менеджеру
    @Override
    public void assignTask(Task task){
        log.info("Assign task \"" + task.getDescription() + "\"");
        synchronized (this) {
            tasks.add(task);
        }

    }

    // удалить задачу из списка задач
    @Override
    public void removeTask(Task task) {
        log.info("Remove task \"" + task.getDescription() + "\"");
        synchronized (this) {
            tasks.remove(task);
        }
    }

    // удалить все задачи
    @Override
    public void removeAllTasks() {
        log.info("Removing all tasks");
        synchronized (this) {
            tasks.clear();
        }
        log.info("All tasks have removed");
    }

    // получить все задачи
    @Override
    public List<Task> getTaskList(){
        synchronized (this) {
            return Collections.unmodifiableList(tasks);
        }
    }

    // нанять сотрудника в проект
    @Override
    public void hireEmployee(Employee emp){
        log.info("Hire employee " + emp);
        synchronized (this) {
            emps.add(emp);
            emp.setManager(this);
        }
    }

    // убрать сотрудника из проекта
    @Override
    public void fireEmployee(Employee emp){
        log.info("Fire employee " + emp);
        synchronized (this) {
            emp.setManager(null);
            emps.remove(emp);
        }
    }

    // список сотрудников в проекте
    @Override
    public List<Employee> getEmployeeList(){
        synchronized (this) {
            return Collections.unmodifiableList(emps);
        }
    }

    // назначить задачу сотруднику
    @Override
    public void assignTaskToEmployee(Task task, Employee emp){
        log.info("Assign task \"" + task.getDescription() + "\" to employee " + emp);
        emp.action(task, new EmployeeTaskAction() {/*SET*/});
    }

    // забрать задачу у сотрудника
    @Override
    public void removeTaskFromEmployee(Task task, Employee emp){
        log.info("Remove task \"" + task.getDescription() + "\" from employee " + emp);
        emp.action(task, new EmployeeTaskAction() {/*REMOVE*/});
    }

    // свободен ли сотрудник
    private boolean isEmployeeFree(Employee emp){
        synchronized (emp) {
            return employeeIsFree.equals(emp.getEmployeeStatus());
        }
    }

    // получить статус задачи
    private TaskStatus getTaskStatus(Task task){
        synchronized (task) {
            return task.getStatus();
        }
    }

    // ожидать завершения задачи
    @Override
    public void waitTaskComplete(Task task){
        synchronized (this) {
            if (!tasks.contains(task)) {
                log.info("Task " + task.getDescription() + " is not in my task list.");
                return;
            }
        }

        try {
            while(!taskCompleted.equals(getTaskStatus(task))) {
                log.info("Waiting a task " + task.getDescription() + " when it is closed. Smoking...");
                //task.wait();
                Thread.sleep(1000);
            }

            log.info("A task " + task.getDescription() + " has completed.");
        } catch (InterruptedException e) {
            log.info("waitTaskComplete interrupted...");
            e.printStackTrace();
        }

        removeTask(task);
    }

    // ожидать, когда сотрудник освободится
    @Override
    public void waitEmployee(Employee emp){
        synchronized (this) {
            if (!emps.contains(emp)) {
                log.info("Employee " + emp + " is not in my employee list.");
                return;
            }
        }

        try {
            while (!isEmployeeFree(emp)) {
                log.info("Waiting an employee " + emp + " when he is free. Smoking...");
                //emp.wait();
                Thread.sleep(1000);
            }

            log.info("An employee " + emp + " is free now.");
        } catch (InterruptedException e) {
            log.info("waitEmployee interrupted...");
        }
    }
}

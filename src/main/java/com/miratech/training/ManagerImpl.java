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
        List<Task> list = new ArrayList<>();
        synchronized (this) {
            list = Collections.unmodifiableList(tasks);
        }
        return tasks;
    }

    // нанять сотрудника в проект
    @Override
    public void hireEmployee(Employee emp){
        log.info("Hire " + emp);
        emp.setManager(this);
        emps.add(emp);
    }

    // убрать сотрудника из проекта
    @Override
    public void fireEmployee(Employee emp){
        log.info("Fire " + emp);
        emps.remove(emp);
        emp.setManager(null);
    }

    // список сотрудников в проекте
    @Override
    public List<Employee> getEmployeeList(){
        return emps;
    }

    // назначить задачу сотруднику
    private void assignTaskToEmployee(Task task, Employee emp){
        emp.action(task, new EmployeeTaskAction() {/*FORWARD*/});
    }

    // забрать задачу у сотрудника
    private void removeTaskFromEmployee(Task task, Employee emp){
        emp.action(task, new EmployeeTaskAction() {/*CLOSE*/});
    }

    // свободен ли сотрудник
    private boolean isEmployeeFree(Employee emp){
        return emp.getEmployeeStatus().equals(EmployeeStatus.FREE);
    }

    // получить статус задачи
    private TaskStatus getTaskStatus(Task task){
        return task.getStatus();
    }

    // ожидать завершения задачи
    @Override
    public void waitTaskComplete(Task task){
        synchronized (this) {
            if (!tasks.contains(task)) {
                log.info("Task " + task.getDescription() + " is not  in my task list.");
                return;
            }
        }

        synchronized (task) {
            try {
                while(!getTaskStatus(task).equals(TaskStatus.STATUS_CLOSED)) {
                    log.info("Waiting a task when it is closed. Smoking...");
                    task.wait();
                }
            } catch (InterruptedException e) {
                log.info("waitTaskComplete interrupted...");
                e.printStackTrace();
            }
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
        synchronized (emp) {
            try {
                while (!isEmployeeFree(emp)) {
                    log.info("Waiting an employee when he is free. Smoking...");
                    emp.wait();
                }
            } catch (InterruptedException e) {
                log.info("waitEmployee interrupted...");
            }
        }
    }
}

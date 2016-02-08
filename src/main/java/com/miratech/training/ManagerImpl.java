package com.miratech.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ManagerImpl() {
        maxId++;
        this.id = maxId;
        this.name = "Manager " + this.id;
        log.debug(name + " has created");
    }

    public ManagerImpl(String name) {
        this();
        this.name = name;
    }

    // добавить задачу менеджеру
    public void assignTask(Task task){
        log.debug("Assign task \"" + task.getDescription() + "\"");
        tasks.add(task);
    }

    // удалить задачу из списка задач
    public void removeTask(Task task) {
        log.debug("Remove task \"" + task.getDescription() + "\"");
        tasks.remove(task);
    }

    // удалить все задачи
    public void removeAllTasks() {
        log.debug("Removing all tasks");
        tasks.clear();
        log.debug("All tasks have  removed");
    }

    // получить все задачи
    public List<Task> getTaskList(){
        return tasks;
    }

    // нанять сотрудника в проект
    public void hireEmployee(Employee emp){
        log.debug("Add " + emp);
        emps.add(emp);
    }

    // убрать сотрудника из проекта
    public void fireEmployee(Employee emp){
        log.debug("Remove " + emp);
        emps.remove(emp);
    }

    // список сотрудников в проекте
    public List<Employee> getEmployeeList(){
        return emps;
    }

    // назначить задачу сотруднику
    private void assignTaskToEmployee(Task task, Employee emp){
//        emp.assignTask(task);
    }

    // забрать задачу у сотрудника
    private void removeTaskFromEmployee(Task task, Employee emp){
//        emp.removeTask(task);
    }

    // свободен ли сотрудник
    private boolean isEmployeeFree(Employee emp){
//        return emp.IsFree();
        return false;
    }

    // получить статус задачи
    private Integer getTaskStatus(Task task){
//        return task.getStatus();
        return -1;
    }

    // ожидать завершения задачи
    public void waitTaskComplete(Task task){
//        if (task.getStatus() != Status.CLOSED) {
//            task.wait();
//        }
    }

    // ожидать свободен ли сотрудник
    public void waitEmployee(Employee emp){
//        if (!emp.isFree()) {
//            emp.wait();
//        }
    }
}

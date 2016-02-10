package com.miratech.training;

import java.util.List;

/**
 * Created by vsytnyk on 17.12.2015.
 */
public interface Manager {
    Integer getId();
    String getName();

    void assignTask(Task task);
    void removeTask(Task task);
    void removeAllTasks();
    List<Task> getTaskList();

    void hireEmployee(Employee emp);
    void fireEmployee(Employee emp);
    List<Employee> getEmployeeList();

    void assignTaskToEmployee(Task task, Employee emp);
    void removeTaskFromEmployee(Task task, Employee emp);

//    boolean isEmployeeFree(Employee emp);
//    Integer getTaskStatus(Task task);

    void waitTaskComplete(Task task);
    void waitEmployee(Employee emp);
    //
}

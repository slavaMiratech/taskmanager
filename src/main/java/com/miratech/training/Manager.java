package com.miratech.training;

import java.util.List;

/**
 * Created by vsytnyk on 17.12.2015.
 */
public interface Manager {
    public Task assignTask(String name);
    public void removeTask(String name);
    public void removeAllTasks();
    public List<Task> getTaskList();

    public void hireEmployee(Employee emp);
    public void fireEmployee(Employee emp);
    public List<Employee> getEmployeeList();

    public void assignTaskToEmployee(Task task, Employee emp);
    public void removeTaskFromEmployee(Task task, Employee emp);

    public boolean isEmployeeFree(Employee emp);
    public Integer getTaskStatus(Task task);

    public void waitTaskComplete(Task task);
    public void waitEmployee(Employee emp);
}

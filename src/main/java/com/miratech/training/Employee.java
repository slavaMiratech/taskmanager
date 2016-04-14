package com.miratech.training;

import java.util.List;
import java.util.Properties;

/**
 * Created by vsytnyk on 17.12.2015.
 */
public interface Employee {

    void setEmployee(Properties properties);

    Employee getEmployee(Properties properties);

    Manager getManager();

    void setManager(Manager manager);

    EmployeeStatus getEmployeeStatus();

    void setEmployeeStatus(EmployeeStatus employeeStatus);

    void action(Task task, TaskStatus action);

    List<Task> getTaskList(TaskStatus taskStatus);
}

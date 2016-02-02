package com.miratech.training;

import java.util.Calendar;
import java.util.Collection;

/**
 * Created by vsytnyk on 17.12.2015.
 */
public interface Task {
    void startWorkOnTask();
    void stopWorkOnTask();
    void completeTask();
    void setStatus(TaskStatus newStatus);
    TaskStatus getStatus();

    void setPriority(TaskPriority priority);
    TaskPriority getPriority();
    void sendReminder();
    void sendNotification();
    void addComment(TaskComment comment);
    void removeComment(TaskComment commment);
    Collection<TaskComment> getComments();
    void addEmployees(Collection<Employee> employee);
    Collection<Employee> getEmployees();
    void removeEmployees(Collection<Employee> employee);
    void calculateRemainingTime();

    String getDescription();
    void setDescription(String newDescrioption);

    void setSupervisor(Manager manager);
    Manager getSupervisor();

    void setEstimate(long seconds);
    long getEstimate();
    void setDeadline(Calendar date);
    Calendar getDeadline();
    void setStartDate(Calendar startDate);
    Calendar getStartDate();
}
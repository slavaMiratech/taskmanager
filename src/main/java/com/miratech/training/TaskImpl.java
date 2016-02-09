package com.miratech.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by VSytnyk on 28.01.2016.
 */
public class TaskImpl implements Task {
    Logger log = LoggerFactory.getLogger(TaskImpl.class);

    private Calendar deadline;
    private String description;
    private boolean isCompleted = false;
    private TaskStatus status;
    private TaskPriority priority;
    List<TaskComment> taskComments = new ArrayList<TaskComment>();
    List<Employee> employees = new ArrayList<Employee>();
    List<Manager> managers = new ArrayList<Manager>();
    private Calendar startDate;
    private long estimate;

    @Override
    public void startWorkOnTask() {
        log.info("Start work on task");
    }

    @Override
    public void stopWorkOnTask() {
        log.info("Stop work on task");
    }

    @Override
    public void completeTask() {
        isCompleted = true;
    }

    @Override
    public void setStatus(TaskStatus newStatus) {
        status = newStatus;
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    @Override
    public TaskPriority getPriority() {
        return priority;
    }

    @Override
    public void sendReminder() {

    }

    @Override
    public void sendNotification() {

    }

    @Override
    public void addComment(TaskComment comment) {
        taskComments.add(comment);
    }

    @Override
    public void removeComment(TaskComment commment) {
        taskComments.remove(taskComments);
    }

    @Override
    public Collection<TaskComment> getComments() {
        return taskComments;
    }

    @Override
    public void addEmployees(Collection<Employee> employee) {
        employees.addAll(employee);
    }

    @Override
    public Collection<Employee> getEmployees() {
        return employees;
    }

    @Override
    public void removeEmployees(Collection<Employee> employee) {
        employees.remove(employee);
    }

    @Override
    public void calculateRemainingTime() {

    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String newDescrioption) {
        description = newDescrioption;
    }

    @Override
    public void addSupervisors(List<Manager> managers) {
        this.managers.addAll(managers);
    }

    @Override
    public List<Manager> getSupervisors() {
        return managers;
    }

    @Override
    public void setEstimate(long seconds) {
        this.estimate = seconds;
    }

    @Override
    public long getEstimate() {
        return estimate;
    }

    @Override
    public void setDeadline(Calendar date) {
        deadline = date;
    }

    @Override
    public Calendar getDeadline() {
        return deadline;
    }

    @Override
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    @Override
    public Calendar getStartDate() {
        return startDate;
    }
}
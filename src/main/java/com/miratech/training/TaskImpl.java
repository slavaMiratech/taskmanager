package com.miratech.training;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by VSytnyk on 28.01.2016.
 */
public class TaskImpl implements Task {

    private boolean isCompleted = false;
    List<TaskComment> taskComments = new ArrayList<>();
    List<Employee> employees = new ArrayList<>();

    @Override
    public void startWorkOnTask() {

    }

    @Override
    public void stopWorkOnTask() {

    }

    @Override
    public void completeTask() {
        isCompleted = true;
    }

    @Override
    public void setStatus(TaskStatus newStatus) {

    }

    @Override
    public TaskStatus getStatus() {
        return null;
    }

    @Override
    public void setPriority(TaskPriority priority) {

    }

    @Override
    public TaskPriority getPriority() {
        return null;
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
        return null;
    }

    @Override
    public void setDescription(String newDescrioption) {

    }

    @Override
    public void setSupervisor(Manager manager) {

    }

    @Override
    public Manager getSupervisor() {
        return null;
    }

    @Override
    public void setEstimate(long seconds) {

    }

    @Override
    public long getEstimate() {
        return 0;
    }

    @Override
    public void setDeadline(Calendar date) {

    }

    @Override
    public Calendar getDeadline() {
        return null;
    }

    @Override
    public void setStartDate(Calendar startDate) {

    }

    @Override
    public Calendar getStartDate() {
        return null;
    }
}

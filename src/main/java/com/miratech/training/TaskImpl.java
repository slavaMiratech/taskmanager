package com.miratech.training;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by akatunina on 1/28/2016.
 */
public class TaskImpl implements Task{
    String taskID;
    Status status;
    Collection<Employee> assigneeList;
    Priority priority;
    String description;
    String summary;
    Date endDate;
    Integer effort;
    Integer progress;

    public TaskImpl(String taskID, String summary, String description, Priority priority, Integer effort, Date endDate) {
        this.effort = effort;
        this.endDate = endDate;
        this.summary = summary;
        this.description = description;
        this.priority = priority;
        this.taskID = taskID;
        this.status = Status.NEW;
        this.assigneeList = new ArrayList<Employee>();
        this.progress = 0;
    }

    @Override
    public String getTaskID() {
        return this.taskID;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Collection<Employee> getAssignee() {
        return this.assigneeList;
    }

    @Override
    public void setAssignee(Employee assignee) {
        this.assigneeList.add(assignee);
    }

    @Override
    public void removeAssignee(Employee assignee) {
        this.assigneeList.remove(assignee);
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getSummary() {
        return this.summary;
    }

    @Override
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public void addProgress(Integer progress) {
        this.progress = this.progress + progress;
        if (this.progress < 0)
            this.progress = 0;
        maintainStatus();
    }

    @Override
    public Integer getProgress() {
        return this.progress;
    }

    public void maintainStatus(){
        if (this.progress >= this.effort)
            setStatus(Status.CLOSED);
        if (this.progress > 0 & this.status.equals(Status.NEW))
            setStatus(Status.IN_PROGRESS);
    }
}

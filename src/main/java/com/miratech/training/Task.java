package com.miratech.training;

import java.util.Date;

/**
 * Created by vsytnyk on 17.12.2015.
 */
public interface Task {
    String getTaskID();
    void setTaskID(String taskID);
    Status getStatus();
    void setStatus(Status status);
    Employee getAssignee();
    void setAssignee(Employee assignee);
    Priority getPriority();
    void setPriority(Priority priority);
    String getDescription();
    void setDescription(String description);
    String getSummary();
    void setSummary(String summary);
    Date getEndDate();
    void setEndDate(Date endDate);
    void addProgress(Integer progress);
    Integer getProgress();
//    Queue<Comment> getComments();
//    void addComment(Comment comment);
//    Manager getReporter();
//    void setReporter(Manager reporter);
}

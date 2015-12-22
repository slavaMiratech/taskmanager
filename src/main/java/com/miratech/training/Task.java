package com.miratech.training;

import java.util.Date;
import java.util.List;

/**
 * Created by vsytnyk on 17.12.2015.
 */
public interface Task {
    Integer getTaskID();
    void setTaskID(Integer taskID);
    Status getStatus();
    void setStatus(Status status);
    Person getAssignee();
    void setAssignee(Person person);
    Type getType();
    void setType(Type type);
    Priority getPriority();
    void setPriority(Priority priority);
    String getDescription();
    void setDescription(String description);
    String getSummary();
    void setSummary(String summary);
    List<Comment> getComments();
    void addComment(Comment comment);
    Date getDeadline();
    void setDeadline(Date deadline);
    Date getCreteDate();
    void setCreateDate(Date createDate);
    Person getReporter();
    void setReporter(Person person);
//
//    Integer getCompleteness();
//    void setCompleteness(Integer completeness);
//    void startTask();
//    void stopTask();
//    void closeTask();

}

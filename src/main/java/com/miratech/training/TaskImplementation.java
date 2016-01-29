package com.miratech.training;

import java.util.Date;
import java.util.List;

/**
 * Created by akatunina on 1/28/2016.
 */
public class TaskImplementation implements Task{
    String taskID;
    Status status;
    List<Employee> assigneeList;
    Priority priority;
    String description;
    String summary;
    Date endDate;
    Integer effort;
    Integer progress;
}

package com.miratech.training;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.invoke.empty.Empty;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by eabramov on 08.02.2016.
 */
public class EmployeeImpl implements Employee {
    private Logger log = LoggerFactory.getLogger(EmployeeImpl.class);

    @JsonIgnore
    List<Task> tasks = new LinkedList<>();
    @JsonIgnore
    List<Manager> manager = new LinkedList<>();
    @JsonProperty
    Integer id;
    @JsonProperty
    String name;
    @JsonProperty
    Integer mangerId;
    @JsonProperty
    EmployeeStatus status;
    static int maxId = 0;


    public EmployeeImpl(){
        this("Employee:ID,Manager:ID");
    }

    public EmployeeImpl(String name) {
        maxId++;
        this.id = maxId;
        String stringArr[]=name.split(",");
        if((stringArr.length==2)){
            this.name = stringArr[0].replaceAll(":ID", this.id.toString());
            this.mangerId = Integer.parseInt(stringArr[1].replaceAll(":ID", this.id.toString()));
            this.status=EmployeeStatus.FREE;
            log.info("Employee " + this.name + " has created\n");
        }else{
            log.error("Employee has wrong format");
        }
    }

    @Override
    public void setEmployee(Properties properties) {

    }

    @Override
    public Employee getEmployee(Properties properties) {
        return null;
    }

    @Override
    public Manager getManager() {
        synchronized(this){
            log.info("Enployee with name "+ this.name + " work");
            return  manager.get(this.mangerId);
        }
    }

    @Override
    public void setManager(Manager manager) {
        synchronized(this){
            log.info("Enployee with name "+ this.name + " should work with "+ manager.getName());
            this.mangerId=manager.getId();
        }
    }

    @Override
    public EmployeeStatus getEmployeeStatus() {
        log.info("Get Employee status");
        synchronized (this){
            return this.status;
        }
    }

    @Override
    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        log.info("Set Employee status to " + employeeStatus);
        synchronized (this){
            this.status=employeeStatus;
        }
    }

    @Override
    public void action(Task task, TaskStatus taskStatus){
        log.info("Set status "+taskStatus + " to "+task.getDescription());
        synchronized (task){
           task.setStatus(taskStatus);
        }
    }

    @Override
    public List<Task> getTaskList(TaskStatus taskStatus) {
        log.info("Get tasks witth status"+taskStatus);
        synchronized (this) {
            //TODO: Rewrite this then task.json will be presented
            return Collections.unmodifiableList(tasks);
        }
        }

}

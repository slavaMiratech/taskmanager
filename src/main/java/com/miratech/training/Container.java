package com.miratech.training;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by VSytnyk on 21.03.2016.
 */
public class Container {
    private static Logger log = LoggerFactory.getLogger(Container.class);

    public static final String MANAGERS_FILE_NAME = "managers.json";
    public static final String EMPLOYEES_FILE_NAME = "employees.json";
    public static final String TASKS_FILE_NAME = "employees.json";

    List<ManagerImpl> managers;
    List<TaskImpl> tasks;
    List<EmployeeImpl> employees;

    public Container() throws IOException {
        init();
    }

    private void init() throws IOException {
        log.info("Initializing entities container");
        managers = getSyncList(MANAGERS_FILE_NAME);
        tasks = getSyncList(TASKS_FILE_NAME);
        employees = getSyncList(EMPLOYEES_FILE_NAME);
    }

    private static <V> List<V> getSyncList(String fileName) throws IOException {
        log.debug("getting list of entities from ["+fileName+"] file");

        InputStream input =  new FileInputStream(new File("init/" + fileName));

        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        List<V> entitiesList = mapper.readValue(input, new TypeReference<List<V>>() {
        });
        input.close();

        List<V> synlist = Collections.synchronizedList(entitiesList);
        log.debug("got ["+synlist.size()+"] instances");

        return synlist;

    }
}

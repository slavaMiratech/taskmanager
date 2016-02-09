package com.miratech.training;

import java.util.List;
import java.util.Properties;

/**
 * Created by eabramov on 17.12.2015.
 */
public interface EmployeeTaskAction {
    final static  int INPROGRESS = 1;
    final static  int CLOSE = 3;
    final static  int REOPEN = 4;
    final static  int FORWARD = 5;
    final static  int GET = 6;
}

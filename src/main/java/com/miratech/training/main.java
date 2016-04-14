package com.miratech.training;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Properties;

public class main
{
    public static void main (String[] args) throws Exception
    {
//        // register the driver
//        String sDriverName = "org.sqlite.JDBC";
//        Class.forName(sDriverName);
//
//        // now we set up a set of fairly basic string variables to use in the body of the code proper
//        String sTempDb = "TaskManager.db";
//        String sJdbc = "jdbc:sqlite";
//        String sDbUrl = sJdbc + ":" + sTempDb;
//        // which will produce a legitimate Url for SqlLite JDBC :
//        // jdbc:sqlite:TaskManager.db
//        int iTimeout = 30;
////        String sMakeTable = "CREATE TABLE dummy (id numeric, response text)";
////        String sMakeInsert = "INSERT INTO dummy VALUES(1,'Hello from the database')";
////        String sMakeSelect = "SELECT response from dummy";
//        String sMakeSelect = "SELECT * from employees";
//
//        // create a database connection
//        Connection conn = DriverManager.getConnection(sDbUrl);
//        try {
//            Statement stmt = conn.createStatement();
//            try {
//                stmt.setQueryTimeout(iTimeout);
//                stmt.setQueryTimeout(iTimeout);
//                ResultSet rs = stmt.executeQuery(sMakeSelect);
//                System.out.println(rs);
//                try {
//                    while(rs.next())
//                    {
////                        {
////                            [0] = {},
////                            [1] = {}
////                        }
//
//                        //System.out.println(sResult);\
////                        ArrayList<String> table = new ArrayList<>();
////                        table.add(rs.next());
////                        System.out.println(rs.);                    }
//
////                } finally {
////                    try { rs.close(); } catch (Exception ignore) {}
////                }
//            } finally {
//                try { stmt.close(); } catch (Exception ignore) {}
//            }
//        } finally {
//            try { conn.close(); } catch (Exception ignore) {}
//        }
    }

}
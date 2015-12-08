package com.miratech.service;

/**
 * Test service for greeting
 * Created by Roman Zolotarov on 9/10/15.
 */
public interface SayService {
    /**
     * Method generate greeting message by name
     *
     * @param name name which will be used in greeting
     * @return generated greeting with name
     */
    String sayHello(String name);
}

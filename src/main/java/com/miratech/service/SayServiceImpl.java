package com.miratech.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Roman Zolotarov on 9/10/15.
 */
@Service
public class SayServiceImpl implements SayService {
    private Logger log = LoggerFactory.getLogger(SayServiceImpl.class);

    @Value("${playground.greeting}")
    private String greeting;

    public String sayHello(final String name) {
        String generatedGreeting = greeting + ", " + name + "!";
        log.info("Generated greeting for " + name + " : " + generatedGreeting);
        return generatedGreeting;
    }
}

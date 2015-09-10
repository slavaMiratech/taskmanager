package com.miratech.web;

import com.miratech.service.SayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Roman Zolotarov on 9/10/15.
 */
@Controller
@RequestMapping("/say")
public class SayController {
    private Logger log = LoggerFactory.getLogger(SayController.class);

    @Autowired
    private SayService sayService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> sayHello(@RequestParam String name) {
        log.info("Handle /hello request with name=" + name);
        String greeting = sayService.sayHello(name);
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}

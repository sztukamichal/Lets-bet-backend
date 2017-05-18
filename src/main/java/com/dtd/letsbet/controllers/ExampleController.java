package com.dtd.letsbet.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Michał on 18.05.2017.
 */
@RestController
@RequestMapping("/example")
public class ExampleController {

    @RequestMapping(method = RequestMethod.GET)
    public String getGreeting() {
        return "Hello";
    }

}
package org.opensoft.projecte.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.opensoft.projecte.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/user")
    public Greeting greetUser() {
        return new Greeting(counter.incrementAndGet(), "Hello User");
    }
    
    @RequestMapping("/admin")
    public Greeting greetAdmin() {
        return new Greeting(counter.incrementAndGet(), "Hello Admin! Ready for upgrade?");
    }
    
    @RequestMapping("/sudo")
    public Greeting greetGod() {
        return new Greeting(counter.incrementAndGet(), "I bow to you God!");
    }
}

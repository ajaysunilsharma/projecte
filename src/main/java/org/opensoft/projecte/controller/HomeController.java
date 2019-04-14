package org.opensoft.projecte.controller;

import org.opensoft.projecte.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@RequestMapping("/")
    public Greeting greetUser() {
        return new Greeting(1l, "Hello World!");
    }
}

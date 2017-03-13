/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.myappname.restcontroller;

import co.com.myappname.dto.Greeting;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dmartinezb
 */
@RestController
public class GreetingController implements ErrorController{
    
    private static final String ERROR_PATH = "/error";
    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong COUNTER = new AtomicLong();

    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(COUNTER.incrementAndGet(),
                            String.format(TEMPLATE, name));
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
    
    @RequestMapping(value = ERROR_PATH)
    public String error() {
        return "Sorry, an error has ocurred!";
    }
}

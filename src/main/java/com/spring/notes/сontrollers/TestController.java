package com.spring.notes.сontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @RequestMapping("/test")
    public ModelAndView printHello(){
        return  new ModelAndView("notes/test")
                .addObject("randomText", "Hello World!");
    }
}

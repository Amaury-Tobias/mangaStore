package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HelloController {

    @GetMapping("/hello")
    String hello(Map<String, Object> model){
        String username = "Usuario estandar";
        model.put("hola", "Hello");
        model.put("username", username);
        return "hello";
    }
}

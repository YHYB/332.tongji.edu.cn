package team.scholarship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Brian.Z
 * @project chocolateFactory
 * @classname HelloController
 * @description TODO
 * @date 2020/12/16 15:00
 */
@CrossOrigin
@RestController
@RequestMapping("/hello")
public class HelloController {

    @PostMapping("/say")
    @CrossOrigin
    @ResponseBody
    public String sayHello() {
        return "Hello Brian!";
    }

    @PostMapping("/add")
    @CrossOrigin
    @ResponseBody
    public int add(int a) {
        return a + 1;
    }
}

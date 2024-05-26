package ynu.edu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class fallbackController {
    @RequestMapping("fallback")
    public String fallback(){
        System.out.println("该服务产生了熔断，请稍后再试");
        return "该服务产生了熔断，请稍后再试";
    }
}

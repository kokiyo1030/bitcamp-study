package bitcamp.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    public HelloController() {
        System.out.println("HelloController 생성됨!");
    }

//    @RequestMapping(value = "/hello", produces = "text/html; charset=utf-8")
    @GetMapping(value = "/hello", produces = "text/html; charset=utf-8")
    @ResponseBody
    public String hello() {
        return "<html><body><h1>하하하하</h1></body></html>";
    }
}

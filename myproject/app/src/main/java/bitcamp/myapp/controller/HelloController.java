package bitcamp.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    public HelloController() {
        System.out.println("HelloController 생성됨!");
    }

    @RequestMapping("/hello")
    public String hello() {
        return "<html><body><h1>하하하하</h1></body></html>";
    }
}

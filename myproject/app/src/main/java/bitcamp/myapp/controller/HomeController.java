package bitcamp.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public void home() {
        // request handler에서 응답에 필요한 것을 요구하지도 않고(즉 HttpServletResponse 객체를 파라미터로 요구하지 않을 때)
        // view name(JSP 파일 경로)도 리턴하지 않는다면
        // 프론트 컨트롤러는 request handler를 실행할때 사용한 요청 URL을 view name으로 사용한다
    }
}

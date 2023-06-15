package hi.hispring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloController {
    @GetMapping("hello") //hello 맵핑을 생성
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); //hello!! 텍스트를 변경하면 /hello에서 나오는 문구도 달라짐
        return "hello";// ViewREsolver가 resources/templates의 hello.html 찾아서 처리
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){ //name 이라는 파라미터를 요청
        model.addAttribute("name", name);
        return "hello-template";
    }
}
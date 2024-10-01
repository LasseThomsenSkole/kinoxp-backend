package Controller;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @org.springframework.web.bind.annotation.GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}

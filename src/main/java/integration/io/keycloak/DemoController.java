package integration.io.keycloak;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {

    @GetMapping
    public String demo() {
        return "Hello, World!";
    }

    @GetMapping("admin")
    public String admin() {
        return "Hello, Admin!";
    }
}

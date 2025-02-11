package integration.io.keycloak;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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


    @GetMapping("/admin")
    @PreAuthorize("hasRole('client_role')")
    public String admin() {
        return "Hello, Admin!";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('user_role')")
    // hasRole('user_role') means that only users with the role user_role can access this endpoint.
    public String user() {
        return "Hello, User!";
    }

}

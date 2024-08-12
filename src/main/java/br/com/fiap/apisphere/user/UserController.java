package br.com.fiap.apisphere.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> findAll() {
        return userService.findAll();
    }

}

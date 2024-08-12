package br.com.fiap.apisphere.user;

import br.com.fiap.apisphere.user.dto.UserFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserFormRequest userForm, UriComponentsBuilder ucBuilder) {
        var user = userService.create(userForm.toModel());
        var uri = ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

}

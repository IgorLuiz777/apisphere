package br.com.fiap.apisphere.user;

import br.com.fiap.apisphere.user.dto.UserFormRequest;
import br.com.fiap.apisphere.user.dto.UserProfileResponse;
import br.com.fiap.apisphere.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> findAll(@RequestParam(required = false) String name) {
        if (name != null) return userService.findByName(name);
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserFormRequest userForm, UriComponentsBuilder ucBuilder) {
        var user = userService.create(userForm.toModel());
        var uri = ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(UserResponse.from(user));
    }

    @GetMapping("profile")
    public UserProfileResponse getProfile(){
        var email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userService.getProfile(email);
    }

    @PostMapping("avatar")
    public void uploadAvatar(@RequestBody MultipartFile file){
        var email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        userService.updateAvatar(email, file);
    }

    @GetMapping("/avatars/{filename}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String filename){
        Path path = Paths.get("src/main/resources/static/avatars/" + filename);
        Resource file = UrlResource.from(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }


}

package br.com.fiap.apisphere.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return  userRepository.findAll();
    }

    public User create(User user) {
        user.setPassword(passwordEncoder
        .encode(user.getPassword()));
        return userRepository.save(user);
    }

}

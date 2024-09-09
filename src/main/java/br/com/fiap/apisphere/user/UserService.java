package br.com.fiap.apisphere.user;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import br.com.fiap.apisphere.mail.MailService;
import br.com.fiap.apisphere.user.dto.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    public List<User> findAll() {
        return  userRepository.findAll();
    }

    public User create(User user) {
        user.setPassword(passwordEncoder
        .encode(user.getPassword()));
        user.setAvatar("https://avatar.iran.liara.run/username?username=" + user.getName());
        mailService.sendWelcomeEmail(user);
        return userRepository.save(user);
    }

    public UserProfileResponse getProfile(String email) {
        return userRepository.findByEmail(email)
                .map(UserProfileResponse::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public void updateAvatar(String email, MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("Invalid File");
        }

        try (InputStream is = file.getInputStream()) {
            Path destinationDir = Path.of("src/main/resources/static/avatars");
            Path destinationFile = destinationDir
                    .resolve(email + file.getOriginalFilename())
                    .normalize()
                    .toAbsolutePath();

            Files.copy(is, destinationFile);

            System.out.println("Arquivo salvo com sucesso");

            var user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            var baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath();
            var avatarUrl = baseUrl + "/avatars/" + destinationFile.getFileName();
            user.setAvatar(avatarUrl);
            userRepository.save(user);

        } catch (Exception e) {
            System.out.println("Erro ao copiar arquivo. " + e.getCause());
        }
    }

    public List<User> findByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
}

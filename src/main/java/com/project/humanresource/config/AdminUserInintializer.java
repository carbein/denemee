package com.project.humanresource.config;
import com.project.humanresource.entity.User;
import com.project.humanresource.entity.UserRole;
import com.project.humanresource.repository.UserRepository;
import com.project.humanresource.repository.UserRoleRepository;
import com.project.humanresource.utility.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUserInintializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    @Override
    public void run(String... args) {
        if (userRepository.findByEmail("admin@site.com").isEmpty()) {
            User admin = User.builder()
                    .email("admin@site.com")
                    .password("Aa1c2b3d456.*") // ✅ strong password
                    .isActive(true)
                    .isVerified(true)
                    .build();

            admin = userRepository.save(admin); // Admin kaydı yapıldı

            UserRole role = UserRole.builder()
                    .userId(admin.getId())
                    .userStatus(UserStatus.SITE_ADMIN)
                    .build();

            userRoleRepository.save(role);
            System.out.println("Admin user created!");
        }
        // deneme
    }
}

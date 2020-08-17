package ru.geekbrains.domodel.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.UserRepresentation;
import ru.geekbrains.domodel.repositories.AuthorityRepository;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.UserService;

import java.util.*;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;

/**
 * Сервис пользователей
 */
@Service
public class UserServiceImpl implements UserService {

    // Репозиторий пользователей
    private final UserRepository userRepository;

    // Репозиторий ролей пользователя
    private final AuthorityRepository authorityRepository;

    // Сервис шифрования паролей
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           AuthorityRepository authorityRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUserById(Long userId) {
        userRepository.deleteById(userId);
        return userRepository.existsById(userId);
    }

    @Override
    public User createUser(UserRepresentation userData) {
        Optional<User> optionalUser = userRepository.findByUsername(userData.getUsername());
        if (optionalUser.isPresent()) {
            return null;
        }

        User newUser = new User(
                userData.getUsername(),
                passwordEncoder.encode(userData.getPassword()),
                true,
                new Date());
        Authority authority = authorityRepository.findByAuthority(ROLE_USER);
        newUser.getAuthorities().add(authority);
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(UserRepresentation userData, User user) {
            if (userData.getUsername() != null && !userData.getUsername().isEmpty()) {
                user.setUsername(userData.getUsername());
            }
            if (userData.getPassword() != null && userData.getPassword().isEmpty() &&
                    userData.getPassword().equals(userData.getPasswordConfirm())) {
                user.setPassword(passwordEncoder.encode(userData.getPassword()));
            }
            if (userData.getFirstName() != null && !userData.getFirstName().isEmpty()) {
                user.setFirstName(userData.getFirstName());
            }
            if (userData.getSecondName() != null && !userData.getSecondName().isEmpty()) {
                user.setSecondName(userData.getSecondName());
            }
            if (userData.getMiddleName() != null && !userData.getMiddleName().isEmpty()) {
                user.setMiddleName(userData.getMiddleName());
            }
            if (userData.getEmail() != null && !userData.getEmail().isEmpty()) {
                user.setEmail(userData.getEmail());
            }
            return userRepository.save(user);
    }
}

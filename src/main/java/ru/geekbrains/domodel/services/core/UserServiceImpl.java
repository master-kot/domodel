package ru.geekbrains.domodel.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.UserRepresentation;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.UserService;

import java.util.*;

/**
 * Сервис пользователей
 */
@Service
public class UserServiceImpl implements UserService {

    // Репозиторий пользователей
    private final UserRepository userRepository;

    // Сервис шифрования паролей
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    @Override
    public User findUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUserById(Long userId) {
        userRepository.deleteById(userId);
        return userRepository.existsById(userId);
    }

    @Override
    public User createUser(UserRepresentation request) {
        Optional<User> userFromDB = userRepository.findByUsername(request.getUsername());
        if (userFromDB.isPresent()) {
            return null;
        }

        User user = new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                true,
                new Date());
        Authority authority = new Authority();
        authority.setUser(user);
        authority.setAuthority("ROLE_USER");
        user.setAuthorities(new ArrayList<>(Collections.singletonList(authority)));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User userData, String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userData.getUsername());
            user.setPassword(passwordEncoder.encode(userData.getPassword()));
            user.setFirstName(userData.getFirstName());
            user.setSecondName(userData.getSecondName());
            user.setMiddleName(userData.getMiddleName());
            user.setEmail(userData.getEmail());
            return userRepository.save(user);
        } else {
            return null;
        }
    }
}

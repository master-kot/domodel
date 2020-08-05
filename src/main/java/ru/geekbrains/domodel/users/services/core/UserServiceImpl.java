package ru.geekbrains.domodel.users.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.users.entities.Authority;
import ru.geekbrains.domodel.users.entities.User;
import ru.geekbrains.domodel.users.repositories.UserRepository;
import ru.geekbrains.domodel.users.entities.UserRepresentation;
import ru.geekbrains.domodel.users.services.api.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Сервис пользователей
 */
@Service
public class UserServiceImpl implements UserService {

    // Репозиторий пользователей
    private final UserRepository userRepository;

    // Класс для кодирования паролей
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username) {
        return userRepository.findById(username).get();
    }

    public User createNewUser(UserRepresentation request) {
        User user = new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                true,
                new ArrayList<>());
        user.getAuthorities().add(new Authority(user, "ROLE_USER"));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getAuthorities()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

}
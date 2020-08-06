package ru.geekbrains.domodel.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.UserRepresentation;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

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

    @Override
    public User findByLogin(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        return user.orElse(null);
    }

    @Override
    public User createNewUser(UserRepresentation request) {
        User user = new User(
                request.getLogin(),
                passwordEncoder.encode(request.getPassword()),
                true,
                new ArrayList<>());
        user.getAuthorities().add(new Authority(user, "ROLE_USER"));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        user.getAuthorities()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

}
package ru.geekbrains.domodel.services.core;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.repositories.UserRepository;

import java.util.Optional;

import static ru.geekbrains.domodel.entities.constants.Messages.USER_NOT_FOUND;

/**
 * Сервис, отвечающий за авторизацию пользователей
 */
@Service
@AllArgsConstructor
public class UserAuthenticationServiceImpl implements UserDetailsService {

    // Репозиторий пользователей
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> loadedUser = userRepository.findByUsername(username);
        return loadedUser
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getAuthorities()
                ))
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }
}

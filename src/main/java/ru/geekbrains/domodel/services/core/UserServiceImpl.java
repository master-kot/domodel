package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.repositories.AuthorityRepository;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;

/**
 * Сервис пользователей
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    // Сервис шифрования паролей
    private final BCryptPasswordEncoder passwordEncoder;

    // Репозиторий пользователей
    private final UserRepository userRepository;

    // Репозиторий ролей пользователя
    private final AuthorityRepository authorityRepository;

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
    public User createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()) {
            return null;
        }

        User newUser = new User(
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                true,
                LocalDate.now());
        Authority authority = authorityRepository.findByAuthority(ROLE_USER);
        newUser.getAuthorities().add(authority);
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(UserDto userDto, User user) {
            if (userDto.getUsername() != null && !userDto.getUsername().isEmpty()) {
                user.setUsername(userDto.getUsername());
            }
            if (userDto.getFirstName() != null && !userDto.getFirstName().isEmpty()) {
                user.setFirstName(userDto.getFirstName());
            }
            if (userDto.getLastName() != null && !userDto.getLastName().isEmpty()) {
                user.setLastName(userDto.getLastName());
            }
            if (userDto.getPatronymic() != null && !userDto.getPatronymic().isEmpty()) {
                user.setPatronymic(userDto.getPatronymic());
            }
            if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
                user.setEmail(userDto.getEmail());
            }
            return userRepository.save(user);
    }

    public void updateUser(UserDto userDto, String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            return;
        }
        if (userDto.getUsername() != null && !userDto.getUsername().isEmpty()) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getFirstName() != null && !userDto.getFirstName().isEmpty()) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null && !userDto.getLastName().isEmpty()) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getPatronymic() != null && !userDto.getPatronymic().isEmpty()) {
            user.setPatronymic(userDto.getPatronymic());
        }
        if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
            user.setEmail(userDto.getEmail());
        }
        userRepository.save(user);
    }
}

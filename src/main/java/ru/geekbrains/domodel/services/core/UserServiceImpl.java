package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.PasswordRequest;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.dto.UserRequest;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.common.JwtUser;
import ru.geekbrains.domodel.mappers.JwtUserMapper;
import ru.geekbrains.domodel.mappers.UserMapper;
import ru.geekbrains.domodel.repositories.AuthorityRepository;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;

/**
 * Сервис пользователей
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    // Необходимые сервисы
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUserMapper jwtUserMapper;

    // Необходимые репозитории
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDto getDtoById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(userMapper::userToUserDto).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElse(null);
    }

    @Override
    public JwtUser getJwtUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(jwtUserMapper::userToJwtUser).orElse(null);
    }

    @Override
    public UserDto getByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(userMapper::userToUserDto).orElse(null);
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.userToUserDto(userRepository.findAll());
    }

    @Override
    public boolean deleteById(Long userId) {
        userRepository.deleteById(userId);
        return userRepository.existsById(userId);
    }

    @Override
    public UserDto save(UserRequest userRequest) {
        if (userRequest.getPassword() == null || userRequest.getPassword().isEmpty()
                || !userRequest.getPassword().equals(userRequest.getPasswordConfirm())
                || userRequest.getUsername() == null || userRequest.getUsername().isEmpty()
                || userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            return null;
        }
        User newUser = new User(
                userRequest.getUsername(),
                passwordEncoder.encode(userRequest.getPassword()),
                true,
                LocalDate.now());
        Authority authority = authorityRepository.findByAuthority(ROLE_USER);
        newUser.getAuthorities().add(authority);
        return userMapper.userToUserDto(userRepository.save(newUser));
    }

    public UserDto update(UserDto userDto,
                          String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            return null;
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
        if (userDto.getPhotoLink() != null && !userDto.getPhotoLink().isEmpty()) {
            user.setPhotoLink(userDto.getPhotoLink());
        }
        if (userDto.getAddress() != null && !userDto.getAddress().isEmpty()) {
            user.setAddress(userDto.getAddress());
        }
        if (userDto.getPhoneNumber() != null && !userDto.getPhoneNumber().isEmpty()) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public boolean updatePassword(PasswordRequest passwordRequest,
                                  Authentication authentication) {
        if (!authentication.getName().equals(passwordRequest.getUsername())) {
            if (!hasAuthenticationRoleAdmin(authentication)) {
                return false;
            }
        }

        Optional<User> optionalUser = userRepository.findByUsername(authentication.getName());
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            return false;
        }

        String oldPassword = passwordRequest.getOldPassword();
        String newPassword = passwordRequest.getNewPassword();

        // TODO Проверить, что старый пароль введен верно
        if (oldPassword != null && newPassword != null && !newPassword.isEmpty() &&
                newPassword.equals(passwordRequest.getNewPasswordConfirm())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    /**
     * Проверить, что пользователь имеет роль Админа
     */
    private boolean hasAuthenticationRoleAdmin(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_ADMIN)));
    }
}

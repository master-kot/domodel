package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.PasswordRequest;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.dto.UserRequest;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.common.JwtUser;
import ru.geekbrains.domodel.exception.EntityBadRequestException;
import ru.geekbrains.domodel.mappers.UserMapper;
import ru.geekbrains.domodel.repositories.AuthorityRepository;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.geekbrains.domodel.entities.constants.Messages.*;
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
        return optionalUser.map(userMapper::userToJwtUser).orElse(null);
    }

    @Override
    public UserDto getByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(userMapper::userToUserDto)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
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
        String username = userRequest.getUsername();
        String password = userRequest.getPassword();

        if (password == null || password.isEmpty() || username == null || username.isEmpty()) {
            throw new EntityBadRequestException(BAD_REQUEST);
        }
        if (!password.equals(userRequest.getPasswordConfirm())) {
            throw new EntityBadRequestException(PASSWORD_MISMATCH);
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new EntityBadRequestException(String.format(USER_HAS_ALREADY_CREATED, username));
        }

        User newUser = new User(
                userRequest.getUsername(),
                passwordEncoder.encode(password),
                true,
                LocalDate.now());
        Authority authority = authorityRepository.findByAuthority(ROLE_USER);
        newUser.getAuthorities().add(authority);
        return userMapper.userToUserDto(userRepository.save(newUser));
    }

    @Override
    public UserDto update(UserDto userDto, String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = userMapper.updateUser(optionalUser.get(), userDto);
            return userMapper.userToUserDto(userRepository.save(user));
        } else {
            return null;
        }
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = userMapper.updateUser(optionalUser.get(), userDto);
            return userMapper.userToUserDto(userRepository.save(user));
        } else {
            return null;
        }
    }

    @Override
    public User update(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()) {
            User user = userMapper.updateUser(optionalUser.get(), userDto);
            return userRepository.save(user);
        } else {
            return null;
        }
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

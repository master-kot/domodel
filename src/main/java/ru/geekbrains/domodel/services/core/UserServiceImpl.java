package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.PasswordRequest;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.dto.UserRequest;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.common.JwtUser;
import ru.geekbrains.domodel.exceptions.EntityBadRequestException;
import ru.geekbrains.domodel.exceptions.EntityNotFoundException;
import ru.geekbrains.domodel.mappers.UserMapper;
import ru.geekbrains.domodel.repositories.AuthorityRepository;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.UserService;

import java.time.LocalDate;
import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Messages.*;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;
import static ru.geekbrains.domodel.entities.constants.Roles.hasAuthenticationRoleAdmin;

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
    public UserDto getDtoById(Long id) {
        return userMapper.userToUserDto(findById(id));
    }

    @Override
    public User getByUsername(String username) {
        return findByUsername(username);
    }

    @Override
    public JwtUser getJwtByUsername(String username) {
        return userMapper.userToJwtUser(findByUsername(username));
    }

    @Override
    public UserDto getDtoByUsername(String username) {
        return userMapper.userToUserDto(findByUsername(username));
    }

    @Override
    public List<UserDto> getAllDto() {
        return userMapper.userToUserDto(userRepository.findAll());
    }

    @Override
    public UserDto save(UserRequest userRequest) {
        if (!userRequest.getPassword().equals(userRequest.getPasswordConfirm())) {
            throw new EntityBadRequestException(PASSWORDS_MISMATCH);
        }
        String username = userRequest.getUsername();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new EntityBadRequestException(String.format(USER_HAS_ALREADY_CREATED, username));
        }

        User newUser = userMapper.userRequestToUser(userRequest);
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setEnabled(true);
        newUser.setCreationDate(LocalDate.now());
        Authority authority = authorityRepository.findByAuthority(ROLE_USER);
        newUser.getAuthorities().add(authority);
        return userMapper.userToUserDto(userRepository.save(newUser));
    }

    @Override
    public UserDto update(UserDto userDto, String username) {
        User user = userMapper.updateUser(findByUsername(username), userDto);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        User user = userMapper.updateUser(findById(id), userDto);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public User update(UserDto userDto) {
        User user = userMapper.updateUser(findByUsername(userDto.getUsername()), userDto);
        return userRepository.save(user);
    }

    @Override
    public boolean updatePassword(PasswordRequest passwordRequest,
                                  Authentication authentication) {
        if (!authentication.getName().equals(passwordRequest.getUsername())) {
            if (!hasAuthenticationRoleAdmin(authentication)) {
                return false;
            }
        }
        if (!passwordRequest.getNewPassword().equals(passwordRequest.getNewPasswordConfirm())) {
            throw new EntityBadRequestException(PASSWORDS_MISMATCH);
        }

        User user = findByUsername(authentication.getName());
        if (!passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())) {
            throw new EntityBadRequestException(OLD_PASSWORD_INVALID);
        } else {
            user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public boolean deleteById(Long userId) {
        userRepository.deleteById(userId);
        return userRepository.existsById(userId);
    }

    /**
     * Найти пользователя по id
     */
    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id)));
    }

    /**
     * Найти пользователя по username
     */
    private User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_USERNAME, username)));
    }
}

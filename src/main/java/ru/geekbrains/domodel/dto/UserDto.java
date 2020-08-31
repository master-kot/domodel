package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.geekbrains.domodel.entities.User;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO представление сущности Пользователь
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String email;

    private String photoLink;

    private String address;

    private Set<AccountDto> accounts;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymic(patronymic);
        user.setEmail(email);
        user.setPhotoLink(photoLink);
        user.setAddress(address);
        user.setAccounts(accounts
                .stream()
                .map(AccountDto::toAccount)
                .collect(Collectors.toSet()));

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPatronymic(user.getPatronymic());
        userDto.setEmail(user.getEmail());
        userDto.setPhotoLink(user.getPhotoLink());
        userDto.setAddress(user.getAddress());
        userDto.setAccounts(user.getAccounts()
                .stream()
                .map(AccountDto::fromAccount)
                .collect(Collectors.toSet()));

        return userDto;
    }
}

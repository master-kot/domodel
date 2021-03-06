package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.dto.AuthenticationRequest;
import ru.geekbrains.domodel.entities.common.JwtUser;
import ru.geekbrains.domodel.security.JwtTokenProvider;
import ru.geekbrains.domodel.services.api.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.geekbrains.domodel.entities.constants.Messages.*;

/**
 * Контроллер аутентификации.
 */
@Log4j2
@Api(value = "Контроллер аутентификации")
@RestController
@RequestMapping(value = "/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    // Список необходимых зависимостей
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @ApiOperation(value = "Осуществляет авторизацию пользователя и выдачу токена авторизации")
    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> login(@Valid @RequestBody AuthenticationRequest requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            JwtUser user = userService.getJwtByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_USERNAME, username));
            }
            log.debug(String.format(USER_WAS_FOUND, username));

            String token = jwtTokenProvider.createToken(username,
                    user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("roles", user.getAuthorities());
            response.put("token", token);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(BAD_CREDENTIALS);
        }
    }
}

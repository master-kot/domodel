package ru.geekbrains.domodel.entities.constants;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Класс, хранящий имена ролей пользователей
 */
public class Roles {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_DIRECTOR = "ROLE_DIRECTOR";
    public static final String ROLE_ACCOUNTANT = "ROLE_ACCOUNTANT";

    /**
     * Проверить, что пользователь имеет роль Админа
     */
    public static boolean hasAuthenticationRoleAdmin(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_ADMIN)));
    }

    /**
     * Проверить, что пользователь имеет роль Юзера
     */
    public static boolean hasAuthenticationRoleUser(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_USER)));
    }
}

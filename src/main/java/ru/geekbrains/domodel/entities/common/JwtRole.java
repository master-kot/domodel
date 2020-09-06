package ru.geekbrains.domodel.entities.common;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

/**
 * Jwt обертка для класса Роль пользователя
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRole implements GrantedAuthority {

    private String authority;
}

package ru.geekbrains.domodel.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Конфигурация WebSecurity для аутентификации пользователей
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Сервис шифрования паролей
    private final PasswordEncoder passwordEncoder;

    // Сервис данных пользователя
    private final UserDetailsService userDetailsService;

    /**
     * Конфигурация провайдера аутентификации
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * Конфигурация доступа в разделы сайта для определенных ролей пользователей
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/management/**").hasAnyRole("ADMIN")
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/meters/**").authenticated()
                .antMatchers("/bills/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/").permitAll()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/?error")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")
                .and()
                .rememberMe().key("uniqueAndSecret")
                .userDetailsService(userDetailsService)
                .and()
                .csrf().disable();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
}

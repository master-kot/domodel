package ru.geekbrains.domodel.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.domodel.security.jwt.JwtConfigurer;
import ru.geekbrains.domodel.security.jwt.JwtTokenProvider;

/**
 * Конфигурация WebSecurity для аутентификации пользователей
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Адреса доступа
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    private static final String MAIN_ENDPOINT = "/api/v1";
    private static final String SWAGGER_ENDPOINT = "/**";
    private static final String INFORMATION_ENDPOINT = "/api/v1/information/**";
    private static final String METERS_ENDPOINT = "/api/v1/meters/**";
    private static final String BILLS_ENDPOINT = "/api/v1/bills/**";
    private static final String ADMIN_ENDPOINT = "/api/v1/management/**";

    // Необходимые сервисы
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Конфигурация доступа в разделы сайта для определенных ролей пользователей
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(MAIN_ENDPOINT).permitAll()
                .antMatchers(INFORMATION_ENDPOINT).permitAll()
                .antMatchers(SWAGGER_ENDPOINT).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
//                .loginPage("/").permitAll()
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/")
//                .failureUrl("/?error")
//                .and()
                .logout().disable()
//                .logoutSuccessUrl("/")
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/")
//                .and()
//                .rememberMe().key("uniqueAndSecret")
//                .userDetailsService(userDetailsService)
//                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}

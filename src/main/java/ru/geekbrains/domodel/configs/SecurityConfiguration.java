package ru.geekbrains.domodel.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.geekbrains.domodel.security.jwt.JwtConfigurer;
import ru.geekbrains.domodel.security.jwt.JwtTokenProvider;

/**
 * Конфигурация WebSecurity для аутентификации пользователей
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Адреса доступа
    private static final String ADMIN_ENDPOINT = "/api/v1/management/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    private static final String METERS_ENDPOINT = "/api/v1/meters/**";
    private static final String BILLS_ENDPOINT = "/api/v1/bills/**";

    // Необходимые сервисы
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

//    /**
//     * Конфигурация провайдера аутентификации
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }

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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
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
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
}

package ru.geekbrains.domodel.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.geekbrains.domodel.services.api.UserService;

/**
 * Конфигурация WebSecurity для аутентификации пользователей
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        private final PasswordEncoder passwordEncoder;
        private final UserService userService;

        @Autowired
        public SecurityConfiguration(PasswordEncoder passwordEncoder, UserService userService) {
            this.passwordEncoder = passwordEncoder;
            this.userService = userService;
        }

        /**
         * Конфигурируем, что аутентификация будет проходить через провайдера
         */
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
        }

        /**
         * Конфигурация HttpSecurity настраивает
         * в какие разделы сайта разрешается доступ для определенных ролей пользователей
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
//                    .antMatchers("/users/**").hasAnyRole("ADMIN")
//                    .antMatchers("/meters/**").authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/").permitAll()
                    .loginProcessingUrl("/authenticateTheUser")
                    .defaultSuccessUrl("/")
                    .failureUrl("/?error")
                    .and()
                    .logout()
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/")
                    .and()
                    .rememberMe().key("uniqueAndSecret");
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userService);
            authenticationProvider.setPasswordEncoder(passwordEncoder);
            return authenticationProvider;
        }

}

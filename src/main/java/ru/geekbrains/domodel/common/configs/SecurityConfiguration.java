package ru.geekbrains.domodel.common.configs;

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
import ru.geekbrains.domodel.users.services.api.UserService;

/**
 * Конфигурация WebSecurity для аутентификации пользователей
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        private PasswordEncoder passwordEncoder;

        private UserService userService;

        @Autowired
        public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
        }

        @Autowired
        public void setUserService(UserService userService) {
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
            http.authorizeRequests()
                    //делаем часть страниц доступными только для пользователей с ролью ADMIN
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/product/delete/**").hasAnyRole("ADMIN")
                    .antMatchers("/product/add/**").hasAnyRole("ADMIN")
                    .antMatchers("/basket/**").authenticated()
                    .antMatchers("/order/**").authenticated()
                    .antMatchers("/product/**").authenticated()
                    .antMatchers("/shop/**").authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/").permitAll()
                    //для проверки коректности аутентификации используем адрес
                    .loginProcessingUrl("/authenticateTheUser")
                    .defaultSuccessUrl("/shop")
                    .failureUrl("/?error=authenticationFailed")
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

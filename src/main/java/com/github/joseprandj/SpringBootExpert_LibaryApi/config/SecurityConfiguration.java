package com.github.joseprandj.SpringBootExpert_LibaryApi.config;

import com.github.joseprandj.SpringBootExpert_LibaryApi.security.CustomAuthentication;
import com.github.joseprandj.SpringBootExpert_LibaryApi.security.CustomUserDetailsService;
import com.github.joseprandj.SpringBootExpert_LibaryApi.security.LoginSocialSuccessHandler;
import com.github.joseprandj.SpringBootExpert_LibaryApi.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginSocialSuccessHandler loginSocialSuccessHandler) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
//                .formLogin(
//                        configurer -> {
//                            configurer.loginPage("/login");
//                        }
//                )
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/users").permitAll();
//                    authorize.requestMatchers("/authors/**").hasRole("ADMIN");
//                    authorize.requestMatchers("/books/**").hasAnyRole("USER", "ADMIN");
                    authorize.anyRequest().authenticated();
                })
//                .oauth2Login(Customizer.withDefaults())
                .oauth2Login(oauth2 -> {
                    oauth2.successHandler(loginSocialSuccessHandler);
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

//    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
//        UserDetails user1 = User.builder()
//                .username("JJ")
//                .password(encoder.encode("1234"))
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("ADMIN")
//                .password(encoder.encode("PASS"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
        return new CustomUserDetailsService(userService);
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }
}

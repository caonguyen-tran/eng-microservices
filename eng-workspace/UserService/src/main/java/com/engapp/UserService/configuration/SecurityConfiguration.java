package com.engapp.UserService.configuration;

import com.engapp.UserService.service.OAuth2UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SecurityConfiguration {
    @Autowired
    private OAuth2UserService oAuth2UserService;

    public final static String[] PUBLIC_ENDPOINTS= {
            "/user/", "/user/register-user", "/internal/user/get-by-username"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS)
                .permitAll()
                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS)
                .permitAll()
                .anyRequest().authenticated()

        ).oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(infoEndpoint ->
                        infoEndpoint.userService(oAuth2UserService)));

        httpSecurity.addFilterBefore(new CustomJwtFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
}

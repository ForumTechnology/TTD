package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        // Chuỗi hóa mật khẩu
        return new BCryptPasswordEncoder();
    }

    /*
     * Bộ lọc bảo mật. Được sử dụng để xác thực, phân quyền, chống tấn công độc hại  cho các request */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // csrf(AbstractHttpConfigurer::disable) -> chống tấn công CSRF
        http.csrf(AbstractHttpConfigurer::disable)
                // xác định quyền cho các request
                .authorizeHttpRequests((requests) -> requests
                        .shouldFilterAllDispatcherTypes(true)
                        // Trước dấu / đầu tiên là localhost:8080
                        // /** -> chấp nhận mọi request bắt đầu = login
                        .requestMatchers("/user/showRegister").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login").loginPage("/")  // URL của trang đang nhập
                        .loginProcessingUrl("/login") // xử lý đăng nhập tự động
                        .defaultSuccessUrl("/showListBlog") // URL mặc định sau khi đăng nhập thành công
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll().logoutSuccessUrl("/"));
        return http.build();
    }
}

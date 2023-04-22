package mspessoas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration

@EnableWebSecurity
public class SecurityConfig {

	@Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        
		http
        .httpBasic(withDefaults())
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()
        )
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions().disable()); // permite o uso de iframes (Necessário para o console do H2)
       
		
		return http.build();
    }	
	
}

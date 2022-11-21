package ch.ffhs.sse.Security;

import ch.ffhs.sse.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    /**
     * Spring interface UserDetailsService returns our custom UserDetailService which
     * implements the Spring interface
     **/
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    /**
     * Validate UserDetails with the Spring auth. provider and return authentication
     * https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/dao-authentication-provider.html
     **/
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        /**  Encrypts the password parameter with BCrypt. So that an unencrypted password
         *   can be matched for the encrypted hash in the database **/
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        // provider.setPasswordEncoder(new SCryptPasswordEncoder());
        return provider;
    }

    /**
     * Security configuration specified in the filter chain
     * https://docs.spring.io/spring-security/reference/6.0.0-M5/servlet/architecture.html#servlet-securityfilterchain
     **/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /** Alle requests need to be authenticated. Endpoint /admin and /api/user
         *  need user Role ADMIN in addition to be accessed. **/
        http.authorizeRequests().antMatchers("/admin").hasAuthority("ADMIN").antMatchers("/api/user/").hasAuthority("ADMIN")
                //.antMatchers("/api/event").hasAuthority("USER")
                .anyRequest().authenticated().and().formLogin().permitAll().and().logout().permitAll();
        return http.build();
    }
}
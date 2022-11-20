package ch.ffhs.sse.Security;

import ch.ffhs.sse.Model.CustomUserDetails;
import ch.ffhs.sse.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
       // provider.setPasswordEncoder(new SCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
         http
                .authorizeRequests()
               // .antMatchers("/").permitAll()
                .antMatchers("/home").hasAuthority("USER")
                .antMatchers("/admin").hasAuthority("ADMIN")
                 /** Der Benutzer kann api/user trotzdem aufrufen!! BUG!**/
                .antMatchers("/api/user").hasAuthority("ADMIN")
                .antMatchers("/api/event").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
                return http.build();
    }
}


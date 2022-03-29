package com.hoang.travel.config;

import com.hoang.travel.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
    private static UserDetailsServiceImpl userDetailsService;

    public SpringSecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Configuration
    public static class SpringSecurityConfigurationAdmin extends WebSecurityConfigurerAdapter {

        public SpringSecurityConfigurationAdmin() {
            super();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                    .ignoring()
                    .antMatchers("/bootstrap/**", "/static/**", "/templates/**", "/resources/**", "/bootstrapuser/**", "/upload/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.authorizeRequests()
                    .antMatchers("/", "/login", "/logout").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/management/**").access("hasAnyRole('MANAGEMENT', 'ADMIN')")
                    .antMatchers("/comment/**").access("hasAnyRole('MANAGEMENT', 'ADMIN', 'USER')")
                    .and()
                    .exceptionHandling().accessDeniedPage("/403");
            http.authorizeRequests().and().formLogin()
                    .loginProcessingUrl("/user/j_spring_security_login")
                    .loginPage("/login2")
                    .failureUrl("/login2?error=true")
                    .usernameParameter("userName")
                    .passwordParameter("passWord")
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login2");


        }
    }
}

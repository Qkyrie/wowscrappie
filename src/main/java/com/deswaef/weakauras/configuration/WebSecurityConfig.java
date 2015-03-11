package com.deswaef.weakauras.configuration;

import com.deswaef.weakauras.configuration.social.FacebookLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private FacebookLogoutHandler facebookLogoutHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/contribute", "contribute/**").fullyAuthenticated()
                .antMatchers("/admin", "/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-do")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .failureUrl("/login?error").and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).addLogoutHandler(facebookLogoutHandler).and()
                .exceptionHandling().accessDeniedPage("/access?error")
                .and()
                .csrf().disable();
    }

    @Autowired
    private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService).passwordEncoder(new StandardPasswordEncoder());
    }
}

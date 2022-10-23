package org.training.library_management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CustomSecurity /*extends WebSecurityConfigurerAdapter*/ {
    @Autowired
    private CustomUserDetailsService service;

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(getEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * If the request is to the /api/librarian/add or /api/book/all endpoints, allow it. Otherwise, require authentication.
     *
     * @param security This is the HttpSecurity object that is used to configure the security of the application.
     * @return A SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain getFilterChain(HttpSecurity security) throws Exception {
        security
                .authorizeRequests()
                .antMatchers("/api/librarian/add", "/api/book/all")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        security.authenticationProvider(getProvider());
        return security.build();
    }

    /**
     * The function returns a DaoAuthenticationProvider object, which is a class that implements the AuthenticationProvider
     * interface
     *
     * @return A DaoAuthenticationProvider object.
     */
    @Bean
    public DaoAuthenticationProvider getProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(getEncoder());
        provider.setUserDetailsService(this.service);
        return provider;
    }

    /**
     * > This function is used to get the AuthenticationManager object from the AuthenticationConfiguration object
     *
     * @param config The AuthenticationConfiguration object that is used to configure the AuthenticationManager.
     * @return An AuthenticationManager
     */
    @Bean
    public AuthenticationManager getManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}

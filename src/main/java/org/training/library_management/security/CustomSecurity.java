package org.training.library_management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.training.library_management.security.jwt.JWTAuthenticationEntryPoint;
import org.training.library_management.security.jwt.JWTRequestFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurity /* extends WebSecurityConfigurerAdapter */ {
    @Autowired
    private CustomUserDetailsService service;

    @Autowired
    private JWTAuthenticationEntryPoint entryPoint;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JWTRequestFilter filter;

    private final String[] PATHS = {"/api/librarian/add", "/api/book/all", "/api/jwt/login",
            "/v3/api-docs", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**", "/v2/api-docs"};
    /*
     * @Override
     * protected void configure(HttpSecurity http) throws Exception {
     * http
     * .authorizeRequests().anyRequest().authenticated().and().httpBasic();
     * }
     *
     * protected void configure(AuthenticationManagerBuilder auth) throws Exception
     * {
     * auth.userDetailsService(service).passwordEncoder(getEncoder());
     * }
     *
     * @Bean
     * public AuthenticationManager authenticationManagerBean() throws Exception {
     * return super.authenticationManagerBean();
     * }
     */

    /*
     * allow only swagger ui, creating users and to know which books available in
     * the library, rest endpoints should be authenticated
     *
     * @param HttpSecurity
     *
     * @return securityFilter chain with configuration
     */
    @Bean
    public SecurityFilterChain getFilterChain(HttpSecurity security) throws Exception {
        security.csrf().disable()
                .cors().disable()
                .authorizeRequests()
//                .antMatchers(PATHS)
//                .permitAll()
//                .anyRequest()
//                .authenticated()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        security.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        security.authenticationProvider(getProvider());
        return security.build();
    }

    /*
     * The function returns a DaoAuthenticationProvider object, which is a class
     * that implements the AuthenticationProvider
     * interface
     *
     * @return A DaoAuthenticationProvider object.
     */
    @Bean
    public DaoAuthenticationProvider getProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(service);
        return provider;
    }

    /*
     * This function is used to get the AuthenticationManager object from the
     * AuthenticationConfiguration object
     *
     * @param config The AuthenticationConfiguration object that is used to
     * configure the AuthenticationManager.
     *
     * @return An AuthenticationManager
     */
    @Bean
    public AuthenticationManager getManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}

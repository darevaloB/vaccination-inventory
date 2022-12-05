package com.kruger.vaccination.security;

import com.kruger.vaccination.filter.JwtAuthenticationFilter;
import com.kruger.vaccination.filter.JwtAuthorizationFilter;
import com.kruger.vaccination.model.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author DIANA
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String[] WHITELIST = {
        "/swagger-resources/**",
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/swagger-ui/*",
        "/swagger-ui/index.html",
        "/v2/api-docs",
        };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter customAuthenticationFilter = new JwtAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/login").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/employee/**").hasAnyAuthority(Roles.ADMIN.toString())
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/employee/**").hasAnyAuthority(Roles.ADMIN.toString())
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/api/employee/**").hasAnyAuthority(Roles.ADMIN.toString(), Roles.EMPLOYEE.toString())
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/employee/**").hasAnyAuthority(Roles.ADMIN.toString())
                .and()
                .authorizeRequests().antMatchers(WHITELIST).permitAll()
                .and()
                .addFilter(customAuthenticationFilter);
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

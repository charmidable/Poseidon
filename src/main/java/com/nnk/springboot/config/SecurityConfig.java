package com.nnk.springboot.config;

import com.nnk.springboot.security.AuthenticationProviderService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    //=========================
    //=      Attributes       =
    //=========================
    private final AuthenticationProviderService authenticationProvider;

    //=========================
    //=     Constructors      =
    //=========================
    public SecurityConfig(AuthenticationProviderService authenticationProvider)
    {
        this.authenticationProvider = authenticationProvider;
    }


    //=========================
    //= Configuration methods =
    //=========================

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {

        // only to connect in order to generate token for the next.

        httpSecurity.csrf().and().cors().disable()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS,"/app/login/basicAuth").permitAll()
                    .antMatchers("/app/login").permitAll()
                .and()
                    .httpBasic()
                .and()
                    .formLogin().loginPage("/app/login").defaultSuccessUrl("/bidList/list", true);


        // only after Token generated.

        httpSecurity.csrf().and().cors().disable()
                    .authorizeRequests()
                    .antMatchers("/*/list").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/*/add", "/*/validate", "/*/update/*", "/*/delete/*", "/secure/article-details").hasRole("ADMIN")
                .and()
                    .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }
}
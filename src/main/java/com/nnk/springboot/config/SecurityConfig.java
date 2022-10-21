package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception
    {
        return  http.authorizeRequests().mvcMatchers("/home","/login","/css/**")                          .permitAll()
                                        .mvcMatchers("/user/**")                                          .hasAuthority("ADMIN")
                                        .mvcMatchers("/*/add","/*/validate", "/*/delete/*", "/*/update/*").hasAnyAuthority("ADMIN", "USER")
                                        .anyRequest()                                                              .authenticated()
                    .and()
                           .formLogin().loginPage("/login").defaultSuccessUrl("/bidList/list")
                    .and()
                           .oauth2Login().loginPage("/login").defaultSuccessUrl("/bidList/list")
                    .and()
                           .exceptionHandling().accessDeniedPage("/error")
                    .and()
                           .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .and()
                           .build();
    }
}

//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter
//{
//    //=========================
//    //=      Attributes       =
//    //=========================
//    private final AuthenticationProviderService authenticationProvider;
//
//    //=========================
//    //=     Constructors      =
//    //=========================
//    public SecurityConfig(AuthenticationProviderService authenticationProvider)
//    {
//        this.authenticationProvider = authenticationProvider;
//    }
//
//    //=========================
//    //= Configuration methods =
//    //=========================
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//    {
//        auth.authenticationProvider(authenticationProvider);
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception
//    {
//        httpSecurity.authorizeRequests().antMatchers("/login","/css/**","/js/**", "/images/**")           .permitAll()
//                                        .antMatchers("/user/*","/user/*/*","/app/secure/article-details") .hasAuthority("ADMIN")
//                                        .antMatchers("/*/add","/*/validate", "/*/delete/*", "/*/update/*").hasAnyAuthority("ADMIN", "USER")
//                                        .anyRequest()                                                                .authenticated()
//                                .and()
//                                        .formLogin().loginPage("/login").defaultSuccessUrl("/bidList/list")
//                                .and()
//                                        .oauth2Login().loginPage("/login").successHandler(new Oauth2AuthenticationSuccessHandler())
//                                .and()
//                                        .exceptionHandling().accessDeniedPage("/error")
//                                .and()
//                                        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//    }
//}
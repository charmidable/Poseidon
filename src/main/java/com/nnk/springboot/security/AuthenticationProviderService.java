package com.nnk.springboot.security;

import com.nnk.springboot.service.JpaUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider
{
    private final JpaUserDetailsService jpaUserDetailsService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public AuthenticationProviderService(JpaUserDetailsService jpaUserDetailsService)
    {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        SecurityUser user = jpaUserDetailsService.loadUserByUsername(username);

        return checkPassword(user, password);
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }


    Authentication checkPassword(SecurityUser user, String rawPassword)
    {
        if (encoder.matches(rawPassword, user.getPassword()))
        {
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        }
        else
        {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    public String encodePassword(CharSequence rawPassword)
    {
        return encoder.encode(rawPassword.toString());
    }
}

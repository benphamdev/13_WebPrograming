package org.com.test4.config;

import org.com.test4.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class UserPrincipal implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    String userName;
    String password = null;
    String role = null;
    Set<SimpleGrantedAuthority> authorities;

    public UserPrincipal(User user) {
        userName = user.getUserName();
        password = user.getPassword();
        role = user.getRole();
        authorities = Collections.singleton(new SimpleGrantedAuthority(role));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

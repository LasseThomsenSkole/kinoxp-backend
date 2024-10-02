package org.example.kinoxpbackend.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {
    private final UserPrincipal userPrincipal;
    public UserPrincipalAuthenticationToken(UserPrincipal userPrincipal, UserPrincipal userPrincipal1) {
        super(userPrincipal.getAuthorities());
        this.userPrincipal = userPrincipal1;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipal getPrincipal() {
        return userPrincipal;
    }
}

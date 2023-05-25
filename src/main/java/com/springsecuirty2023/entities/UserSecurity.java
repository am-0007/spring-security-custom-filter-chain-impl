package com.springsecuirty2023.entities;

import com.springsecuirty2023.entities.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserSecurity implements UserDetails {

    private User user;

    public UserSecurity(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthority()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    private Set<Role> getAuthority() {
        Set<String> roleInString = StringUtils.commaDelimitedListToSet(user.getRole());
        Set<Role> roles = new HashSet<>();
        roleInString.forEach(role -> {
            switch (role) {
                case "admin" -> roles.add(Role.ADMIN);
                case "user" -> roles.add(Role.USER);
                case "super_admin" -> roles.add(Role.SUPER_ADMIN);
                default -> roles.add(Role.OTHER);
            }
        });
        System.out.println(roles);
        return roles;
    }
}

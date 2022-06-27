package com.igormeshalkin.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(Permission.SEE_ALL_USERS,
            Permission.SEE_YOUR_PROFILE,
            Permission.UPDATE_ANY_PROFILES,
            Permission.UPDATE_YOUR_PROFILE,
            Permission.DELETE_ANY_PROFILES,
            Permission.CREATE_REAL_ESTATE,
            Permission.SEE_ANY_REAL_ESTATE,
            Permission.UPDATE_ANY_REAL_ESTATE,
            Permission.UPDATE_YOUR_REAL_ESTATE,
            Permission.DELETE_ANY_REAL_ESTATE,
            Permission.DELETE_YOUR_REAL_ESTATE)),
    USER(Set.of(Permission.SEE_YOUR_PROFILE,
            Permission.UPDATE_YOUR_PROFILE,
            Permission.CREATE_REAL_ESTATE,
            Permission.SEE_ANY_REAL_ESTATE,
            Permission.UPDATE_YOUR_REAL_ESTATE,
            Permission.DELETE_YOUR_REAL_ESTATE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}

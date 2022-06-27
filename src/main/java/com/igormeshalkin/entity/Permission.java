package com.igormeshalkin.entity;

public enum Permission {
    SEE_ALL_USERS("users:show all"),
    SEE_YOUR_PROFILE("users:see your profile"),
    UPDATE_ANY_PROFILES("users:update any profiles"),
    UPDATE_YOUR_PROFILE("users:update your profile"),
    DELETE_ANY_PROFILES("users:delete any profiles"),
    CREATE_REAL_ESTATE("real estate:create"),
    SEE_ANY_REAL_ESTATE("real estate:see any"),
    UPDATE_ANY_REAL_ESTATE("real estate:update any"),
    UPDATE_YOUR_REAL_ESTATE("real estate:update your"),
    DELETE_ANY_REAL_ESTATE("real estate:delete any"),
    DELETE_YOUR_REAL_ESTATE("real estate:delete your");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

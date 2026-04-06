package io.github.rveggab.auth.domain.model.enums;

public enum AppStatus {
    A("active"),
    B("blocked"),
    I("inactive");

    private final String permission;

    AppStatus(String permission) {
        this.permission = permission;
    }

    public boolean canAccess() {
        return this == A;
    }
}

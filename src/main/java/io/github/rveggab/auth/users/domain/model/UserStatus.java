package io.github.rveggab.auth.users.domain.model;

public enum UserStatus {
    A("active"),
    B("blocked"),
    R("revoked"),
    I("inactive");

    private final String status;

    UserStatus(String status){
        this.status = status;
    }

    public boolean canAuth(){
        return this == A;
    }
}

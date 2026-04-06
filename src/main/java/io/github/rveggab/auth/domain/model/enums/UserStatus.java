package io.github.rveggab.auth.domain.model.enums;

public enum UserStatus {
    A("active"),
    I("inactive"),
    B("blocked"),
    R("revoked");

    private final String description;

    UserStatus(String description){
        this.description = description;
    }

    public boolean canAuth(){
        return  this == A;
    }
}

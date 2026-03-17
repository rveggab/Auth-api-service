package io.github.rveggab.auth.domain.model.enums;

public enum UserRoles {

    A("admin"),
    U("user");

    private final String accessRole;

    UserRoles(String accessRole){
        this.accessRole = accessRole;
    }

    public String getAccessRole(){
        return accessRole;
    }
}

package io.github.rveggab.auth.domain.model.identity;

import io.github.rveggab.auth.domain.model.enums.AppStatus;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class App {
    private Long id;
    private String name;
    private String clientId;
    private String clientSecret;
    private String url;
    private AppStatus status;

    public void assignDefaultStatus(){
        if (status == null)
            this.status = AppStatus.A;
    }

    public void createId(String client){
        this.clientId = client;
    }

    public void createSecret(String secret){
        this.clientSecret = secret;
    }

    public boolean isActive(){
        return status == AppStatus.A;
    }

    public void changeStatus(AppStatus newStatus){
        this.status = newStatus;
    }

    public void changeAppName(String newAppName){
        this.name = newAppName;
    }

    public void changeUrl(String newUrl){
        this.url = newUrl;
    }

    public void changeSecret(String newSecret){
        this.clientSecret = newSecret;
    }

    public void changeClientId(String clientId){
        this.clientId = clientId;
    }
}

package io.github.rveggab.auth.infrastructure.adapter.mapper.app;

import io.github.rveggab.auth.domain.model.identity.App;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.response.apps.AppDetailResponse;

import java.util.List;

public class AppMapperResponse {
    public static AppDetailResponse toAppDetail(App app){
        return AppDetailResponse.builder()
                .appName(app.getName())
                .url(app.getUrl())
                .status(app.getStatus().toString())
                .build();
    }

    public static AppDetailResponse lastNewLog(App app){
        return AppDetailResponse.builder()
                .idClient(app.getClientId())
                .secret(app.getClientSecret())
                .build();
    }

    public static List<AppDetailResponse> toDetailList(List<App>apps){
        return apps.stream()
                .map(AppMapperResponse::toAppDetail)
                .toList();
    }
}

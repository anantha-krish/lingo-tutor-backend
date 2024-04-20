package com.lingotutor.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiRoutes = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecuredRoute =
            request -> openApiRoutes
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
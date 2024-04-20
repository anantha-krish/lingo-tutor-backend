package com.lingotutor.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.lingotutor.apigateway.util.JwtUtil;

@Component
public class LingoAuthFilter extends AbstractGatewayFilterFactory<LingoAuthFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public LingoAuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
			boolean isSecuredApiRoute = validator.isSecuredRoute.test(request);
			HttpHeaders headers = request.getHeaders();
            //header contains token or not
			boolean hasAuthHeader = headers.containsKey(HttpHeaders.AUTHORIZATION);
			
			if (isSecuredApiRoute) {  
				if (!hasAuthHeader) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "missing authorization header");
                }

                String authHeader = headers.get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                	// could you Auth Service, but validating JWT is faster & secure way
                    jwtUtil.validateToken(authHeader);

                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "un authorized access to application");       
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
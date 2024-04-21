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
public class LingoApiAuthFilter extends AbstractGatewayFilterFactory<LingoApiAuthFilter.Config> {

	@Autowired
	private RouteValidator validator;

	@Autowired
	private JwtUtil jwtUtil;

	public LingoApiAuthFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {

		String AUTH_PREFIX = "Bearer ";

		return ((exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();

			boolean isSecuredApiRoute = validator.isSecuredRoute.test(request);
			HttpHeaders headers = request.getHeaders();
			// header contains token or not
			boolean hasAuthHeader = headers.containsKey(HttpHeaders.AUTHORIZATION);

			if (isSecuredApiRoute) {
				if (!hasAuthHeader) {
					throw new ResponseStatusException(HttpStatus.FORBIDDEN, "missing authorization header");
				}

				String token = headers.get(HttpHeaders.AUTHORIZATION).get(0);

				if (token != null && token.startsWith(AUTH_PREFIX)) {
					token = token.substring(AUTH_PREFIX.length());
				}
				try {
					// could you Auth Service, but validating JWT is faster & secure way
					jwtUtil.validateToken(token);
					request = request.mutate().header("username", jwtUtil.extractUsername(token)).build();

				} catch (Exception e) {
					System.out.println("invalid access...!");
					throw new ResponseStatusException(HttpStatus.FORBIDDEN, "un authorized access to application");
				}
			}

			return chain.filter(exchange.mutate().request(request).build());

		});

	}

	public static class Config {

	}
}
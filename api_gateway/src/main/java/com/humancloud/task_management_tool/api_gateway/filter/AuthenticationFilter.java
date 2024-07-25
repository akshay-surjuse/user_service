package com.humancloud.task_management_tool.api_gateway.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.humancloud.task_management_tool.api_gateway.utils.JwtUtils;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private RouteValidator routeValidator;
	
	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (routeValidator.isSecured.test(exchange.getRequest())) {
				// header contains token or not
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing authorization header");
				}

				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				System.out.println("authHeader: " + authHeader);
				String bearerToken = null;
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					bearerToken = authHeader.substring(7);
					System.out.println("bearerToken: " + bearerToken);
				}
				try {
					List roles = jwtUtils.extractRoles(bearerToken);
					jwtUtils.validateToken(bearerToken);
					System.out.println("Token is valid");
				} catch (Exception e) {
					System.out.println("invalid access...!");
					throw new RuntimeException("un-authorized access to application");
				}
				//jwtUtils.validateToken(bearerToken);
			}
			return chain.filter(exchange);
		});
	}
	
	public static class Config{
		
	}
}

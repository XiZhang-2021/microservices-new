package org.programming.apigateway.filter;

import org.apache.http.HttpHeaders;
import org.programming.apigateway.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;
    @Autowired
    private JwtService jwtService;
    public AuthenticationFilter() {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain)->{
            if(validator.isSecured.test(exchange.getRequest())){
                //header contains token or not
                System.out.println(exchange.getRequest().getHeaders());
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authorication header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(org.springframework.http.HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader!= null && authHeader.startsWith("Bearer")){
                    authHeader = authHeader.substring(7);
                }
                try{
                    jwtService.validToken(authHeader);
                    String username = jwtService.extractUsername(authHeader);
                    String role = jwtService.extractRole(authHeader);
                    exchange.getAttributes().put("user", username);
                    exchange.getAttributes().put("role", role);
                }catch(Exception e){
                    throw new RuntimeException("not valid token");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}

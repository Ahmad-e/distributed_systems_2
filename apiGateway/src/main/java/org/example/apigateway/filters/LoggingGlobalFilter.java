package com.example.apigateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import org.springframework.http.server.reactive.ServerHttpRequest;

@Component
public class LoggingGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        String path = request.getPath().toString();
        String method = request.getMethod().name();

        System.out.println("REQUEST: [" + method + "] " + path + " at " + LocalDateTime.now());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            int statusCode = exchange.getResponse().getStatusCode().value();
            System.out.println("RESPONSE: " + statusCode + " for " + path);
            // من هنا يمكنك إرسال البيانات إلى log-service عبر REST أو Kafka
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

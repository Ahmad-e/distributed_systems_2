package org.example.apigateway.filters;

import org.example.apigateway.dto.LogEntryDto;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class LoggingGlobalFilter implements GlobalFilter, Ordered {

    private final WebClient webClient = WebClient.create("http://localhost:8085"); // log-service base URL

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        String method = request.getMethod().name();

        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    int statusCode = exchange.getResponse().getStatusCode().value();

                    LogEntryDto log = new LogEntryDto();
                    log.setMethod(method);
                    log.setPath(path);
                    log.setStatusCode(statusCode);
                    log.setTimestamp(LocalDateTime.now());

                    webClient.post()
                            .uri("/logs")
                            .bodyValue(log)
                            .retrieve()
                            .bodyToMono(Void.class)
                            .subscribe();
                })
        );
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
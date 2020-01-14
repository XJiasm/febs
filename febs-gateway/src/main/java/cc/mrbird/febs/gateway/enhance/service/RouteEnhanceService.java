package cc.mrbird.febs.gateway.enhance.service;

import cc.mrbird.febs.common.entity.constant.FebsConstant;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author MrBird
 */
public interface RouteEnhanceService {

    Mono<Void> filterBalckList(ServerWebExchange exchange);

    Mono<Void> filterRateLimit(ServerWebExchange exchange);

    @Async(FebsConstant.ASYNC_POOL)
    void saveRequestLogs(ServerWebExchange exchange);

    @Async(FebsConstant.ASYNC_POOL)
    void saveBlockLogs(ServerWebExchange exchange);

    @Async(FebsConstant.ASYNC_POOL)
    void saveRateLimitLogs(ServerWebExchange exchange);
}

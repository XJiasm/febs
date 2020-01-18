package cc.mrbird.febs.gateway.enhance.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.gateway.enhance.entity.RateLimitLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author MrBird
 */
public interface RateLimitLogService {
    Mono<RateLimitLog> create(RateLimitLog rateLimitLog);

    Flux<RateLimitLog> delete(String ids);

    Flux<RateLimitLog> findPages(QueryRequest request, RateLimitLog rateLimitLog);

    Mono<Long> findCount(RateLimitLog rateLimitLog);
}

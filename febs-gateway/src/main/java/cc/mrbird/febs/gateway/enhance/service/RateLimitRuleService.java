package cc.mrbird.febs.gateway.enhance.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.gateway.enhance.entity.RateLimitRule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RateLimitRuleService {

    Mono<RateLimitRule> create(RateLimitRule rateLimitRule);

    Flux<RateLimitRule> findAll();

    Flux<RateLimitRule> findByRequestUriAndRequestMethod(String requestUri, String requestMethod);

    Flux<RateLimitRule> findPages(QueryRequest request, RateLimitRule rateLimitRule);

    Mono<Long> findCount(RateLimitRule rateLimitRule);

    Mono<RateLimitRule> update(RateLimitRule rateLimitRule);

    Flux<RateLimitRule> delete(String ids);
}

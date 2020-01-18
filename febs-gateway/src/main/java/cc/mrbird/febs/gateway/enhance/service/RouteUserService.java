package cc.mrbird.febs.gateway.enhance.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.gateway.enhance.entity.RouteUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteUserService {
    Mono<RouteUser> create(RouteUser user);

    Mono<RouteUser> update(RouteUser routeUser);

    Flux<RouteUser> delete(String ids);

    Mono<RouteUser> findByUsername(String username);

    Flux<RouteUser> findPages(QueryRequest request, RouteUser routeUser);

    Mono<Long> findCount(RouteUser routeUser);
}

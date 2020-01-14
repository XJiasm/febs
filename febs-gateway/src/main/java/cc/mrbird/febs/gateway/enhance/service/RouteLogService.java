package cc.mrbird.febs.gateway.enhance.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.gateway.enhance.entity.RouteLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteLogService {

    Flux<RouteLog> findAll();

    Mono<RouteLog> create(RouteLog routeLog);

    Flux<RouteLog> delete(String ids);

    Flux<RouteLog> findPages(QueryRequest request, RouteLog routeLog);

    Mono<Long> findCount(RouteLog routeLog);
}

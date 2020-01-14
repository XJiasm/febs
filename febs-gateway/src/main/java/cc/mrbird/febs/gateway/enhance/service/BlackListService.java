package cc.mrbird.febs.gateway.enhance.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.gateway.enhance.entity.BlackList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlackListService {

    Flux<BlackList> findAll();

    Mono<BlackList> create(BlackList blackList);

    Mono<BlackList> update(BlackList blackList);

    Flux<BlackList> delete(String ids);

    Flux<BlackList> findPages(QueryRequest request, BlackList blackList);

    Mono<Long> findCount(BlackList blackList);

    Flux<BlackList> findByCondition(String ip, String requestUri, String requestMethod);

}

package cc.mrbird.febs.gateway.enhance.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.gateway.enhance.entity.BlockLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlockLogService {

    Mono<BlockLog> create(BlockLog blockLog);

    Flux<BlockLog> delete(String ids);

    Flux<BlockLog> findPages(QueryRequest request, BlockLog blockLog);

    Mono<Long> findCount(BlockLog blockLog);
}

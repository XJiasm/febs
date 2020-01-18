package cc.mrbird.febs.gateway.enhance.mapper;

import cc.mrbird.febs.gateway.enhance.entity.RouteUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RouteUserMapper extends ReactiveMongoRepository<RouteUser, String> {

    Mono<RouteUser> findByUsername(String username);

    Flux<RouteUser> deleteByIdIn(String[] ids);
}

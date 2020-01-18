package cc.mrbird.febs.gateway.enhance.mapper;

import cc.mrbird.febs.gateway.enhance.entity.RateLimitLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RateLimitLogMapper extends ReactiveMongoRepository<RateLimitLog, String> {

    Flux<RateLimitLog> deleteByIdIn(String[] ids);
}

package cc.mrbird.febs.gateway.enhance.mapper;

import cc.mrbird.febs.gateway.enhance.entity.BlockLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * @author MrBird
 */
public interface BlockLogMapper extends ReactiveMongoRepository<BlockLog, String> {

    Flux<BlockLog> deleteByIdIn(String[] ids);
}

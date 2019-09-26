package cc.mrbird.febs.auth.service;

import cc.mrbird.febs.common.service.RedisService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * 自定义oauth客户端适配
 * @author Yuuki
 * @date 2019年9月23日17:23:04
 */
@Service
@Slf4j
public class RedisClientDetailsService extends JdbcClientDetailsService {

    @Autowired
    RedisService redisService;

    public RedisClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }


    /**
     * 缓存client的redis key，这里是hash结构存储
     */
    private static final String CACHE_CLIENT_KEY = "client_details";

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = null;

        // 先从redis获取
        String value = (String) redisService.hget(CACHE_CLIENT_KEY,clientId);
        if (Strings.isBlank(value)) {
            clientDetails = cacheAndGetClient(clientId);
        } else {
            clientDetails = JSONObject.parseObject(value, BaseClientDetails.class);
        }

        return clientDetails;
    }

    /**
     * 缓存client并返回client
     *
     * @param clientId
     */
    public ClientDetails cacheAndGetClient(String clientId) {
        // 从数据库读取
        ClientDetails clientDetails = super.loadClientByClientId(clientId);
        if (clientDetails != null) {// 写入redis缓存
            redisService.hset(CACHE_CLIENT_KEY,clientId,JSONObject.toJSONString(clientDetails));
            log.info("缓存clientId:{},{}", clientId, clientDetails);
        }

        return clientDetails;
    }

    /**
     * 删除redis缓存
     *
     * @param clientId
     */
    public void removeRedisCache(String clientId) {
        redisService.hdel(CACHE_CLIENT_KEY,clientId);
    }

    /**
     * 将oauth_client_details全表刷入redis
     */
    public void loadAllClientToCache() {
        if (redisService.hasKey(CACHE_CLIENT_KEY)) {
            return;
        }
        log.info("将oauth_client_details全表刷入redis");

        List<ClientDetails> list = super.listClientDetails();
        if (list.isEmpty()) {
            log.error("oauth_client_details表数据为空，请检查");
            return;
        }

        list.forEach(client -> {
            redisService.hset(CACHE_CLIENT_KEY,client.getClientId(),JSONObject.toJSONString(client));
        });
    }

}

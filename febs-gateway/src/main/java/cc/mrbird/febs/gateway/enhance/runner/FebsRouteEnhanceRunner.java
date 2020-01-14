package cc.mrbird.febs.gateway.enhance.runner;

import cc.mrbird.febs.gateway.enhance.service.BlackListService;
import cc.mrbird.febs.gateway.enhance.service.RateLimitRuleService;
import cc.mrbird.febs.gateway.enhance.service.RouteEnhanceCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author MrBird
 */
@Slf4j
public class FebsRouteEnhanceRunner implements ApplicationRunner {

    @Autowired
    private RouteEnhanceCacheService cacheService;
    @Autowired
    private BlackListService blackListService;
    @Autowired
    private RateLimitRuleService rateLimitRuleService;

    @Override
    public void run(ApplicationArguments args) {
        cacheService.saveAllBlackList(blackListService.findAll());
        cacheService.saveAllRateLimitRules(rateLimitRuleService.findAll());
    }
}

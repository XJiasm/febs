package cc.mrbird.febs.common.logging.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: xuefrye
 */
@Data
@ConfigurationProperties(prefix = "febs.log")
public class FebsLogProperties {
    /**
     * 日志上传地址
     */
    private String logstashHost = "127.0.0.1:4560";

    /**
     * 是否开启controller层api调用的日志
     */
    private String enableLogForController;

    /**
     * 是否开启ELK日志收集
     */
    private String enableElk;
}

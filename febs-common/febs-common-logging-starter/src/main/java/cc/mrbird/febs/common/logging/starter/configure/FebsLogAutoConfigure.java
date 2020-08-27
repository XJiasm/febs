package cc.mrbird.febs.common.logging.starter.configure;

import cc.mrbird.febs.common.logging.starter.properties.FebsLogProperties;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.spi.FilterReply;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.HashMap;

/**
 * @author: xuefrye
 */
@Configuration
@EnableConfigurationProperties(FebsLogProperties.class)
public class FebsLogAutoConfigure {
    private final FebsLogProperties properties;

    @Value("${spring.application.name}")
    private String applicationName;

    public FebsLogAutoConfigure(FebsLogProperties properties) {
        this.properties = properties;
    }

    private static final LoggerContext context;
    private static final Logger rootLogger;

    static{
        context = (LoggerContext) LoggerFactory.getILoggerFactory();
        rootLogger = context.getLogger("ROOT");
    }

    @ConditionalOnProperty(name = "febs.log.enable-elk", havingValue = "true", matchIfMissing = true)
    @Bean
    public void enableElk() throws JsonProcessingException {
        LogstashTcpSocketAppender appender = new LogstashTcpSocketAppender();
        LogstashEncoder encoder = new LogstashEncoder();

        HashMap<String, String> customFields = new HashMap<>();
        customFields.put("application-name", applicationName);
        String customFieldsString = new ObjectMapper().writeValueAsString(customFields);
        encoder.setCustomFields(customFieldsString);

        appender.setEncoder(encoder);
        appender.addDestination(properties.getLogstashHost());
        appender.setName("logstash[" + applicationName + "]");
        appender.start();
        appender.setContext(context);
        rootLogger.addAppender(appender);
    }
}

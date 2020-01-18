package cc.mrbird.febs.common.configure;

import cc.mrbird.febs.common.entity.constant.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Base64Utils;

/**
 * OAuth2 Feign配置
 *
 * @author MrBird
 */
public class FebsOAuth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            // 请求头中添加 Gateway Token
            String zuulToken = new String(Base64Utils.encode(FebsConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(FebsConstant.GATEWAY_TOKEN_HEADER, zuulToken);
            // 请求头中添加原请求头中的 Token
            String authorizationToken = FebsUtil.getCurrentTokenValue();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, FebsConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
        };
    }
}

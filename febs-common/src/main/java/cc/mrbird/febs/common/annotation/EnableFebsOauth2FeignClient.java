package cc.mrbird.febs.common.annotation;

import cc.mrbird.febs.common.configure.FebsOauth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author MrBird
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsOauth2FeignConfigure.class)
public @interface EnableFebsOauth2FeignClient {
}

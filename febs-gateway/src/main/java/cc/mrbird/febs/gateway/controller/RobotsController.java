package cc.mrbird.febs.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author MrBird
 */
@RestController
public class RobotsController {

    @GetMapping("/robots.txt")
    public Mono<String> robotsTxt() {
        StringBuilder builder = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator", "\n");
        builder.append("User-agent: Baiduspider").append(lineSeparator);
        builder.append("Disallow: ").append("/").append(lineSeparator);
        builder.append("User-agent: Googlebot").append(lineSeparator);
        builder.append("Disallow: ").append("/").append(lineSeparator);
        return Mono.just(builder.toString());
    }
}

package cc.mrbird.febs.gateway.enhance.controller;

import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.gateway.enhance.auth.JWTTokenHelper;
import cc.mrbird.febs.gateway.enhance.service.RouteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author MrBird
 */
@RestController
@RequestMapping("route")
public class RouteLoginController {

    @Autowired
    private JWTTokenHelper tokenHelper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RouteUserService routeUserService;

    @GetMapping("login")
    public Mono<ResponseEntity<FebsResponse>> login(String username, String password) {
        String error = "认证失败，用户名或密码错误";
        return routeUserService.findByUsername(username)
                .map(u -> passwordEncoder.matches(password, u.getPassword()) ?
                        ResponseEntity.ok(new FebsResponse().data(tokenHelper.generateToken(u))) :
                        new ResponseEntity<>(new FebsResponse().message(error), HttpStatus.INTERNAL_SERVER_ERROR))
                .defaultIfEmpty(new ResponseEntity<>(new FebsResponse().message(error), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}

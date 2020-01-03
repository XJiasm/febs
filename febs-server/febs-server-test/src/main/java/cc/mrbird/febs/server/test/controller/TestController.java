package cc.mrbird.febs.server.test.controller;

import cc.mrbird.febs.common.entity.CurrentUser;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.system.SystemUser;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.server.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MrBird
 */
@RestController
@RequestMapping
public class TestController {

    @Autowired
    private IUserService userService;

    /**
     * 用于演示 Feign调用受保护的远程方法
     */
    @GetMapping("user/list")
    public FebsResponse getRemoteUserList(QueryRequest request, SystemUser user) {
        return userService.userList(request, user);
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("user")
    public Map<String, Object> currentUser(Principal principal) {
        Map<String, Object> map = new HashMap<>(5);
        map.put("currentUser", FebsUtil.getCurrentUser());
        map.put("currentUsername", FebsUtil.getCurrentUsername());
        map.put("currentUserAuthority", FebsUtil.getCurrentUserAuthority());
        map.put("currentTokenValue", FebsUtil.getCurrentTokenValue());
        map.put("currentRequestIpAddress", FebsUtil.getCurrentRequestIpAddress());
        return map;
    }
}

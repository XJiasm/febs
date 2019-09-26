package cc.mrbird.febs.auth.service.impl;


import cc.mrbird.febs.auth.mapper.OauthCliendetailsMapper;
import cc.mrbird.febs.auth.service.IOauthCliendetailsService;
import cc.mrbird.febs.auth.service.RedisClientDetailsService;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.auth.OauthCliendetails;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  Service实现
 *
 * @author Yuuki
 * @date 2019年9月23日17:22:03
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OauthCliendetailsServiceImpl extends ServiceImpl<OauthCliendetailsMapper, OauthCliendetails> implements IOauthCliendetailsService {

    @Autowired
    private OauthCliendetailsMapper oauthCliendetailsMapper;
    @Autowired
    private RedisClientDetailsService redisClientDetailsService;

    @Override
    public IPage<OauthCliendetails> findOauthCliendetailss(QueryRequest request, OauthCliendetails oauthCliendetails) {
        LambdaQueryWrapper<OauthCliendetails> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<OauthCliendetails> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<OauthCliendetails> findOauthCliendetailss(OauthCliendetails oauthCliendetails) {
	    LambdaQueryWrapper<OauthCliendetails> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createOauthCliendetails(OauthCliendetails oauthCliendetails) {
        this.save(oauthCliendetails);
    }

    @Override
    @Transactional
    public void updateOauthCliendetails(OauthCliendetails oauthCliendetails) {
        this.saveOrUpdate(oauthCliendetails);
        // 更新客户端缓存
        redisClientDetailsService.cacheAndGetClient(oauthCliendetails.getClientId());
    }

    @Override
    @Transactional
    public void deleteOauthCliendetails(OauthCliendetails oauthCliendetails) {
        LambdaQueryWrapper<OauthCliendetails> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	    // 移除客户端缓存
	    redisClientDetailsService.removeRedisCache(oauthCliendetails.getClientId());
	}
}

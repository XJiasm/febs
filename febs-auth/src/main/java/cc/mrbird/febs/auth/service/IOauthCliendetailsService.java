package cc.mrbird.febs.auth.service;


import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.auth.OauthCliendetails;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author Yuuki
 * @date 2019年9月23日17:14:38
 */
public interface IOauthCliendetailsService extends IService<OauthCliendetails> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param oauthCliendetails oauthCliendetails
     * @return IPage<OauthCliendetails>
     */
    IPage<OauthCliendetails> findOauthCliendetailss(QueryRequest request, OauthCliendetails oauthCliendetails);

    /**
     * 查询（所有）
     *
     * @param oauthCliendetails oauthCliendetails
     * @return List<OauthCliendetails>
     */
    List<OauthCliendetails> findOauthCliendetailss(OauthCliendetails oauthCliendetails);

    /**
     * 新增
     *
     * @param oauthCliendetails oauthCliendetails
     */
    void createOauthCliendetails(OauthCliendetails oauthCliendetails);

    /**
     * 修改
     *
     * @param oauthCliendetails oauthCliendetails
     */
    void updateOauthCliendetails(OauthCliendetails oauthCliendetails);

    /**
     * 删除
     *
     * @param oauthCliendetails oauthCliendetails
     */
    void deleteOauthCliendetails(OauthCliendetails oauthCliendetails);
}

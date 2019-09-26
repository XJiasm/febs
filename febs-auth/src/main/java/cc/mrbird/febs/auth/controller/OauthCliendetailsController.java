package cc.mrbird.febs.auth.controller;

import cc.mrbird.febs.auth.service.IOauthCliendetailsService;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.auth.OauthCliendetails;
import cc.mrbird.febs.common.exception.FebsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static cc.mrbird.febs.common.utils.FebsUtil.getDataTable;

/**
 *  Controller
 *
 * @author MrBird
 * @date 2019-09-09 14:13:23
 */
@Slf4j
@Validated
@RestController
@RequestMapping("client")
public class OauthCliendetailsController {

    @Autowired
    private IOauthCliendetailsService oauthCliendetailsService;


    @GetMapping
    @PreAuthorize("hasAnyAuthority('client:view')")
    public FebsResponse oauthCliendetailsList(QueryRequest request, OauthCliendetails oauthCliendetails) {
        Map<String, Object> dataTable = getDataTable(this.oauthCliendetailsService.findOauthCliendetailss(request, oauthCliendetails));
        return new FebsResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('client:add')")
    public void addOauthCliendetails(@Valid OauthCliendetails oauthCliendetails) throws FebsException {
        try {
            this.oauthCliendetailsService.createOauthCliendetails(oauthCliendetails);
        } catch (Exception e) {
            String message = "新增OauthCliendetails失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('client:delete')")
    public void deleteOauthCliendetails(OauthCliendetails oauthCliendetails) throws FebsException {
        try {
            this.oauthCliendetailsService.deleteOauthCliendetails(oauthCliendetails);
        } catch (Exception e) {
            String message = "删除OauthCliendetails失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('client:update')")
    public void updateOauthCliendetails(OauthCliendetails oauthCliendetails) throws FebsException {
        try {
            this.oauthCliendetailsService.updateOauthCliendetails(oauthCliendetails);
        } catch (Exception e) {
            String message = "修改OauthCliendetails失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}

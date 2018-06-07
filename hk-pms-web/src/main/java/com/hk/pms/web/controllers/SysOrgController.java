package com.hk.pms.web.controllers;

import com.hk.commons.util.JsonUtils;
import com.hk.core.data.commons.query.QueryPage;
import com.hk.core.data.jpa.query.JpaQueryModel;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysOrg;
import com.hk.pms.core.servcie.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huangkai
 * @date 2018-05-23 11:26
 */
@RestController
@RequestMapping("orgs")
public class SysOrgController extends BaseController {

    @Autowired
    private SysOrgService orgService;

    @RequestMapping()
    @PreAuthorize("hasAuthority('org_list')")
    public String list(@RequestBody JpaQueryModel<SysOrg> query) {
        QueryPage<SysOrg> pageable = orgService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageable));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('org_list')")
    public String detail(@PathVariable String id) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(orgService.findOne(id)));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('org_delete')")
    public String deleteById(@PathVariable String id) {
        orgService.deleteById(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('org_create','org_edit')")
    public String saveOrUpdate(SysOrg org, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        orgService.saveOrUpdate(org);
        return JsonUtils.toJSONString(JsonResult.success());
    }
}

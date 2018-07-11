package com.hk.pms.web.controllers;

import com.hk.commons.util.JsonUtils;
import com.hk.commons.util.date.DatePattern;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.data.commons.query.QueryModel;
import com.hk.core.data.commons.query.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysPermission;
import com.hk.pms.core.servcie.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: kevin
 * @date 2018-05-08 15:33
 */
@RestController
@RequestMapping("permissions")
public class PermissionController extends BaseController {

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 查询
     *
     * @param query 查询参数
     * @return
     */
    @RequestMapping()
    @PreAuthorize("hasAuthority('permission_list')")
    public String queryByPage(@RequestBody QueryModel query) {
        QueryPage<SysPermission> pageResult = permissionService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageResult), "app", "parent", "child");
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('permission_delete')")
    public String deleteById(@PathVariable String id) {
        permissionService.deleteById(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('permission_create','permission_edit')")
    public String insertOrUpdate(SysPermission permission, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.error(errors.getFieldError().getDefaultMessage()));
        }
        permissionService.insertOrUpdate(permission);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('permission_list')")
    public String detail(@PathVariable String id) {
        SysPermission permission = permissionService.findOne(id);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(permission), DatePattern.YYYY_MM_DD_HH_MM_SS, "app", "parent", "child");
    }

    /**
     * 获取当前登陆的所有权限列表
     *
     * @return
     */
    @GetMapping("my")
    public String getMyPermissionList() {
        UserPrincipal principal = getPrincipal();
        return JsonUtils.toJSONString(JsonResult.success(principal.getPermissionByAppId(principal.getAppId())));
    }

    /**
     * 获取用户权限详情
     *
     * @return
     */
    @GetMapping("my/detail")
    public String getMyPermissionDetailList() {
        List<SysPermission> permissionList = permissionService.getCurrentUserPermissionList(getPrincipal().getAppId());
        return JsonUtils.toJSONStringExcludes(JsonResult.success(permissionList), "app");
    }

}

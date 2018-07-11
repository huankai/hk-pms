package com.hk.pms.web.controllers;

import com.hk.commons.util.JsonUtils;
import com.hk.core.data.commons.query.QueryModel;
import com.hk.core.data.commons.query.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.core.web.controller.BaseController;
import com.hk.pms.core.domain.SysUser;
import com.hk.pms.core.servcie.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author: kevin
 * @date 2018-5-13 11:37
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    @Autowired
    private SysUserService userService;

    @RequestMapping()
    @PreAuthorize("hasAuthority('user_list')")
    public String list(@RequestBody QueryModel<SysUser> query) {
        QueryPage<SysUser> pageable = userService.queryForPage(query);
        return JsonUtils.toJSONStringExcludes(JsonResult.success(pageable));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('user_list')")
    public String detail(@PathVariable String id) {
        return JsonUtils.toJSONStringExcludes(JsonResult.success(userService.getOne(id)));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user_delete')")
    public String deleteById(@PathVariable String id) {
        userService.deleteById(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('user_create','user_edit')")
    public String saveOrUpdate(SysUser user, Errors errors) {
        if (errors.hasErrors()) {
            return JsonUtils.toJSONString(JsonResult.badRueqest(errors.getFieldError().getDefaultMessage()));
        }
        userService.insertOrUpdate(user);
        return JsonUtils.toJSONString(JsonResult.success());
    }

    @PostMapping("disable/{id}")
    @PreAuthorize("hasAuthority('user_edit')")
    public String disableUser(@PathVariable String id) {
        userService.disable(id);
        return JsonUtils.toJSONString(JsonResult.success());

    }

    @PostMapping("enable/{id}")
    @PreAuthorize("hasAuthority('user_edit')")
    public String enableUser(@PathVariable String id) {
        userService.enable(id);
        return JsonUtils.toJSONString(JsonResult.success());
    }


}

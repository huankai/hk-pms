package com.hk.pms.core.repository;

import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.pms.core.domain.SysRolePermission;

/**
 * @author: kevin
 * @date 2018-04-12 16:41
 */
public interface SysRolePermissionRepository extends StringRepository<SysRolePermission> {


    void deleteByRoleIdAndPermissionId(String roleId, String permissionId);
}

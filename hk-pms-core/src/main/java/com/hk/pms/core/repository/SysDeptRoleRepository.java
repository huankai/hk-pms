package com.hk.pms.core.repository;

import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.pms.core.domain.SysDeptRole;

/**
 * @author: kevin
 * @date 2018-04-12 16:37
 */
public interface SysDeptRoleRepository extends StringRepository<SysDeptRole> {

    void deleteByDeptIdAndRoleId(String deptId, String roleId);
}

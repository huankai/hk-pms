package com.hk.pms.core.servcie.impl;

import com.hk.core.data.commons.BaseDao;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysUserRole;
import com.hk.pms.core.repository.SysUserRoleRepository;
import com.hk.pms.core.servcie.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date 2018-04-12 17:02
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole,String> implements SysUserRoleService {

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseDao<SysUserRole, String> getBaseDao() {
        return sysUserRoleRepository;
    }

    @Override
    public void deleteByUserIdAndRoleId(String userId, String roleId) {
        sysUserRoleRepository.deleteByUserIdAndRoleId(userId,roleId);
    }
}

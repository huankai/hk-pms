package com.hk.pms.core.servcie.impl;

import com.google.common.collect.Lists;
import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.commons.BaseDao;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysRole;
import com.hk.pms.core.domain.SysUser;
import com.hk.pms.core.repository.SysRoleRepository;
import com.hk.pms.core.servcie.SysRoleService;
import com.hk.pms.core.servcie.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: kevin
 * @date 2018-04-12 16:59
 */
@Service
@CacheConfig(cacheNames = {"SYS_ROLE"})
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, String> implements SysRoleService {

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysUserService userService;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseDao<SysRole, String> getBaseDao() {
        return sysRoleRepository;
    }

    @Override
    public List<SysRole> getRoleList(String userId, String appId) {
        SysUser user = userService.findOne(userId);
        Set<SysRole> deptRoleSet = user.getOrgDept().getRoleSet();
        List<SysRole> deptRoleList = deptRoleSet.stream()
                .filter(item -> ByteConstants.ZERO.equals(item.getRoleStatus())
                        && StringUtils.notEquals(item.getApp().getId(), appId))
                .collect(Collectors.toList());
        Set<SysRole> roleSet = user.getRoleSet();
        List<SysRole> roleList = roleSet.stream()
                .filter(item -> ByteConstants.ZERO.equals(item.getRoleStatus())
                        && StringUtils.notEquals(item.getApp().getId(), appId))
                .collect(Collectors.toList());
        roleList.addAll(deptRoleList);
        return Lists.newArrayList(roleSet);
    }

    @Override
    public void disable(String id) {
        SysRole role = findOne(id);
        role.setRoleStatus(ByteConstants.ZERO);
        insertOrUpdate(role);
    }

    @Override
    public void enable(String id) {
        SysRole role = findOne(id);
        role.setRoleStatus(ByteConstants.ONE);
        insertOrUpdate(role);
    }

    @Override
    protected SysRole saveBefore(SysRole entity) {
        if (null == entity.getRoleStatus()) {
            entity.setRoleStatus(ByteConstants.ONE);
        }
        return super.saveBefore(entity);
    }

}

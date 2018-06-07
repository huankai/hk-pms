package com.hk.pms.core.servcie.impl;

import com.hk.commons.util.ByteConstants;
import com.hk.core.data.commons.BaseDao;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysUser;
import com.hk.pms.core.repository.SysUserRepository;
import com.hk.pms.core.servcie.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: huangkai
 * @date 2018-04-12 17:01
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String> implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseDao<SysUser, String> getBaseDao() {
        return sysUserRepository;
    }

    @Override
    public SysUser findByLoginUsername(String username) {
        return sysUserRepository.findByUserName(username);
    }

    @Override
    public void disable(String userId) {
        updateStatus(userId, ByteConstants.ZERO);
    }

    @Override
    public void enable(String userId) {
        updateStatus(userId, ByteConstants.ONE);
    }

    private void updateStatus(String userId, Byte userStatus) {
        SysUser sysUser = findOne(userId);
        sysUser.setUserStatus(userStatus);
        saveOrUpdate(sysUser);
    }

    @Override
    protected SysUser saveBefore(SysUser entity) {
        if (null == entity.getUserStatus()) {
            entity.setUserStatus(ByteConstants.ONE);
        }
        if (null == entity.getIsProtect()) {
            entity.setIsProtect(Boolean.FALSE);
        }
        return super.saveBefore(entity);
    }

}

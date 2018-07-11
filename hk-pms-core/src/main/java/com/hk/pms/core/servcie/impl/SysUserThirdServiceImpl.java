package com.hk.pms.core.servcie.impl;

import com.hk.core.data.commons.BaseDao;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysUserThird;
import com.hk.pms.core.repository.SysUserThirdRepository;
import com.hk.pms.core.servcie.SysUserThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date 2018-04-12 17:03
 */
@Service
public class SysUserThirdServiceImpl extends BaseServiceImpl<SysUserThird, String> implements SysUserThirdService {

    @Autowired
    private SysUserThirdRepository sysUserThirdRepository;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseDao<SysUserThird, String> getBaseDao() {
        return sysUserThirdRepository;
    }
}

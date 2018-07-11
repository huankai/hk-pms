package com.hk.pms.core.servcie.impl;

import com.hk.core.data.commons.BaseDao;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysOrgDept;
import com.hk.pms.core.repository.SysOrgDeptRepository;
import com.hk.pms.core.servcie.SysOrgDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date 2018-04-12 16:50
 */
@Service
public class SysOrgDeptServiceImpl extends BaseServiceImpl<SysOrgDept, String> implements SysOrgDeptService {

    @Autowired
    private SysOrgDeptRepository sysOrgDeptRepository;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseDao<SysOrgDept, String> getBaseDao() {
        return sysOrgDeptRepository;
    }
}

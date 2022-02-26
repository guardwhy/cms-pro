package com.cms.service.impl;

import com.cms.dao.mapper.TestMapper;
import com.cms.service.api.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guardwhy
 * @date 2022/2/26 22:26
 */
@Service
public class TestServiceImpl implements TestService {
    // 注入dao层
    @Autowired
    private TestMapper testMapper;

    @Override
    public int count() {
        return testMapper.count();
    }
}

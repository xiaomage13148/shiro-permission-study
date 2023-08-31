package com.xiaoma.sys.service.impl;

import com.xiaoma.sys.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysUserServiceImplTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    void checkSysUserIsExist() {
        Boolean isExist = sysUserService.checkSysUserIsExist("xiaoma");
        System.out.println("用户名是否存在" + isExist);
    }
}
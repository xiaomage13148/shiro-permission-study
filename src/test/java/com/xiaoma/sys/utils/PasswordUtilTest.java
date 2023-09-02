package com.xiaoma.sys.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordUtilTest {
    @Test
    public void test1() {
        String hashPassword = PasswordUtil.hashPassword("Wo610783");
        System.out.println(hashPassword);
        System.out.println(PasswordUtil.verifyPassword("Wo610783" , hashPassword));
    }
}
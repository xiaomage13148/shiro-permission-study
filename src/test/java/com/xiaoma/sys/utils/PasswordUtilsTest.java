package com.xiaoma.sys.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordUtilsTest {
    @Test
    public void test1() {
        String hashPassword = PasswordUtils.hashPassword("Wo610783");
        System.out.println(hashPassword);
        System.out.println(PasswordUtils.verifyPassword("Wo610783" , hashPassword));
    }
}
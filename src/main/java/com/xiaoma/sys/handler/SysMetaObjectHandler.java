package com.xiaoma.sys.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author mjh
 */
//@Slf4j
@Component
public class SysMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATOR = "creator";
    private static final String CREATETIME = "createTime";
    private static final String UPDATER = "updater";
    private static final String UPDATETIME = "updateTime";

    // TODO 尚未完成获取当前登录用户信息 , 这里假设值
    private static final String USERNAME = "xiaomage";

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject , CREATOR , String.class , USERNAME);
        this.strictInsertFill(metaObject , UPDATER , String.class , USERNAME);
        this.strictInsertFill(metaObject , CREATETIME , Data.class , LocalDateTime.now());
        this.strictInsertFill(metaObject , UPDATETIME , Data.class , LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject , UPDATER , String.class , USERNAME);
        this.strictUpdateFill(metaObject , UPDATETIME , Data.class , LocalDateTime.now());
    }
}

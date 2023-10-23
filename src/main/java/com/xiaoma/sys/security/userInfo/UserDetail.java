package com.xiaoma.sys.security.userInfo;

import com.xiaoma.sys.common.BaseEntity;
import com.xiaoma.sys.entity.SysRoleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author mjh
 * 用户详情
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDetail extends BaseEntity {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 用户对应的角色列表
     */
    private List<SysRoleEntity> sysRoleEntityList;

    /**
     * 是否是超级管理员
     */
    private Boolean isSuperAdmin;
}

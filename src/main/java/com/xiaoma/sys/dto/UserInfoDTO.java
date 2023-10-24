package com.xiaoma.sys.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
    private Long id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headImage;
}

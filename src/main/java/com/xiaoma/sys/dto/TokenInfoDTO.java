package com.xiaoma.sys.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Token信息
 */
@Data
public class TokenInfoDTO {

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private Date expireDate;
}

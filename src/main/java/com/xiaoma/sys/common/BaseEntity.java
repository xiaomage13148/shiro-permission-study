package com.xiaoma.sys.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * 基础类
 * @author mjh
 */
@Data
public class BaseEntity {
    /**
     *
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String updater;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 是否有效  1有效  2无效
     */
    @TableLogic(value = "1" , delval = "2")
    private String deleteStatus;
}

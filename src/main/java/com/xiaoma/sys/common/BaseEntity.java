package com.xiaoma.sys.common;

import com.baomidou.mybatisplus.annotation.*;
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
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String creator;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updater;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 是否有效  1有效  2无效
     */
    @TableLogic(value = "1" , delval = "2")
    private String deleteStatus;
}

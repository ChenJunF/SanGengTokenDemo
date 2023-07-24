package com.sangeng.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.ref.PhantomReference;
import java.math.BigDecimal;

/**
 * @Author: junfan
 * @Date: 2023/7/21 9:43
 * @Description:
 */

@Data
@TableName("JDshoplist")
public class JDEntity {

    @TableId
    private Long id;

    private String title;

    private String shop;

    private String link;

    private BigDecimal price;

    private String comment;

    private String tips;

    private String keyword;
}

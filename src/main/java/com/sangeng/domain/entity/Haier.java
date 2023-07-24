package com.sangeng.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_haier_copy1")
public class Haier {

    @TableId
    private Integer id;

    private String name;

    private String price;

    private Integer type;
}

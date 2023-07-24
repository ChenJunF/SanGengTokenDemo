package com.sangeng.domain.bo;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.Min;
@Data
public class PageQuery implements Serializable {

  @Min(value = 1, message = "最小页码数为1")
  private Integer current;


  @Min(value = 1, message = "每页展示数据条目至少有1条")
  private Integer size;
  public Integer getCurrent(){
    return current == null?1:current;
  }

  public Integer getSize(){
    return size == null?10:size;
  }

}
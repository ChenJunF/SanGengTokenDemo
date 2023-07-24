package com.sangeng.service.impl.责任链;

import com.sangeng.domain.Result;
import com.sangeng.domain.vo.ProductVO;
import com.sangeng.service.AbstractCheckHandler;

public class HandlerClient {
 
  public static Result executeChain(AbstractCheckHandler handler, ProductVO param) {
      //执行处理器
      Result handlerResult = handler.handle(param);
      if (!handlerResult.isSuccess()) {
          System.out.println("HandlerClient 责任链执行失败返回：" + handlerResult.toString());
          return handlerResult;
      }
      return Result.success();
  }
}
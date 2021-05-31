package com.xiaoshabao.base.oauth.util;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ResultUtil {

  public static Result fail(String message) {
    return new Result(false, message);
  }

  @Data
  @AllArgsConstructor
  public static class Result {

    /**
     * 结果是否成功
     */
    protected boolean success;

    /**
     * 返回信息
     */
    protected String message;

  }

}

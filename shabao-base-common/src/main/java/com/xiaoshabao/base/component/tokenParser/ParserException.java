package com.xiaoshabao.base.component.tokenParser;
/**
 * 通用变量解析异常
 */
public class ParserException extends RuntimeException{

  private static final long serialVersionUID = 8904266149245846293L;
  
  public ParserException(String mess) {
    super(mess);
  }
  public ParserException(String message, Throwable cause) {
    super(message, cause);
  }

}

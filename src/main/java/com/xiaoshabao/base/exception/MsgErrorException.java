package com.xiaoshabao.base.exception;

public class MsgErrorException extends ServiceException{

  private static final long serialVersionUID = 1L;

  public MsgErrorException(String mess) {
    super(mess);
  }
  
  public MsgErrorException(String message, Throwable cause) {
    super(message, cause);
  }

}

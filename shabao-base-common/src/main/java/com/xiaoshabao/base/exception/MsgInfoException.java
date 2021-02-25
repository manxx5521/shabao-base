package com.xiaoshabao.base.exception;

public class MsgInfoException extends ServiceException{

  private static final long serialVersionUID = 1L;

  public MsgInfoException(String mess) {
    super(mess);
  }

  public MsgInfoException(String message, Throwable cause) {
    super(message, cause);
  }

}

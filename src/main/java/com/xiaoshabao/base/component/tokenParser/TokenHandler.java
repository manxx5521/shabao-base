package com.xiaoshabao.base.component.tokenParser;

public interface TokenHandler {
  
  /**
   * 解析参水返回替换后的参数
   * @param params 用来替换的表达式
   * @return 替换后的值
   */
  String handleToken(String content);

}

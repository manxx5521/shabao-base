package com.xiaoshabao.base.component.tokenParser;

import java.util.Map;

public class MapTokenHandler implements TokenHandler{
  
  private Map<String,Object> params;
  
  public MapTokenHandler(Map<String,Object> params){
    this.params=params;
  }

  @Override
  public String handleToken(String content) {
    
    Object obj=this.params.get(content);
    if(obj==null){
      throw new ParserException("无法在参数中获得表达式 @{"+content+"}所代表的变量");
    }
    return obj.toString();
  }
}

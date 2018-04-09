package com.xiaoshabao.base.component.tokenParser;

import java.util.HashMap;
import java.util.Map;

public class GenericTokenParserTest {

  public static void main(String[] args) {
    Map<String,Object> params=new HashMap<String,Object>();
    params.put("aaa", "123");
    TokenHandler handler=new MapTokenHandler(params);
    GenericTokenParser parser=new GenericTokenParser("@{","}",handler);
    String rs=parser.parse("的年龄是@{aaa},谁的年龄是@{aaa},哈哈哈@{bbb}");
    System.out.println(rs);

  }

}

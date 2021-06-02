package com.xiaoshabao.base.oauth2.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoshabao.base.oauth2.config.OAuth2ClientConfig;

/**
 * 登录页
 */
@Controller
public class LoginController {
  @Autowired
  private OAuth2ClientConfig oAuth2ClientConfig;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView view(ModelMap model, HttpServletRequest request, RedirectAttributes attr) {
    String src = oAuth2ClientConfig.getClientId() + ":" + oAuth2ClientConfig.getClientSecret();
    //		model.put("Authorization", Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8)));
    attr.addAttribute("authorization", Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8)));
    attr.addAttribute("uri", "/blog/user");
    return new ModelAndView("redirect:" + oAuth2ClientConfig.getLoginUrl(), model);
  }

}

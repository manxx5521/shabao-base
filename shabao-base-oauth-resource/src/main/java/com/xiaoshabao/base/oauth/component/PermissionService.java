package com.xiaoshabao.base.oauth.component;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

/**
 * 接口权限判断工具
 */
public class PermissionService {

  /**
   * 判断接口是否有xxx:xxx权限
   * @param permission 权限
   * @return {boolean}
   */
  public boolean hasPermission(String permission) {
    if (!StringUtils.hasText(permission)) {
      return false;
    }
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return false;
    }
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    return authorities.stream().map(GrantedAuthority::getAuthority).filter(StringUtils::hasText)
      .anyMatch(x -> PatternMatchUtils.simpleMatch(permission, x));
  }

}


package com.xiaoshabao.base.oauth.service;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.xiaoshabao.base.oauth.util.OauthConstants;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户详细信息
 *
 * @author lengleng
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PigUserDetailsServiceImpl implements UserDetailsService {

//	private final RemoteUserService remoteUserService;

	private final CacheManager cacheManager;

	/**
	 * 用户密码登录
	 * @param username 用户名
	 * @return
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		Cache cache = cacheManager.getCache(OauthConstants.USER_DETAILS);
		if (cache != null && cache.get(username) != null) {
			return (PigUser) cache.get(username).get();
		}

    /*R<UserInfo> result = remoteUserService.info(username, OauthConstants.FROM_IN);
    UserDetails userDetails = getUserDetails(result);
    if (cache != null) {
    	cache.put(username, userDetails);
    }
    return userDetails;*/
		return null;
	}

	/**
	 * 构建userdetails
	 * @param result 用户信息
	 * @return
	 */
  /*private UserDetails getUserDetails(R<UserInfo> result) {
  	if (result == null || result.getData() == null) {
  		throw new UsernameNotFoundException("用户不存在");
  	}
  
  	UserInfo info = result.getData();
  	Set<String> dbAuthsSet = new HashSet<>();
  	if (ArrayUtil.isNotEmpty(info.getRoles())) {
  		// 获取角色
  		Arrays.stream(info.getRoles()).forEach(role -> dbAuthsSet.add(SecurityConstants.ROLE + role));
  		// 获取资源
  		dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));
  
  	}
  	Collection<? extends GrantedAuthority> authorities = AuthorityUtils
  			.createAuthorityList(dbAuthsSet.toArray(new String[0]));
  	SysUser user = info.getSysUser();
  
  	// 构造security用户
  	return new PigUser(user.getUserId(), user.getDeptId(), user.getUsername(),
  			SecurityConstants.BCRYPT + user.getPassword(),
  			StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL), true, true, true, authorities);
  }*/

}

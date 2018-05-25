package com.xiaoshabao.base.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.xiaoshabao.base.entity.SysConfigEntity;
import com.xiaoshabao.base.service.SysConfigService;

@Service("sysConfigServiceImpl")
@CacheConfig(cacheNames = "sysConfig")
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Cacheable
	public SysConfigEntity getDataById(String key) {
		String sql = "select id,name,type,value,remark FROM sys_config WHERE id=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {key},new int[]{Types.VARCHAR}, new RowMapper<SysConfigEntity>(){
			@Override
			public SysConfigEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				SysConfigEntity entity=new SysConfigEntity();
				entity.setId(rs.getString(1));
				entity.setName(rs.getString(2));
				entity.setType(rs.getInt(3));
				entity.setValue(rs.getString(4));
				entity.setRemark(rs.getString(5));
				return entity;
			}
		});
	}

}

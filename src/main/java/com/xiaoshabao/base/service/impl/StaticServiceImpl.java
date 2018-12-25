package com.xiaoshabao.base.service.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.xiaoshabao.base.entity.StaticEntity;
import com.xiaoshabao.base.service.StaticService;

@Service
public class StaticServiceImpl implements StaticService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<StaticEntity> getStatic(String typeId, String module) {
		String sql = "select type_id,module,data_id,data_name,order_no,used from sys_static a where type_id=? and module=?";
		return jdbcTemplate.queryForList(sql,new Object[] {typeId,module},new int[]{Types.VARCHAR,Types.VARCHAR},StaticEntity.class );
	}
}

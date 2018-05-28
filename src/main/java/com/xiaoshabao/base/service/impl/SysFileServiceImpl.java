package com.xiaoshabao.base.service.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.xiaoshabao.base.entity.SysFileEntity;
import com.xiaoshabao.base.service.SysFileService;

/**
 * 文件数据库操作
 */
@Service("sysFileServiceImpl")
@CacheConfig(cacheNames = "sysFile")
public class SysFileServiceImpl implements SysFileService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String COLUMNS = "FILE_ID,UPLOAD_NAME,EXT,SAVE_PATH,SIZE,MD5,CREATE_TIME";
	private final static String TABLE = "sys_file";

	@Override
	@Cacheable
	public SysFileEntity getFileEntityById(Long id) {
		String sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE FILE_ID=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new int[] { Types.NUMERIC },
				new BeanPropertyRowMapper<SysFileEntity>(SysFileEntity.class));
	}

	@Override
	public List<SysFileEntity> getFileEntityByMD5(String md5) {
		String sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE MD5=?";
		return jdbcTemplate.query(sql, new Object[] { md5 }, new int[] { Types.VARCHAR },
				new BeanPropertyRowMapper<SysFileEntity>(SysFileEntity.class));
	}

	@Override
	public int insertFileEntity(SysFileEntity entity) {
		String sql = "INSERT INTO " + TABLE + " (" + COLUMNS + ") VALUES " + "(?,?,?,?,?,?,SYSDATE())";
		return jdbcTemplate.update(sql,
				new Object[] { entity.getFileId(), entity.getUploadName(), entity.getExt(), entity.getSavePath(),
						entity.getSize(), entity.getMd5() },
				new int[] { Types.NUMERIC, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.NUMERIC, Types.VARCHAR });
	}

}

package com.xiaoshabao.base.mybatis.util;
import com.xiaoshabao.base.component.PageVo;

/**
 * 分页工具类
 *
 */
public class PageUtil {

	/**
	 * 分页方法
	 * <p>
	 * 如果想直接返回列表直接在mapper方法中返回List<UserInfo><br>
	 * 如果想要返回分页参数在mapper中返回Page<UserInfo>形式参数
	 * </p>
	 * @param pageParams
	 */
	public static void startPage(PageVo pageVo) {
		if (pageVo.getIndex() != null && pageVo.getSize() != null) {
//			PageHelper.startPage(pageVo.getIndex(), pageVo.getSize());
		}
	}

	/**
	 * 分页方法
	 * <p>
	 * 如果想直接返回列表直接在mapper方法中返回List<UserInfo><br>
	 * 如果想要返回分页参数在mapper中返回Page<UserInfo>形式参数
	 * </p>
	 * @param pageParams
	 */
	public static void startPage(Integer page, Integer rows) {
		if (page != null && rows != null) {
//			PageHelper.startPage(page, rows);
		}
	}

}
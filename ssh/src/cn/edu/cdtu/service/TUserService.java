package cn.edu.cdtu.service;

import cn.edu.cdtu.entity.TUser;

public interface TUserService extends BaseService<TUser> {
	/**
	 * 登录时判断是否在数据库中
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public TUser validateLoginInfo(String name, String password);

	/**
	 * 判断是否注册
	 */
	public TUser isRegisted(String name);

}

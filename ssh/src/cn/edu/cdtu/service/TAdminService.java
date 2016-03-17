package cn.edu.cdtu.service;

import cn.edu.cdtu.entity.TAdmin;

public interface TAdminService extends BaseService<TAdmin> {
	public TAdmin validateLoginInfo(String name, String password);
}

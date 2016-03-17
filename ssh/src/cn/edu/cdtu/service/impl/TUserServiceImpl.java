package cn.edu.cdtu.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.edu.cdtu.dao.BaseDao;
import cn.edu.cdtu.entity.TUser;
import cn.edu.cdtu.service.TUserService;

@Service
public class TUserServiceImpl extends BaseServiceImpl<TUser> implements
		TUserService {
	@Resource(name = "tUserDao")
	public void setBaseDao(BaseDao<TUser> baseDao) {
		super.setBaseDao(baseDao);
	}

	/**
	 * 验证登录
	 */
	public TUser validateLoginInfo(String name, String password) {
		String hql = "from TUser as u where u.userName = ? and u.userPassword = ?";
		List<TUser> list = this.findEntityHql(hql, name, password);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	/**
	 * 验证注册信息
	 */
	public TUser isRegisted(String name) {
		String hql = "from TUser as u where u.userName = ?";
		List<TUser> list = this.findEntityHql(hql, name);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}
}

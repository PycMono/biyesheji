package cn.edu.cdtu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.edu.cdtu.dao.BaseDao;
import cn.edu.cdtu.entity.TAdmin;
import cn.edu.cdtu.service.TAdminService;

@Service
//@Scope("prototype")
public class TAdminServiceImpl extends BaseServiceImpl<TAdmin>implements TAdminService{
	@Resource(name="tAdminDao")
	public void setBaseDao(BaseDao<TAdmin> baseDao) {
		super.setBaseDao(baseDao);
	}
	public TAdmin validateLoginInfo(String name, String password) {
		String hql = "from TAdmin as c where c.adminName = ? and c.adminPassword = ?";
		List<TAdmin> list = this.findEntityHql(hql, name, password);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}
}

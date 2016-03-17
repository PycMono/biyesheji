package cn.edu.cdtu.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.edu.cdtu.dao.BaseDao;
import cn.edu.cdtu.entity.TOrderitem;
import cn.edu.cdtu.service.TOrderitemService;

@Service
//@Scope("prototype")
public class TOrderitemServiceImpl extends BaseServiceImpl<TOrderitem>implements TOrderitemService {
	@Resource(name="tOrderitemDao")
	public void setBaseDao(BaseDao<TOrderitem> baseDao) {
		super.setBaseDao(baseDao);
	}
}

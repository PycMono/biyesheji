package cn.edu.cdtu.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cdtu.dao.BaseDao;
import cn.edu.cdtu.entity.TBook;
import cn.edu.cdtu.entity.TCategory;
import cn.edu.cdtu.service.TCategoryService;

@Service
public class TCategoryServiceImpl extends BaseServiceImpl<TCategory> implements
		TCategoryService {
	@Resource(name = "tCategoryDao")
	public void setBaseDao(BaseDao<TCategory> baseDao) {
		super.setBaseDao(baseDao);
	}

	/**
	 * 返回所有分类
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<TCategory> findAll() {
		/*
		 * 1. 查询出所有一级分类
		 */
		String hql = " from TCategory as t where t.TCategory is null order by t.categoryOrderBy";
		List<TCategory> parents = this.findEntityHql(hql);
		
		return parents;
	}

	/**
	 * 通过父分类查询子分类
	 * 
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public List<TCategory> findByParent(String pid) {
		String hql = "from TCategory as t where t.TCategory.categoryId=?";
		List<TCategory> mapList = this.findEntityHql(hql, pid);
		return mapList;
	}
	
	
	
	/**
	 * 添加分类
	 * @param category
	 * @throws SQLException 
	 */
	public void add(TCategory category) {
		this.save(category);
	}
	
	/**
	 * 获取所有父分类，但不带子分类的！
	 * @return
	 * @throws SQLException
	 */
	public List<TCategory> findParents(){
		return findAll();
	}
	/**
	 * 查询指定父分类下子分类的个数,用于删除
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	public int findChildrenCountByParent(String pid){
		String sql = "select * from t_category where t_c_category_id=?";
	
		List<TCategory> number=this.executeSQLQuery(TCategory.class, sql, pid);
		int cnt =number.size();
		return  cnt;//返回个数
	}
	
	
}

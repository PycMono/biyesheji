package cn.edu.cdtu.service;

import java.util.List;

import cn.edu.cdtu.entity.TCategory;

public interface TCategoryService extends BaseService<TCategory> {
	public List<TCategory> findAll();
	public List<TCategory> findByParent(String pid);
	/**
	 * 添加分类
	 * @param category
	 * @throws SQLException 
	 */
	public void add(TCategory category);
	public List<TCategory> findParents();//查看一級分類
	/**
	 * 查询指定父分类下子分类的个数,用于删除
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	public int findChildrenCountByParent(String pid);
}

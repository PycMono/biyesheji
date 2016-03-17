package cn.edu.cdtu.service;

import java.util.List;

import cn.edu.cdtu.entity.TCategory;


public interface BaseService<T> {
	/**
	 * 保存和写操作
	 * 
	 * @param t
	 */
	
	public T get(Class<T> entity, String id);
	public void save(T t);
	
	public void update(T t);

	public void delete(T t);
	/**
	 * 读操作
	 * 
	 */
	public List<T> findEntityHql(String hql, Object... objects);
	public T getEntity(String id);
	//执行原生的sql查询
	public void executeSQL(String sql, Object... objects);
	public List executeSQLQuery(Class clazz,String sql,Object...objects);
	//批量跟新
	public void batchEntityByHQL(String hql, Object... objects);

}

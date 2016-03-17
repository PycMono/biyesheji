package cn.edu.cdtu.dao;

import java.util.List;

public interface BaseDao<T> {
	/**
	 * 锟斤拷锟斤拷锟叫达拷锟斤拷锟�
	 * 
	 * @param t
	 */
	public void save(T t);

	public T get(Class<T> entity, String id);

	public void update(T t);

	public void delete(T t);

	/**
	 * 锟斤拷锟斤拷锟斤拷 dsaf
	 */
	public List<T> findEntityHql(String hql, Object... objects);

	public T getEntity(String id);

	// 执行原生的sql查询
	public List executeSQLQuery(Class clazz, String sql, Object... objects);

	// 执行原生的sql语句
	public void executeSQL(String sql, Object... objects);

	/**
	 * 按照HQL语句进行批量更新
	 */
	public void batchEntityByHQL(String hql, Object... objects);
}

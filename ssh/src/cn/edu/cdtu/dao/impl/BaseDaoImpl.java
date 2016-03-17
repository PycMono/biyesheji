package cn.edu.cdtu.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cdtu.dao.BaseDao;

@Transactional
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	// 注入sessionFactory
	@Resource
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	public BaseDaoImpl() {
		// 得到泛型话超类
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	public T get(Class<T> entity,String id)
	{
		return (T)sessionFactory.getCurrentSession().get(entity, id);
	}
	@Override
	public void save(T t) {
		sessionFactory.getCurrentSession().save(t);
	}

	@Override
	public void update(T t) {
		sessionFactory.getCurrentSession().update(t);
	}

	@Override
	public void delete(T t) {
		sessionFactory.getCurrentSession().delete(t);
	}
	/**
	 * 查找实体在数据库中是否存在
	 * @param hql
	 * @param objects
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findEntityHql(String hql, Object... objects) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}
	
	public T getEntity(String id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}
	
	/**
	 * 按照HQL语句进行批量更新
	 */
	public void batchEntityByHQL(String hql, Object... objects) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}

	// 执行原生的sql语句
	public void executeSQL(String sql, Object... objects) {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}
	// 执行原生的sql查询
		public List executeSQLQuery(Class clazz, String sql, Object... objects) {
			SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
			// 添加实体类
			if (clazz != null) {
				q.addEntity(clazz);
			}
			for (int i = 0; i < objects.length; i++) {
				q.setParameter(i, objects[i]);
			}
			return q.list();
		}
}

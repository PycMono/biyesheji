package cn.edu.cdtu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cdtu.dao.BaseDao;
import cn.edu.cdtu.entity.TOrder;
import cn.edu.cdtu.entity.TOrderitem;
import cn.edu.cdtu.page.Expression;
import cn.edu.cdtu.page.PageBean;
import cn.edu.cdtu.page.PageConstants;
import cn.edu.cdtu.service.TOrderService;
import cn.edu.cdtu.service.TOrderitemService;

@Service("tOrderServiceImpl")
public class TOrderServiceImpl extends BaseServiceImpl<TOrder> implements
		TOrderService {
	@Resource(name = "tOrderitemDao")
	public void setBaseDao(BaseDao<TOrder> baseDao) {
		super.setBaseDao(baseDao);
	}

	/*
	 * @Resource private TOrderitemService service;
	 */

	/**
	 * 通用的查询方法
	 * 
	 * @param exprList
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<TOrder> findByCriteria(List<Expression> exprList, int pc) {
		/*
		 * 1. 得到ps 2. 得到tr 3. 得到beanList 4. 创建PageBean，返回
		 */
		/*
		 * 1. 得到ps
		 */
		int ps = PageConstants.ORDER_PAGE_SIZE;// 订单每页记录数
		/*
		 * 2. 通过exprList来生成where子句
		 */
		StringBuilder whereSql = new StringBuilder(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();// SQL中有问号，它是对应问号的值
		for (Expression expr : exprList) {
			/*
			 * 添加一个条件上， 1) 以and开头 2) 条件的名称 3) 条件的运算符，可以是=、!=、>、< ... is null，is
			 * null没有值 4) 如果条件不是is null，再追加问号，然后再向params中添加一与问号对应的值
			 */
			whereSql.append(" and ").append(expr.getName()).append(" ")
					.append(expr.getOperator()).append(" ");
			if (!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}

		}
		/*
		 * 3. 总记录数
		 */
		String hql = "select * from t_order" + whereSql;
		System.out.println(hql);
		List<TOrder> number = this.executeSQLQuery(null, hql, params.toArray());
		int tr = number.size();// 得到了总记录数

		// * 4. 得到beanList，即当前页记录

		hql = "select * from t_order" + whereSql
				+ " order by order_time desc limit ?,?";
		params.add((pc - 1) * ps);// 当前页首行记录的下标
		params.add(ps);// 一共查询几行，就是每页记录数
		List<TOrder> beanList = this.executeSQLQuery(TOrder.class, hql,
				params.toArray());
		// * 5. 创建PageBean，设置参数
		// 虽然已经获取所有的订单，但每个订单中并没有订单条目。
		// 遍历每个订单，为其加载它的所有订单条目
		for (TOrder order : beanList) {
			loadOrderItem(order);
		}
		PageBean<TOrder> pb = new PageBean<TOrder>();
		// * 其中PageBean没有url，这个任务由Servlet完成
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		return pb;
	}

	/**
	 * 按用户查询订单
	 * 
	 * @param uid
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<TOrder> findByUser(String uid, int pc) {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("user_id", "=", uid));
		return findByCriteria(exprList, pc);
	}

	/*
	 * 为指定的order载它的所有OrderItem
	 */
	private void loadOrderItem(TOrder order) {
		/*
		 * 1. 给sql语句select * from t_orderitem where order_id=? 2.
		 * 执行之，得到List<OrderItem> 3. 设置给Order对象
		 */
		String sql = "select * from t_orderitem where order_id=?";
		List<TOrderitem> mapList = this.executeSQLQuery(TOrderitem.class, sql,
				order.getOrderId());
		order.setOrderItemList(mapList);
	}

	/**
	 * 加载订单,其实就是查看订单详细信息
	 * 
	 * @param oid
	 * @return
	 */
	public TOrder load(String oid) {
		TOrder order = this.get(TOrder.class, oid);
		loadOrderItem(order);// 为当前订单加载它的所有订单条目
		return order;
	}

	/**
	 * 查询订单状态
	 * 
	 * @param oid
	 * @return
	 */
	public int findStatus(String oid) {
		TOrder order = this.get(TOrder.class, oid);
		return order.getOrderStatus();
	}

	/**
	 * 修改订单状态
	 * 
	 * @param oid
	 * @param status
	 */
	public void updateStatus(String oid, int status) {
		String sql = "update t_order set order_status=? where order_id=?";
		this.executeSQL(sql, status, oid);
	}

	/**
	 * 生成订单
	 */
	public void add(TOrder order) {
		/*
		 * 1. 插入订单
		 */
		this.save(order);

	}
}

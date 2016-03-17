package cn.edu.cdtu.service;

import cn.edu.cdtu.entity.TOrder;
import cn.edu.cdtu.page.PageBean;

public interface TOrderService extends BaseService<TOrder>{
	/**
	 * 按用户查询订单
	 * @param uid
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<TOrder> findByUser(String uid, int pc) ;
	/**
	 * 加载订单,其实就是查看订单详细信息
	 * @param oid
	 * @return
	 */
	public TOrder load(String oid);
	
	/**
	 * 修改订单状态
	 * @param oid
	 * @param status
	 */
	public void updateStatus(String oid, int status);
	/**
	 * 查询订单状态
	 * @param oid
	 * @return
	 */
	public int findStatus(String oid);
	/**
	 * 生成订单
	 */
	public void add(TOrder order);
}

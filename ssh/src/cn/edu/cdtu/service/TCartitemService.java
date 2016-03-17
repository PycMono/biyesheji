package cn.edu.cdtu.service;

import java.util.List;

import cn.edu.cdtu.entity.TBook;
import cn.edu.cdtu.entity.TCartitem;

public interface TCartitemService extends BaseService<TCartitem> {
	/**
	 * 添加条目
	 * @param cartItem
	 */
	public void add(TCartitem cartItem);
	/**
	 * 修改指定条目的数量
	 * @param cartItemId
	 * @param quantity
	 * @throws SQLException 
	 */
	public void updateQuantity(String cartItemId, int quantity);
	/**
	 * 加载多个CartItem
	 * @param cartItemIds
	 * @return
	 * @throws SQLException 
	 */
	public List<TCartitem> loadCartItems(Object[] cartItemIds);
}

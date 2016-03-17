package cn.edu.cdtu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.edu.cdtu.dao.BaseDao;
import cn.edu.cdtu.entity.TCartitem;
import cn.edu.cdtu.service.TCartitemService;

@Service
// @Scope("prototype")
public class TCartitemServiceImpl extends BaseServiceImpl<TCartitem> implements
		TCartitemService {
	@Resource(name = "tCartitemDao")
	public void setBaseDao(BaseDao<TCartitem> baseDao) {
		super.setBaseDao(baseDao);
	}

	/**
	 * 查询某个用户的某本图书的购物车条目是否存在
	 * 
	 * @throws SQLException
	 */
	public TCartitem findByUidAndBid(String uid, String bid) {
		String sql = "select * from t_cartitem where user_id=? and book_id=?";
		List<TCartitem> cartItem = this.executeSQLQuery(TCartitem.class, sql,
				uid, bid);
		if (cartItem.size() > 0)
			return cartItem.get(0);
		else
			return null;

	}

	/**
	 * 添加条目
	 * 
	 * @param cartItem
	 */
	public void add(TCartitem cartItem) {
		try {
			/*
			 * 1. 使用uid和bid去数据库中查询这个条目是否存在
			 */
			TCartitem _cartItem = findByUidAndBid(cartItem.getTUser()
					.getUserId(), cartItem.getTBook().getBookId());
			if (_cartItem == null) {// 如果原来没有这个条目，那么添加条目
				this.save(cartItem);
			} else {// 如果原来有这个条目，修改数量
				// 使用原有数量和新条目数量之各，来做为新的数量
				int quantity = cartItem.getCartitemCount()
						+ _cartItem.getCartitemCount();
				cartItem.setCartitemCount(quantity);
				cartItem.setCartitemId(_cartItem.getCartitemId());
				// 修改这个老条目的数量
				this.update(cartItem);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改指定条目的数量
	 * 
	 * @param cartItemId
	 * @param quantity
	 * @throws SQLException
	 */
	public void updateQuantity(String cartItemId, int quantity) {
		String sql = "update t_cartitem set cartitem_count=? where cartitem_id=?";
		this.executeSQL(sql, quantity, cartItemId);
	}
	
	/**
	 * 加载多个CartItem
	 */
	public List<TCartitem> loadCartItems(Object[] cartItemIds) {
		String whereSql = toWhereSql(cartItemIds.length);
		String sql = "select * from t_cartitem, t_book where t_cartitem.book_id=t_book.book_id and "+whereSql;
		return this.executeSQLQuery(TCartitem.class, sql, cartItemIds);
	}
	/*
	 * 用来生成where子句
	 */
	private String toWhereSql(int len) {
		StringBuilder sb = new StringBuilder("cartitem_id in(");
		for(int i = 0; i < len; i++) {
			sb.append("?");
			if(i < len - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}
}

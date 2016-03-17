package cn.edu.cdtu.service;

import java.util.List;

import cn.edu.cdtu.entity.TBook;
import cn.edu.cdtu.page.Expression;
import cn.edu.cdtu.page.PageBean;


public interface TBookService extends BaseService<TBook> {
	/**
	 * 通用的查询方法
	 * @param exprList
	 * @param pc
	 * @return
	 * @throws SQLException 
	 */
	public  PageBean<TBook> findByCriteria(List<Expression> exprList, int pc);
	/**
	 * 按书名模糊查询
	 * @param bname
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<TBook> findByBname(String bname, int pc);
	/**
	 * 按出版社查
	 * @param press
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<TBook> findByPress(String press, int pc);

/**
 * 按分类查询,当点击右边图书时
 * @param cid
 * @param pc
 * @return
 * @throws SQLException 
 */
public PageBean<TBook> findByCategory(String cid, int pc);
/**
 * 按bookid查询，显示详细信息
 * @param bid
 * @return
 * @throws SQLException
 */
public TBook findByBid(String bid);

/**
 * 按作者查
 * @param bname
 * @param pc
 * @return
 * @throws SQLException
 */
public PageBean<TBook> findByAuthor(String author, int pc);
/**
 * 多条件组合查询
 * @param combination
 * @param pc
 * @return
 * @throws SQLException
 */
public PageBean<TBook> findByCombination(String name,String author,String press, int pc);
/**
 * 查询指定分类下图书的个数
 * @param cid
 * @return
 * @throws SQLException
 */
public int findBookCountByCategory(String cid);
}


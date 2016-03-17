package cn.edu.cdtu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.edu.cdtu.dao.BaseDao;
import cn.edu.cdtu.entity.TBook;
import cn.edu.cdtu.page.Expression;
import cn.edu.cdtu.page.PageBean;
import cn.edu.cdtu.page.PageConstants;
import cn.edu.cdtu.service.TBookService;

@Service
// @Scope("prototype")
public class TBookServiceImpl extends BaseServiceImpl<TBook> implements
		TBookService {
	@Resource(name = "tBookDao")
	public void setBaseDao(BaseDao<TBook> baseDao) {
		super.setBaseDao(baseDao);
	}

	/**
	 * 按书名模糊查询
	 * 
	 * @param bname
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<TBook> findByBname(String bname, int pc) {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("book_name", "like", "%" + bname + "%"));
		return findByCriteria(exprList, pc);
	}

	/**
	 * 按出版社查
	 * 
	 * @param press
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<TBook> findByPress(String press, int pc) {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("book_press", "like", "%" + press + "%"));
		return findByCriteria(exprList, pc);
	}

	/**
	 * 通用的查询方法
	 * 
	 * @param exprList
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<TBook> findByCriteria(List<Expression> exprList, int pc) {
		/*
		 * 1. 得到ps 2. 得到tr 3. 得到beanList 4. 创建PageBean，返回
		 */
		/*
		 * 1. 得到ps
		 */
		int ps = PageConstants.BOOK_PAGE_SIZE;// 每页记录数
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
			// where 1=1 and bid = ?
			if (!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}

		}
		// System.out.println(params);
		// System.out.println(whereSql);

		/*
		 * 3. 总记录数
		 */
		String hql = "select * from t_book" + whereSql;
		List<TBook> number = this.executeSQLQuery(null, hql, params.toArray());
		int tr = number.size();// 得到了总记录数

		// * 4. 得到beanList，即当前页记录

		hql = "select * from t_book" + whereSql
				+ " order by book_orderBy limit ?,?";
		params.add((pc - 1) * ps);// 当前页首行记录的下标
		params.add(ps);// 一共查询几行，就是每页记录数

		List<TBook> beanList = this.executeSQLQuery(TBook.class, hql,
				params.toArray());
		// * 5. 创建PageBean，设置参数

		PageBean<TBook> pb = new PageBean<TBook>();

		// * 其中PageBean没有url，这个任务由Servlet完成

		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		return pb;
	}

	/**
	 * 测试专用
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TBookServiceImpl ss = new TBookServiceImpl();
		List<Expression> list = new ArrayList<Expression>();
		list.add(new Expression("bid", "=", "1"));
		list.add(new Expression("bid", "is null", null));
		ss.findByCriteria(list, 10);
	}

	/**
	 * 按分类查询,当点击右边图书时
	 * 
	 * @param cid
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<TBook> findByCategory(String cid, int pc) {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("category_id", "=", cid));
		return findByCriteria(exprList, pc);
	}

	/**
	 * 按bookid查询，显示详细信息
	 * 
	 * @param bid
	 * @return
	 * @throws SQLException
	 */
	public TBook findByBid(String bid) {
		System.out.println(bid);
		String sql = "SELECT * FROM t_book , t_category WHERE t_book.category_id=t_category.category_id AND t_book.book_id=?";
		// 一行记录中，包含了很多的book的属性，还有一个cid属性
		List<TBook> beanList = this.executeSQLQuery(TBook.class, sql, bid);
		System.out.println(beanList.size()
				+ "----------------------------------------");
		System.out.println(beanList.get(0).getBookName());
		if (beanList.size() > 0) {
			TBook book = beanList.get(0);
			return book;
		}
		return null;
	}

	/**
	 * 按作者查
	 * 
	 * @param bname
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<TBook> findByAuthor(String author, int pc) {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("book_author", "like", "%" + author + "%"));
		return findByCriteria(exprList, pc);
	}

	
	/**
	 * 多条件组合查询
	 * @param combination
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<TBook> findByCombination(String name,String author,String press, int pc){
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("book_name", "like", "%" + name + "%"));
		exprList.add(new Expression("book_author", "like", "%" + author + "%"));
		exprList.add(new Expression("book_press", "like", "%" + press + "%"));
		return findByCriteria(exprList, pc);
	}
	/**
	 * 查询指定分类下图书的个数
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	public int findBookCountByCategory(String cid){
		String sql = "select * from t_book where category_id=?";
		int cnt=this.executeSQLQuery(TBook.class, sql, cid).size();
		return cnt;
	}
}

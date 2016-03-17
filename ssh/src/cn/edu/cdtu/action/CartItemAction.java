package cn.edu.cdtu.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.cdtu.entity.TBook;
import cn.edu.cdtu.entity.TBookAndTCartitem;
import cn.edu.cdtu.entity.TCartitem;
import cn.edu.cdtu.entity.TUser;
import cn.edu.cdtu.service.TBookService;
import cn.edu.cdtu.service.TCartitemService;

@Controller
@Scope("prototype")
public class CartItemAction {

	HttpServletResponse resp = ServletActionContext.getResponse();
	HttpServletRequest req = ServletActionContext.getRequest();
	@Resource
	private TCartitemService service;
	@Resource
	private TBookService serviceTb;
	private String bookId;
	private int quantity;
	private double total;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	private String cartItemId;

	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * 我的购物车，显示我的购物车的所有信息，购物车条目
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myCart() {
		/*
		 * 1. 得到uid
		 */
		TUser user = (TUser) req.getSession().getAttribute("sessionUser");
		String uid = user.getUserId();
		/*
		 * 2. 通过service得到当前用户的所有购物车条目
		 */
		String sql = "select book_name,book_currPrice,cartitem_count,book_image_b,cartitem_id from t_cartitem , t_book  where t_cartitem.book_id=t_book.book_id and user_id=?";
		List cartItemLIst = service.executeSQLQuery(null, sql, uid);
		List<TBookAndTCartitem> lis = new ArrayList<TBookAndTCartitem>();
		Iterator it = cartItemLIst.iterator();
		TBookAndTCartitem s = null;
		while (it.hasNext()) {
			Object[] tuple = (Object[]) it.next();
			String bookName = (String) tuple[0];
			BigDecimal bookCurrPrice = (BigDecimal) tuple[1];
			Integer cartitemCount = (Integer) tuple[2];
			String bookImageB = (String) tuple[3];
			String cartitemId = (String) tuple[4];
			s = new TBookAndTCartitem(bookName, bookCurrPrice, cartitemCount,
					cartitemId, bookImageB);
			lis.add(s);
		}
		/*
		 * 3. 保存起来，转发到/cart/list.jsp
		 */
		req.setAttribute("cartItemList", lis);
		return "list";
	}

	/**
	 * 添加购物车条目（添加书本）
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add() {
		/*
		 * 1. 封装表单数据到CartItem(bid, quantity)
		 */
		TCartitem cartItem = new TCartitem();
		TBook book = new TBook();
		book.setBookId(bookId);
		TUser user = (TUser) req.getSession().getAttribute("sessionUser");
		cartItem.setTBook(book);
		;
		cartItem.setTUser(user);
		cartItem.setCartitemCount(quantity);
		/*
		 * 2. 调用service完成添加
		 */
		service.add(cartItem);
		/*
		 * 3. 查询出当前用户的所有条目，转发到list.jsp显示
		 */
		return myCart();
	}

	/**
	 * 用异步刷洗跟新数据库中的书的数量
	 * 
	 * @return
	 */
	public String updateQuantity() {
		// 先查询，吧bookid和userid放到cart中
		service.updateQuantity(cartItemId, quantity);
		TCartitem cartItem = service.get(TCartitem.class, cartItemId);// 然后在查询
		TBook book = serviceTb
				.get(TBook.class, cartItem.getTBook().getBookId());
		BigDecimal b1 = new BigDecimal(book.getBookCurrPrice() + "");
		BigDecimal b2 = new BigDecimal(cartItem.getCartitemCount() + "");
		BigDecimal b3 = b1.multiply(b2);
		// 给客户端返回一个json对象
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"quantitys\"").append(":")
				.append(cartItem.getCartitemCount());
		sb.append(",");
		sb.append("\"subtotals\"").append(":").append(b3);
		sb.append("}");
		try {
			resp.getWriter().print(sb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 购物车条目的删除功能
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteCart() {
		/*
		 * 1. 获取cartItemIds参数 2. 调用service方法完成工作 3. 返回到list.jsp
		 */
		String sql = "delete from t_cartitem where cartitem_id=?";
		service.executeSQL(sql, cartItemId);
		/*
		 * TCartitem cart=service.get(TCartitem.class, cartItemId);
		 * System.out.println(cart.getCartitemCount());
		 * System.out.println(cart.getCartitemId());
		 * System.out.println(cart.getCartitemOrderBy()); service.delete(cart);
		 */
		return myCart();
	}

	/**
	 * 加载多个CartItem
	 * 
	 */
	public String loadCartItems() {
		Object[] cartItemIdArray = cartItemId.split(",");
		List<TCartitem> cartItemList = service.loadCartItems(cartItemIdArray);// 吧图书查询出来
		for (int i = 0; i < cartItemList.size(); i++) {
			TBook book = serviceTb.get(TBook.class, cartItemList.get(i)
					.getTBook().getBookId());
			cartItemList.get(i).setTBook(book);
		}
		/*
		 * 3. 保存，然后转发到/cart/showitem.jsp
		 */
		req.setAttribute("cartItemList", cartItemList);
		req.setAttribute("total", total);
		req.setAttribute("cartItemIds", cartItemId);
		return "showitem";
	}
}

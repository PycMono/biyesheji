package cn.edu.cdtu.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.cdtu.entity.TBook;
import cn.edu.cdtu.entity.TCartitem;
import cn.edu.cdtu.entity.TOrder;
import cn.edu.cdtu.entity.TOrderitem;
import cn.edu.cdtu.entity.TUser;
import cn.edu.cdtu.page.PageBean;
import cn.edu.cdtu.service.TBookService;
import cn.edu.cdtu.service.TCartitemService;
import cn.edu.cdtu.service.TOrderService;
import cn.edu.cdtu.service.TOrderitemService;

@Controller("orderAction")
@Scope("prototype")
public class OrderAction {
	HttpServletResponse resp = ServletActionContext.getResponse();
	HttpServletRequest req = ServletActionContext.getRequest();
	@Resource
	private TOrderService service;
	@Resource
	private TCartitemService serviceCar;
	@Resource
	private TBookService serviceBo;
	@Resource
	private TOrderitemService serviceTo;
	
	private String orderId;// 获取orderId
	private String btn;// btn说明了用户点击哪个超链接来访问本方法的;
	private String cartItemIds;

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCartItemIds() {
		return cartItemIds;
	}

	public void setCartItemIds(String cartItemIds) {
		this.cartItemIds = cartItemIds;
	}

	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取当前页码
	 * 
	 * @param req
	 * @return
	 */
	private int getPc() {
		int pc = 1;
		String param = req.getParameter("pc");
		if (param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch (RuntimeException e) {
			}
		}
		return pc;
	}

	/**
	 * 截取url，页面中的分页导航中需要使用它做为超链接的目标！
	 * 
	 * @param req
	 * @return
	 */
	private String getUrl() {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		/*
		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 */
		int index = url.lastIndexOf("&pc=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	public String myOrders() {

		// 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1

		int pc = getPc();

		// * 2. 得到url：...

		String url = getUrl();

		// * 3. 从当前session中获取User

		TUser user = (TUser) req.getSession().getAttribute("sessionUser");
		// * 4. 使用pc和cid调用service#findByCategory得到PageBean

		PageBean<TOrder> pb = service.findByUser(user.getUserId(), pc);

		// * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp

		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "list";
	}

	/**
	 * 加载订单,其实就是查看订单详细信息
	 */
	public String load() {
		TOrder order = service.load(orderId);
		req.setAttribute("order", order);
		req.setAttribute("btn", btn);// 得到点击按钮的状态
		return "desc";
	}

	/**
	 * 取消订单
	 * 
	 * @return
	 */
	public String cancel() {
		/*
		 * 校验订单状态
		 */
		int status = service.findStatus(orderId);
		if (status != 1) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "状态不对，不能取消！");
			return "msg";
		}
		service.updateStatus(orderId, 5);// 设置状态为取消！
		req.setAttribute("code", "success");
		req.setAttribute("msg", "您的订单已取消，您不后悔吗！");
		return "msg";
	}

	/**
	 * 确认收货
	 * 
	 * @return
	 */
	public String confirm() {
		/*
		 * 校验订单状态
		 */
		int status = service.findStatus(orderId);
		if (status != 3) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "状态不对，不能确认收货！");
			return "msg";
		}
		service.updateStatus(orderId, 4);// 设置状态为取消！
		req.setAttribute("code", "success");
		req.setAttribute("msg", "恭喜，交易成功！");
		return "msg";
	}

	/**
	 * 支付准备
	 */
	public String paymentPre() {
		req.setAttribute("order", service.load(orderId));
		return "pay";
	}

	/**
	 * 生成订单
	 */
	public String createOrder() {
		/*
		 * 1. 获取所有购物车条目的id，查询之
		 */
		Object[] cartItemIdArray = cartItemIds.split(",");
		List<TCartitem> cartItemList = serviceCar
				.loadCartItems(cartItemIdArray);
		if (cartItemList.size() == 0) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "您没有选择要购买的图书，不能下单！");
			return "msg";
		}
		/*
		 * 2. 创建Order
		 */
		String time=String.format("%tF %<tT", new Date());
		TOrder order = new TOrder();
		order.setOrderAddress(address);
		order.setOrderTime(time);// 下单时间
		order.setOrderStatus(1);// 设置状态，1表示未付款
		TUser owner = (TUser) req.getSession().getAttribute("sessionUser");
		order.setTUser(owner);// 设置订单所有者
		BigDecimal total = new BigDecimal("0");
		for (TCartitem cartItem : cartItemList) {
			TBook tbook = serviceBo.get(TBook.class, cartItem.getTBook()
					.getBookId());
			BigDecimal b1 = new BigDecimal(tbook.getBookCurrPrice() + "");
			BigDecimal b2 = new BigDecimal(cartItem.getCartitemCount() + "");
			BigDecimal b3 = b1.multiply(b2);
			total = total.add(b3);
		}
		order.setOrderTotal(total);// 设置总计
		/*
		 * 3. 创建List<OrderItem> 一个CartItem对应一个OrderItem
		 */
		List<TOrderitem> orderItemList = new ArrayList<TOrderitem>();
		for (TCartitem cartItem : cartItemList) {
			TBook tbook = serviceBo.get(TBook.class, cartItem.getTBook()
					.getBookId());
			BigDecimal b1 = new BigDecimal(tbook.getBookCurrPrice() + "");
			BigDecimal b2 = new BigDecimal(cartItem.getCartitemCount() + "");
			BigDecimal b3 = b1.multiply(b2);
			TOrderitem orderItem = new TOrderitem();
			orderItem.setOrderitemCount(cartItem.getCartitemCount());
			orderItem.setOrderitemSubtotal(b3);
			orderItem.setTBook(cartItem.getTBook());
			orderItem.setTOrder(order);
			orderItemList.add(orderItem);
		}
		order.setOrderItemList(orderItemList);
		/*
		 * 4. 调用service完成添加
		 */
		String sql="select * from t_order where order_time=?";
		service.add(order);
		List<TOrder>  orderid=service.executeSQLQuery(TOrder.class, sql, time);
		/**
		 * 生成订单项
		 */
		
		/*
		 * 2. 循环遍历订单的所有条目,让每个条目生成一个Object[] 多个条目就对应Object[][] 执行批处理，完成插入订单条目
		 */
		int len = order.getOrderItemList().size();
		for (int i = 0; i < len; i++) {
			TOrderitem item = order.getOrderItemList().get(i);
			TBook tbook = serviceBo.get(TBook.class, item.getTBook()
					.getBookId());
			TOrderitem ord=new TOrderitem();
			ord.setTBook(tbook);
			System.out.println("-----");
			System.out.println(orderid.get(0).getOrderId()+"----------------");
			ord.setTOrder(orderid.get(0));
			ord.setOrderitemImage(tbook.getBookImageB());
			ord.setOrderitemName(tbook.getBookName());
			ord.setOrderitemCount(item.getOrderitemCount());
			ord.setOrderitemPrice(tbook.getBookCurrPrice());
			ord.setOrderitemSubtotal(tbook.getBookCurrPrice());
			serviceTo.save(ord);
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		 * 5. 保存订单，转发到ordersucc.jsp
		 */
		req.setAttribute("order", order);
		return "ordersucc";
	}

}

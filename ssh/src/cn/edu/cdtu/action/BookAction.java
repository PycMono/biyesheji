package cn.edu.cdtu.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.cdtu.entity.TBook;
import cn.edu.cdtu.page.PageBean;
import cn.edu.cdtu.service.TBookService;

@Controller
@Scope("prototype")
public class BookAction {

	HttpServletResponse resp = ServletActionContext.getResponse();
	HttpServletRequest req = ServletActionContext.getRequest();
	private String bookName;
	private String categoryId;
	private String bookId;
	private String bookAuthor;
	private String bookPress;
	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPress() {
		return bookPress;
	}

	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}

	

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Resource
	private TBookService service;

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
	/*
	 * http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=3
	 * /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
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

	/**
	 * 按分类查,当我点击左边时用分类id查询的
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory() {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc();
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl();
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		// String cid = req.getParameter("cid");
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */

		PageBean<TBook> pb = service.findByCategory(categoryId, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		// System.out.println(pb.get);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "list";
	}

	/**
	 * 按出版社查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByPress() {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc();
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl();
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean<TBook> pb = service.findByPress(bookPress, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "list";
	}

	/**
	 * 按图名查，点击list页面上的名称时这样查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByBname() {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc();
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl();
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		// String bname = req.getParameter("bname");
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		System.out.println(bookName);
		PageBean<TBook> pb = service.findByBname(bookName, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "list";
	}

	/**
	 * 按bid查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByBid() {
		TBook book = service.findByBid(bookId);// 通过bid得到book对象
		req.setAttribute("book", book);// 保存到req中
		return "desc";// 转发到desc.jsp
	}

	/**
	 * 按作者查
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByAuthor() {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc();
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl();
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		System.out.println(bookAuthor);
		PageBean<TBook> pb = service.findByAuthor(bookAuthor, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "list";
	}
	
	

	/**
	 * 多条件组合查询,所谓高级查询
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCombination(){
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc();
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl();
		PageBean<TBook> pb = service.findByCombination(bookName, bookAuthor, bookPress, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "list";
	}
}

package cn.edu.cdtu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.cdtu.entity.TBook;
import cn.edu.cdtu.entity.TCategory;
import cn.edu.cdtu.page.PageBean;
import cn.edu.cdtu.service.TBookService;
import cn.edu.cdtu.service.TCategoryService;

@Controller
@Scope("prototype")
public class AdminBookAction {
	HttpServletRequest req = ServletActionContext.getRequest();
	HttpServletResponse rep=ServletActionContext.getResponse();
	@Resource
	private TCategoryService serviceTc;
	@Resource
	private TBookService service;
	///////////////////////////////////
	private String bookName;
	private String categoryId;
	private String bookId;
	private String bookAuthor;
	private String bookPress;
	private String pid;
	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	///////////////////////////////////
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


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	public String getBookId() {
		return bookId;
	}


	public void setBookId(String bookId) {
		this.bookId = bookId;
	}


	/**
	 * 查询所有分类
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAlls() {
		/*
		 * 1. 通过service得到所有的分类 2. 保存到request中，转发到left.jsp
		 */
		List<TCategory> parents = serviceTc.findAll();
		/*
		 * 2. 循环遍历所有的一级分类，为每个一级分类加载它的二级分类
		 */
		for (TCategory parent : parents) {
			// 查询出当前父分类的所有子分类
			List<TCategory> childrens = serviceTc.findByParent(parent
					.getCategoryId());
			System.out.println(childrens.size());
			parent.setCategory(childrens);
		}
		req.setAttribute("parents", parents);
		return "left";
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
	
	

	/**
	 * 添加图书：第一步,将一级分类显示在add.jsp中
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addPre() {
		/*
		 * 1. 获取所有一级分类，保存之
		 * 2. 转发到add.jsp，该页面会在下拉列表中显示所有一级分类
		 */
		/*
		 * 1. 通过service得到所有的分类 2. 保存到request中，转发到left.jsp
		 */
		List<TCategory> parents = serviceTc.findAll();
	
		/*
		 * 2. 循环遍历所有的一级分类，为每个一级分类加载它的二级分类
		 */
		for (TCategory parent : parents) {
			// 查询出当前父分类的所有子分类
			List<TCategory> childrens = serviceTc.findByParent(parent.getCategoryId());
			System.out.println(childrens.size());
				parent.setCategory(childrens);
		}
		req.setAttribute("parents", parents);
		return "add";
	}
	/**
	 * 通过Ajax显示一级分类下所有二级分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxFindChildren() throws IOException {
		/*
		 * 1. 获取pid
		 * 2. 通过pid查询出所有2级分类
		 * 3. 把List<Category>转换成json，输出给客户端
		 */
		List<TCategory> children = serviceTc.findByParent(pid);
		System.out.println(children.get(0).getCategoryName()+"----------------------------------");
		String json = toJson(children);
		rep.getWriter().print(json);
		return null;
	}
	/**
	 * 转换诶json格式
	 * @param categoryList
	 * @return
	 */
	private String toJson(List<TCategory> categoryList) {
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < categoryList.size(); i++) {
			sb.append(toJson(categoryList.get(i)));
			if(i < categoryList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	/**
	 * 转换为json格式2
	 * @param category
	 * @return
	 */
	private String toJson(TCategory category) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"categoryId\"").append(":").append("\"").append(category.getCategoryId()).append("\"");
		sb.append(",");
		sb.append("\"categoryName\"").append(":").append("\"").append(category.getCategoryName()).append("\"");
		sb.append("}");
		return sb.toString();
	}
}

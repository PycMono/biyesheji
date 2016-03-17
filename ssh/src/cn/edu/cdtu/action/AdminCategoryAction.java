package cn.edu.cdtu.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.cdtu.entity.TBook;
import cn.edu.cdtu.entity.TCategory;
import cn.edu.cdtu.service.TAdminService;
import cn.edu.cdtu.service.TBookService;
import cn.edu.cdtu.service.TCategoryService;

@Controller
@Scope("prototype")
public class AdminCategoryAction {
	HttpServletRequest req = ServletActionContext.getRequest();
	TCategory category;
	private String cid;// 分類的id
	private String pid;// 二級分類裡面一級分類的id
	private String desc;// 分類描述
	private String cname;// 分類名稱
	@Resource
	private TCategoryService serviceTc;
	@Resource
	private TBookService serviceBo;
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public TCategory getCategory() {
		return category;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setCategory(TCategory category) {
		this.category = category;
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
	public String findAll() {
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
		return "list";
	}

	/**
	 * 添加一级分类
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addParent() {
		/*
		 * 1. 封装表单数据到Category中 2. 调用service的add()方法完成添加 3.
		 * 调用findAll()，返回list.jsp显示所有分类
		 */
		serviceTc.add(category);
		return findAll();
	}

	/**
	 * 添加第二分类：第一步
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addChildPre() {
		String pid = req.getParameter("pid");// 当前点击的父分类id
		List<TCategory> parents = serviceTc.findParents();
		req.setAttribute("pid", pid);
		req.setAttribute("parents", parents);
		return "add2";
	}

	/**
	 * 添加二級分類，第二不
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */

	public String addChild() {
		// 子類
		TCategory child = new TCategory();
		child.setCategoryName(cname);
		child.setCategoryDesc(desc);
		// 父類
		TCategory parent = new TCategory();
		parent.setCategoryId(pid);
		child.setTCategory(parent);

		serviceTc.add(child);
		return findAll();
	}

	/**
	 * 修改一级分类：第一步
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editParentPre() {
		TCategory parent = serviceTc.get(TCategory.class, cid);
		req.setAttribute("parent", parent);
		return "edit";
	}

	/**
	 * 修改一级分类：第二步
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editParent() {

		serviceTc.update(category);
		return findAll();
	}

	/**
	 * 修改二级分类：第一步
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editChildPre() {
		TCategory child = serviceTc.get(TCategory.class, cid);
		req.setAttribute("child", child);
		req.setAttribute("parents", serviceTc.findParents());
		return "edit2";
	}

	/**
	 * 修改二级分类：第二步
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editChild() {
		System.out.println(pid);
		System.out.println(cname);
		System.out.println(desc);
		// 子類
		TCategory child = new TCategory();
		child.setCategoryName(cname);
		child.setCategoryDesc(desc);
		child.setCategoryId(cid);
		// 父類
		TCategory parent = new TCategory();
		parent.setCategoryId(pid);
		child.setTCategory(parent);
		serviceTc.update(child);
		return findAll();
	}

	/**
	 * 删除一级分类
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteParent() {
		/*
		 * 1. 获取链接参数cid，它是一个1级分类的id 2. 通过cid，查看该父分类下子分类的个数 3.
		 * 如果大于零，说明还有子分类，不能删除。保存错误信息，转发到msg.jsp 4. 如果等于零，删除之，返回到list.jsp
		 */
		int cnt = serviceTc.findChildrenCountByParent(pid);
		if (cnt > 0) {
			req.setAttribute("msg", "该分类下还有子分类，不能删除！");
			return "msg";
		} else {
			String sql = "delete from t_category where category_id=?";
			serviceTc.executeSQL(sql, pid);
			return findAll();
		}
	}
	
	/**
	 * 删除2级分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteChild() {
		/*
		 * 1. 获取cid，即2级分类id
		 * 2. 获取该分类下的图书个数
		 * 3. 如果大于零，保存错误信息，转发到msg.jsp
		 * 4. 如果等于零，删除之，返回到list.jsp
		 */
		int cnt = serviceBo.findBookCountByCategory(cid);
		System.out.println(cnt);
		if(cnt > 0) {
			req.setAttribute("msg", "该分类下还存在图书，不能删除！");
			return "msg";
		} else {
			String sql = "delete from t_category where category_id=?";
			serviceTc.executeSQL(sql, cid);
			return findAll();
		}
	}
	
	
}

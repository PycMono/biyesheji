package cn.edu.cdtu.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.cdtu.entity.TCategory;
import cn.edu.cdtu.service.TCategoryService;

@Controller
@Scope("prototype")
public class CategoryAction {
	HttpServletResponse resp = ServletActionContext.getResponse();
	HttpServletRequest req = ServletActionContext.getRequest();
	@Resource
	private TCategoryService service;

	/**
	 * 查询所有分类
	 */
	public String findAll() {
		/*
		 * 1. 通过service得到所有的分类 2. 保存到request中，转发到left.jsp
		 */
		List<TCategory> parents = service.findAll();
	
		/*
		 * 2. 循环遍历所有的一级分类，为每个一级分类加载它的二级分类
		 */
		for (TCategory parent : parents) {
			// 查询出当前父分类的所有子分类
			List<TCategory> childrens = service.findByParent(parent.getCategoryId());
			System.out.println(childrens.size());
				parent.setCategory(childrens);
		}
	
		req.setAttribute("parents", parents);
		return "left";
	}
}

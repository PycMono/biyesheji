package cn.edu.cdtu.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import cn.edu.cdtu.entity.TCategory;
import cn.edu.cdtu.entity.TUser;
import cn.edu.cdtu.service.TCategoryService;
import cn.edu.cdtu.service.TUserService;
import cn.edu.cdtu.utils.MD5Util;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {
	HttpServletResponse resp = ServletActionContext.getResponse();
	HttpServletRequest req = ServletActionContext.getRequest();
	@Resource
	private TUserService service;
	@Resource
	private TCategoryService categoryService;
	private TUser user;

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	/**
	 * 注册页面
	 * 
	 * @return
	 */
	public String registUI() {
		/*
		 * 2. 校验之, 如果校验失败，保存错误信息，返回到regist.jsp显示
		 */
		Map<String, String> errors = validateRegist();
		if (errors.size() > 0) {
			// req.setAttribute("form", formUser);
			req.setAttribute("errors", errors);
			return "regist";
		}
		/*
		 * 3. 使用service完成业务
		 */
		user.setUserPassword(MD5Util.Md5(user.getUserPassword()));
		service.save(user);
		/*
		 * 保存成功信息，转发到msg.jsp显示！
		 */
		req.setAttribute("msg", "新用户注册成功！");
		return "msg";
	}

	/*
	 * 注册校验 对表单的字段进行逐个校验，如果有错误，使用当前字段名称为key，错误信息为value，保存到map中 返回map
	 */
	private Map<String, String> validateRegist() {

		// HttpSession session
		Map<String, String> errors = new HashMap<String, String>();
		/*
		 * 1. 校验登录名
		 */
		String loginname = user.getUserName();
		TUser u = service.isRegisted(loginname);
		if (loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "用户名不能为空！");
		} else if (loginname.length() < 3 || loginname.length() > 20) {
			errors.put("loginname", "用户名长度必须在3~20之间！");
		} else if (u != null) {
			errors.put("loginname", "用户名已被注册！");
		}

		/*
		 * 2. 校验登录密码
		 */
		String loginpass = user.getUserPassword();
		if (loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "密码不能为空！");
		} else if (loginpass.length() < 3 || loginpass.length() > 20) {
			errors.put("loginpass", "密码长度必须在3~20之间！");
		}

		/*
		 * 3. 确认密码校验
		 */
		String reloginpass = user.getReloginpass();
		if (reloginpass == null || reloginpass.trim().isEmpty()) {
			errors.put("reloginpass", "确认密码不能为空！");
		} else if (!reloginpass.equals(loginpass)) {
			errors.put("reloginpass", "两次输入不一致！");
		}

		/*
		 * 5. 验证码校验
		 */
		String verifyCode = user.getInputClass();
		String vcode = (String) (ActionContext.getContext().getSession()
				.get("sessionCode"));
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if (!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode", "验证码错误！");
		}

		return errors;
	}

	/**
	 * 登录功能
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loginUI() {
		/*
		 * 2. 校验
		 */
		Map<String, String> errors = validateLogin();
		if (errors.size() > 0) {
			req.setAttribute("errors", errors);
			return "login";
		}

		/*
		 * 3. 调用userService#login()方法
		 */
		TUser u = service.validateLoginInfo(user.getUserName(),
				MD5Util.Md5(user.getUserPassword()));
		/*
		 * 4. 开始判断
		 */
		if (u == null) {
			req.setAttribute("msg", "用户名或密码错误！");
			return "login";
		} else {
			List<TCategory> parents = categoryService.findAll();
			for (TCategory parent : parents) {
				List<TCategory> childrens = categoryService.findByParent(parent.getCategoryId());
				System.out.println(childrens.size());
				parent.setCategory(childrens);
			}
			req.setAttribute("parents", parents);
			// 保存用户到session
			req.getSession().setAttribute("sessionUser", u);
			// 获取用户名保存到cookie中
			String loginname = u.getUserName();
			Cookie cookie = new Cookie("loginname", loginname);
			cookie.setMaxAge(60 * 60 * 24 * 10);// 保存10天
			resp.addCookie(cookie);
			return "index";// 回到主页
		}
	}

	/*
	 * 登录校验方法
	 */
	private Map<String, String> validateLogin() {
		Map<String, String> errors = new HashMap<String, String>();
		
		/*
		 * 1. 校验登录名
		 */
		String loginname = user.getUserName();
		if (loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "用户名不能为空！");
		} else if (loginname.length() < 3 || loginname.length() > 20) {
			errors.put("loginname", "用户名长度必须在3~20之间！");
		}

		/*
		 * 2. 校验登录密码
		 */

		String loginpass = user.getUserPassword();
		if (loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "密码不能为空！");
		} else if (loginpass.length() < 3 || loginpass.length() > 20) {
			errors.put("loginpass", "密码长度必须在3~20之间！");
		}
		/*
		 * 5. 验证码校验
		 */
		String verifyCode = user.getInputClass();
		String vcode = (String) (ActionContext.getContext().getSession()
				.get("sessionCode"));
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if (!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode", "验证码错误！");
		}
		return errors;
	}

	/**
	 * ajax验证码是否正确校验
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateVerifyCode() throws IOException {

		/*
		 * 1. 获取输入框中的验证码
		 */
		String verifyCode = req.getParameter("verifyCode");
		/*
		 * 2. 获取图片上真实的校验码
		 */
		String vcode = (String) (ActionContext.getContext().getSession()
				.get("sessionCode"));
		/*
		 * 3. 进行忽略大小写比较，得到结果
		 */
		boolean b = verifyCode.equalsIgnoreCase(vcode);
		/*
		 * 4. 发送给客户端
		 */
		resp.getWriter().print(b);
		return null;
	}

	/**
	 * 退出功能
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit() {
		req.getSession().invalidate();
		return "index";
	}

	/**
	 * 修改密码　
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updatePassword() {
		/*
		 * 1. 封装表单数据到user中 2. 从session中获取uid 3.
		 * 使用uid和表单中的oldPass和newPass来调用service方法 >
		 * 如果出现异常，保存异常信息到request中，转发到pwd.jsp 4. 保存成功信息到rquest中 5. 转发到msg.jsp
		 */
		TUser u = (TUser) req.getSession().getAttribute("sessionUser");
		// 判断用户名是否存在
		// 如果用户没有登录，返回到登录页面，显示错误信息
		if (u == null) {
			req.setAttribute("msg", "您还没有登录！");
			return "login";
		}
		// 判断两次输入的新密码密码是否相等
		else if (user.getNewpass() != user.getReloginpass()) {
			req.setAttribute("newpwd", "两次输入的密码不一致！");
			return "pwd";
		}
		// 判断输入的原密码是否正确
		else if (user.getUserPassword() != u.getUserPassword()) {
			req.setAttribute("pwd", "你输入的原密码不正确");
			return "pwd";
		} else {
			// 取出session中缓存的主键
			String uuid = req.getSession().getAttribute("uuid").toString();
			// 设置主键
			user.setUserId(uuid);
			// 取出session中name
			user.setUserName(u.getUserName());
			// 设置加密
			user.setUserPassword(MD5Util.Md5(user.getNewpass()));
			// 更新
			service.update(user);
			req.setAttribute("msg", "修改密码成功");
			req.setAttribute("code", "success");
			return "msg";
		}
	}

	/**
	 * ajax用户名是否注册校验
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateLoginname() throws IOException {
		boolean b = true;// 采用Ajax判断、用户名是否注册
		/*
		 * 1. 获取用户名
		 */
		String loginname = req.getParameter("loginname");
		/*
		 * 2. 通过service得到校验结果
		 */

		// 判断用户名是否存在
		TUser u = service.isRegisted(loginname);
		if (u != null) {
			b = false;
		}
		/*
		 * 3. 发给客户端
		 */
		resp.getWriter().print(b);
		return null;
	}

}

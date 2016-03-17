package cn.edu.cdtu.action;

import java.io.ByteArrayInputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.cdtu.utils.RandomNumUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class YanZMAction extends ActionSupport{
	private ByteArrayInputStream inputStream;
	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	/**
	 * 生产验证码
	 */
	public String randomIamge() throws Exception {
		RandomNumUtil rdnu = RandomNumUtil.Instance();
		this.setInputStream(rdnu.getImage());// 取得带有随机字符串的图片
		ActionContext.getContext().getSession()
				.put("sessionCode", rdnu.getString());// 取得随机字符串放入HttpSession
	
		return SUCCESS;
	}
}

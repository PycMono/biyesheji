package cn.edu.cdtu.action;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.cdtu.entity.TBook;
import cn.edu.cdtu.entity.TCategory;
import cn.edu.cdtu.service.TBookService;
import cn.edu.cdtu.service.TCategoryService;

@Controller
@Scope("prototype")
public class AdminAddBookAction {
	HttpServletRequest req = ServletActionContext.getRequest();
	@Resource
	private TCategoryService serviceTc;
	@Resource
	private TBookService service;
	private File bookImageS; // 获取上传文件
	private File bookImageB; // 获取上传文件
	private String bookImageSFileName;
	private String bookImageBFileName;
	private String cid;
	private String pid;
	TBook book;
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}



	public TBook getBook() {
		return book;
	}

	public void setBook(TBook book) {
		this.book = book;
	}

	public String getBookImageSFileName() {
		return bookImageSFileName;
	}

	public void setBookImageSFileName(String bookImageSFileName) {
		this.bookImageSFileName = bookImageSFileName;
	}

	public String getBookImageBFileName() {
		return bookImageBFileName;
	}

	public void setBookImageBFileName(String bookImageBFileName) {
		this.bookImageBFileName = bookImageBFileName;
	}

	public File getBookImageS() {
		return bookImageS;
	}

	public void setBookImageS(File bookImageS) {
		this.bookImageS = bookImageS;
	}

	public File getBookImageB() {
		return bookImageB;
	}

	public void setBookImageB(File bookImageB) {
		this.bookImageB = bookImageB;
	}

	/**
	 * 添加图书
	 * 
	 * @return
	 */
	public String addBookTwo() {
		if (bookImageSFileName != null && bookImageBFileName != null) {
			String realpath = ServletActionContext.getServletContext()
					.getRealPath("/book_img");
			File savefile = new File(new File(realpath), bookImageBFileName);
			File savefile2 = new File(new File(realpath), bookImageSFileName);
			if (!savefile.getParentFile().exists())// 判断该文件夹是否存在
				savefile.getParentFile().mkdirs();

			try {
				FileUtils.copyFile(bookImageB, savefile);
				FileUtils.copyFile(bookImageS, savefile2);
				// 将文件copy到这个目录
				// 保存成功信息转发到msg.jsp
			book.setBookImageB("book_img/"+bookImageBFileName);
			book.setBookImageS("book_img/"+bookImageSFileName);
			TCategory catetegory=new TCategory();
			catetegory.setCategoryId(pid);
			book.setTCategory(catetegory);
			service.save(book);
			req.setAttribute("msg", "添加图书成功！");
			return "msg";
			} catch (IOException e) {
				//显示所有的一级分类
				req.setAttribute("parents", serviceTc.findParents());//所有一级分类
				req.setAttribute("msg", "添加图片失败！");
				return "add";
			}
		} else {
			//显示所有的一级分类
			req.setAttribute("parents", serviceTc.findParents());//所有一级分类
			req.setAttribute("msg", "添加图片失败！");
			return "add";
		}

	}

}

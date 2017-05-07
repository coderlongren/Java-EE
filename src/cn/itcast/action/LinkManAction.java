package cn.itcast.action;



import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	private  LinkManService linkManService;
	//模型驱动的对象 需要自己手动创建
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
		
	}
	private CustomerService customerService;
	//提交表单信息 使用模型驱动
	/**
	 *可以封装联系人基本信息
	 *但是cid是客户的id不能直接封装
	 *吧cid封装到linkman实体类里面的customer对象里面
	 * @return
	 */
	
	
	/**
	 * 1 在action里面成员变量 
	 * 一个表示上传文件
	 * 一个表示文件名称
	 * 2生成成员变量的get和set方法
	 * 
	 * 
	 * 上传文件
	 * 
	 * @return
	 */
	
	//变量的名称需要是表单里面文件上传的name 值
	 private File upload;
	 
	 //上传文件名称 表单里面文件上传项的name值再加上FileName
	 private String uploadFileName;
	 
	 //生成成员变量的get 和 set方法
	 
	 //还有mime变量的方法
	 
	 public String showLinkMan(){
		 //使用模型驱动
		 int linkid = linkMan.getLinkid();
		 //根据linkid查询联系人对象
		 LinkMan linkman = linkManService.findOne(linkid);
		 //需要所有客户的list集合
		 List<Customer> listCustomer = customerService.findAll();
		 //放到域对象中去
		 HttpServletRequest request = ServletActionContext.getRequest();
		 request.setAttribute("link", linkman);
		 request.setAttribute("listCustomer", listCustomer);
		 
		 return "showLinkMan";
	 }
	 public String list(){
		 List<LinkMan> list = linkManService.listLinkMan();
		 ServletActionContext.getRequest().setAttribute("list", list);
		 return "list";
	 }
	 
	public String addLinkMan() throws IOException{
		
		//判断是否需要上传文件
		if (upload != null){
			//写上传代码
			//在服务器文件夹里面创建 文件
			File serverFile = new File("F:\\sshcd"+"/"+uploadFileName);
			//把上传文件复制到服务器文件里面
			//struts2帮我们做了文件上传的类 调用他就可以了
			FileUtils.copyFile(upload, serverFile);
		
		}
		//1:原始方法实现
		
//		
//		
//		String scid = ServletActionContext.getRequest().getParameter("cid");
//		int cid = Integer.parseInt(scid);
//		//创建customer对象
//		Customer c = new Customer();
//		c.setCid(cid); 
//		linkMan.setCustomer(c);
//			 
//			
	//2:简化的方式来实现
//		linkMan.addLI=inkMan(linkMan);
		linkManService.addLinkMan(linkMan);
		return "addLinkMan";
	}
	
	
	
	public String toAddPage(){
//		System.out.println("actioning.....");
		//1:查询所有客户 把所有的客户列表集合传递到页面中区（放到域对象中去
		//调用 service里面的方法得到所有的用户）
		List<Customer> listCustomer  = customerService.findAll();  
//		for (Customer customer:listCustomer){
//			System.out.println(customer);
//		}
//		System.out.println("ssfffsdfffffffffffffffff");
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "toAddPage";
		
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


}

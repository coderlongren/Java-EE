package cn.itcast.action;



import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	private  LinkManService linkManService;
	private LinkMan linkMan;
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
	
	public String addLinkMan(){
		//1:原始方法实现
		
		
		
		String scid = ServletActionContext.getRequest().getParameter("cid");
		int cid = Integer.parseInt(scid);
		//创建customer对象
		Customer c = new Customer();
		c.setCid(cid); 
		linkMan.setCustomer(c);
			 
		
	
		
		//2:简化的方式来实现
		
		
		
		
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


}

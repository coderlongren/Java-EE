package cn.itcast.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	//添加客户功能
	public void add(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	//客户列表功能
	@SuppressWarnings("all")
	public List<Customer> findAll() {
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	//根据id查询
	public Customer findOne(int cid) {
		return this.getHibernateTemplate().get(Customer.class, cid);
	}

	//删除功能
	public void delete(Customer c) {
		this.getHibernateTemplate().delete(c);
	}

	//修改功能
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

	//查询记录数
	@SuppressWarnings("all")
	public int findCount() {
		//调用hibernateTemplate里面的find方法实现
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		//从list中把值得到
		if(list != null && list.size()!=0) {
			Object obj = list.get(0);
			//变成int类型
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}

	//分页查询操作
	public List<Customer> findPage(int begin, int pageSize) {
		//第一种 使用hibernate底层代码实现（了解）
		//得到sessionFactory
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
		//得到session对象
//		Session session = sessionFactory.getCurrentSession();
		//设置分页信息
//		Query query = session.createQuery("from Customer");
//		query.setFirstResult(begin);
//		query.setMaxResults(pageSize);
//		List<Customer> list = query.list();
		
		//第二种 使用离线对象和hibernateTemplate的方法实现
		//1 创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		//调用hibernateTemplate的方法实现
//		第一个参数是离线对象
//		第二个参数是开始位置
//		第三个参数是每页记录数
		List<Customer> list = 
				(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	//条件查询
	@SuppressWarnings("all")
	public List<Customer> findCondition(Customer customer) {
		//第一种方式： 
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
		//得到session对象
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("from Customer where custName like ?");
//		query.setParameter(0, "%"+customer.getCustName()+"%");
//		List<Customer> list = query.list();
		
		//第二种方式 ： 调用hibernateTemplate的find方法实现
//		List<Customer> list = (List<Customer>) this.getHibernateTemplate().
//			find("from Customer where custName like ?", "%"+customer.getCustName()+"%");
		//拼接hql语句实现
		
		
		//第三种方式
		// 1 创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// 2 设置对实体类哪个属性
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		// 3 调用hibernateTemplate里面的方法得到list集合
		List<Customer> list = 
				(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

}



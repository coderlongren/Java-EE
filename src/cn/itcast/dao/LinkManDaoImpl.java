package cn.itcast.dao;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	
	//添加联系人的方法
	@Override
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
		}

	@Override
	public List<LinkMan> list() {
//		this.getHibernateTemplate().find("from LinkMan");
		return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
	}

	@Override
	public LinkMan findOne(int linkid) {
		
		return this.getHibernateTemplate().get(LinkMan.class, linkid);
	}



}

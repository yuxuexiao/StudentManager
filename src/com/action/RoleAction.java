package com.action;

import java.util.List;

import com.bean.RoleBean;
import com.dao.RoleDao;

public class RoleAction {
	
	static RoleDao rDao=new RoleDao();
	public List<RoleBean> getNames() {
		List<RoleBean> list=rDao.getNames();
		return list;
	}
	public int getRid(String role)
	{
		
		return rDao.getRid(role);
	}

}

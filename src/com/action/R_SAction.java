package com.action;

import java.util.ArrayList;
import java.util.List;

import com.bean.UserKeyBean;
import com.dao.R_SDao;

public class R_SAction {

	 R_SDao rsDao=new R_SDao();
	public List<Integer> getSid(UserKeyBean uBean) {
		
		return rsDao.getSid(uBean);
	}

}

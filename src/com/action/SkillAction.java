package com.action;

import java.util.List;

import com.bean.SkillBean;
import com.dao.SkillDao;

public class SkillAction {
	SkillDao sd=new SkillDao();
	public String getRoot()
	{
		return sd.getRoot();
	}
	
	public List<SkillBean> getChildNode(int pid)
	{
		List<SkillBean> list=sd.skill(pid);
		return list;
	}
	
}

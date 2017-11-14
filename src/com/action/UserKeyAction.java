package com.action;

import java.util.Vector;

import com.bean.UserKeyBean;
import com.dao.UserDao;

public class UserKeyAction {
	UserDao ud=new UserDao();
	public UserKeyBean userKey(UserKeyBean bean)
	{
		return ud.userkey(bean);
	}
	public Vector<Vector> getRoot() {
		return ud.getRoot();
	}
	public Vector<Vector> getTeacher() {
		return ud.getTeacher();
	}
	public int initPassRoot(int id) {
		return ud.initPassRoot(id);
	}
	public int delRoot(int id) {
		
		return ud.delRoot(id);
	}
	public UserKeyBean getAthority(int aid) {
		return ud.getAthority(aid);
	}
	public int updateAuthority(UserKeyBean ukbHave) {
		return ud.updateAuthority(ukbHave);
	}
	public UserKeyBean getControlAut(UserKeyBean bean) {
		return ud.getControlAut(bean);
	}
	public int addUser(UserKeyBean ukb) {
		return ud.addUser(ukb);
	}
	public Vector<String> getNotExsistTeacher() {
		return ud.getNotExsistTeacher();
	}
	public int updatePass(String username, String newPass) {
		return ud.updatePass(username,newPass);
	}
	public Vector<Vector> getStudentById(String id) {
		// TODO Auto-generated method stub
		return ud.getStudentById(id);
	}
	public int initStudentPass(String values) {
		return ud.initStudentPass(values);
	}
	
}

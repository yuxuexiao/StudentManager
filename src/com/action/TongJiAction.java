package com.action;

import java.util.Vector;

import com.dao.TongJiDao;

public class TongJiAction {
	TongJiDao tDao=new TongJiDao();
	public Vector<String> getAllDepart(String education) {
		return tDao.getAllDepart(education);
	}
	public Vector<String> getAllAddress() {
	
		return tDao.getAllAddress();
	}
	public Vector<String> getAllProfessionByDepartmen(String department) {
		
		return tDao.getAllProfessionByDeprtment(department);
	}
	public Vector<Vector> getCount(String education, Vector<String> profession, Vector<String> adds) {
		return tDao.getCount(education,profession,adds);
	}
	public Vector getCounts(String education, String department, Vector<String> adds) {
		// TODO Auto-generated method stub
		return tDao.getCounts(education,department,adds);
	}
	

}

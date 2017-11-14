package com.action;

import java.util.List;
import java.util.Vector;

import com.bean.DepartmentBean;
import com.dao.DepartmentDao;

public class DepartmentAction {
	DepartmentDao dDao=new DepartmentDao();
	public List<String> selAllDepartment() {
		return dDao.selAllDepartment();
	}
	public Vector<Vector> selAllNormalProfession(List<String> listD) {
		// TODO Auto-generated method stub
		return dDao.selAllNormalProfession(listD);
	}
	public Vector<Vector> selAllGraduationProfession(List<String> listD) {
		// TODO Auto-generated method stub
		return dDao.selAllGraduationProfession(listD);
	}
	public int importProfession(Vector<Vector> vecNormalP, Vector<Vector> vecGraduationP) {
		// TODO Auto-generated method stub
		return dDao.importProfession(vecNormalP,vecGraduationP);
	}
	public Vector countRows() {
		return dDao.countRows();
	}
	public Vector<Vector> getDepartmentTable(Vector count, int rowsPage, int currentPage) {
		return dDao.getDepartmentTable(count,rowsPage,currentPage);
	}
	public Vector<Vector> getDepartOne(String value, String[] ng) {
		return dDao.getDepartOne(value,ng);
	}
	public int updateDepartOne(int id, String code, String profession) {
		return dDao.updateDepartOne(id,code,profession);
	}
	public int delDepartOne(int id) {
		return dDao.delDepartOne(id);
	}
	public int addDepartmentOne(String name) {
		return dDao.addDepartmentOne(name);
	}
	public int addProfessionOne(DepartmentBean dBeanN) {
		return dDao.addProfessionOne(dBeanN);
	}
	public int delBatchProfession(Vector<Vector> delBatch) {
		return dDao.delBatchProfession(delBatch);
	}


}

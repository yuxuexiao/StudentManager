package com.action;

import java.util.Vector;

import com.bean.AuditBean;
import com.bean.StudentBean;
import com.bean.UserKeyBean;
import com.dao.AuditDao;

public class AuditAction {
	AuditDao aDao=new AuditDao();
	public Vector<String> getAllDepart() {
		return aDao.getAllDepart();
	}
	public Vector<Vector> getStudentAuditCenter(AuditBean bean, int currentPage2, int rowsPage) {
		
		return aDao.getStudentAuditCenter(bean,currentPage2,rowsPage);
	}
	public StudentBean getStudentByKey(UserKeyBean uBean) {
		// TODO Auto-generated method stub
		return aDao.getStudentByKey(uBean);
	}
	public int updateStudentOwn(StudentBean selfBean) {
		return aDao.updateStudentOwn(selfBean);
	}
	public Vector<Vector> getStudentAuditTeacher(AuditBean bean, int currentPage2, int rowsPage) {
		return aDao.getStudentAuditTeacher(bean,currentPage2,rowsPage);
	}
	public int updateStudentTeacher(String values,String reason,int audit) {
		return aDao.updateStudentTeacher(values,reason,audit);
	}
	public String[] tempStudent(String values) {
		return aDao.tempStudent(values);
	}
	public int getCountRows(int rid, String Department, String education) {
		return aDao.getCountRows(rid,Department,education);
	}
	public Vector<String[]> tempBatchStudent(Vector<Vector> vector) {
		return aDao.tempBatchStudent(vector);
	}
	public int updateBatchTeacher(Vector<Vector> Batch, int audit) {
		
		return aDao.updateBatchTeacher(Batch,audit);
	}

}

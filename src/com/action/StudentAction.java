package com.action;

import java.util.List;
import java.util.Vector;

import com.bean.StudentBean;
import com.bean.UserKeyBean;
import com.dao.StudentDao;

public class StudentAction {

	 StudentDao sDao=new StudentDao();
	
	public List<Vector> selAllStudent(StudentBean sBean, int rowsPage2, int currentPage2, String stuTableName, String teacher) {
		
		return sDao.selAllStudent(sBean,rowsPage2,currentPage2,stuTableName,teacher);
	}
	public int deleteStudent(String value, String stuTableName) {
		return sDao.deleteStudent(value,stuTableName);
	}
	public int getCountRows(StudentBean stuBean, String stuTableName, String teacher) {
		
		return sDao.getCountRows(stuBean,stuTableName,teacher);
	}
	public StudentBean getStudengById(String stuTableName,String values) {
		
		return sDao.getStudentById(stuTableName,values);
	}
	public int UpdateStudent(StudentBean updateBean, String studentTableName) {
		
		return sDao.UpdateStudent(updateBean,studentTableName);
	}
	public boolean addBatchStudent(String[][] result, String studentTableName) {
		return sDao.addBatchStudent(result,studentTableName);
	}
	public List<String[]> getStudentAll(String studentTableName,String department) {
		return sDao.getStudentAll(studentTableName,department);
	}
	public int delBatchStudent(Vector<Vector> result, String studentTableName) {
		return sDao.delBatchStudent(result,studentTableName);
	}
	public UserKeyBean studentLogin(UserKeyBean bean) {
		return sDao.studentLogin(bean);
	}
	public int updatePassStudent(String username, String newPass) {
		
		return sDao.updatePassStudent(username,newPass);
	}
	public int getQuestion(String username) {
		return sDao.getQuesion(username);
	}
	public int updateStudentQuestion(String username,int question) {
		return sDao.updateStudentQuestion(username, question);
	}
	public int resettingStudent(String sid) {
		return sDao.resettingStudent(sid);
	}
	

	
}

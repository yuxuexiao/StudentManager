package com.action;

import java.util.List;
import java.util.Vector;

import com.bean.ImportCodeBean;
import com.dao.CodeDao;

public class CodeAction {
	CodeDao cDao=new CodeDao();
	public int getCountRows(ImportCodeBean bean) {
		// TODO Auto-generated method stub
		return cDao.getCountRows(bean);
	}
	public List<Vector> getCodeTable(ImportCodeBean bean, int currpage, int rowsPage) {
		// TODO Auto-generated method stub
		return cDao.getCodeTable(bean,currpage,rowsPage);
	}
	public boolean addBatchCode(String[][] result, ImportCodeBean codeBean) {
		return cDao.addBatchCode(result,codeBean);
	}
	public int addCodeOne(ImportCodeBean b) {
		return cDao.addCodeOne(b);
	}
	public List<String[]> getCodeAll(ImportCodeBean codeBean) {
		
		return cDao.getCodeAll(codeBean);
	}
	public int delBatchCode(Vector<Vector> result, ImportCodeBean codeBean) {
		return cDao.delBatchCode(result,codeBean);
	}
	public int updateCodeOne(ImportCodeBean iBean) {
		return cDao.updateCodeOne(iBean);
	}
	public int delCodeOne(ImportCodeBean iBean) {
		return cDao.delCodeOne(iBean);
	}

}

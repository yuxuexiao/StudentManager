package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.bean.ImportCodeBean;
import com.tools.JDBCTools;

public class CodeDao {

	public int getCountRows(ImportCodeBean bean) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		int num=0;
		try {
			String sql="select count(*) from "+bean.getTable()+" where 1=1";
			
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			if(rs.next())
			{
				num=rs.getInt(1);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return num;
	}

	public List<Vector> getCodeTable(ImportCodeBean bean, int currpage, int rowsPage) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		ImportCodeBean icBean=new ImportCodeBean();
		List<Vector> list=new ArrayList<Vector>();
		try {
			String sql="select * from "+bean.getTable()+" where 1=1 ";
			sql+="limit "+(currpage-1)*rowsPage+","+rowsPage+"";
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(false);
				v.add(rs.getInt("id"));
				v.add(rs.getString("code"));
				v.add(rs.getString(bean.getTable()));
				list.add(v);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return list;
	}

	public boolean addBatchCode(String[][] result, ImportCodeBean codeBean) {
		Connection conn=JDBCTools.getconn();
		Statement stat=null;
		boolean flag=false;
		try {
			conn.setAutoCommit(false);
			String sql="";
			
			stat=conn.createStatement();
			for(String str[]:result)
			{
				sql="insert into "+codeBean.getTable()+" values(null,'"+str[0]+"','"+str[1]+"')";
				stat.addBatch(sql);
			}
			stat.executeBatch();
			conn.commit();
			flag=true;
		}catch(Exception e)
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}

	public int addCodeOne(ImportCodeBean b) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			String sql="insert into "+b.getTable()+" values(null,'"+b.getCode()+"','"+b.getName()+"')";
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			num=stat.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

	public List<String[]> getCodeAll(ImportCodeBean codeBean) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try {
			String sql="select * from "+codeBean.getTable()+"";
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				String str[]=new String[2];
				str[0]=rs.getString("code");
				str[1]=rs.getString(codeBean.getTable());
				list.add(str);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return list;
	}

	public int delBatchCode(Vector<Vector> result, ImportCodeBean codeBean) {
		Connection conn=null;
		Statement stat=null;
		int in=0;
		try {
			
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			for(Vector rs:result)
			{
				if(rs.get(0).equals(true))
				{
					String sql="delete from "+codeBean.getTable()+" where id="+rs.get(1)+"";
					stat.addBatch(sql);
				}
			}
			int num[]=stat.executeBatch();
			for(int n:num)
			{
				in++;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return in;
	}

	public int updateCodeOne(ImportCodeBean iBean) {
		Connection conn=null;
		PreparedStatement pstat=null;
		int num=0;
		try {
			String sql="update "+iBean.getTable()+" set code=?,"+iBean.getTable()+"=? where id=?";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, iBean.getCode());
			pstat.setString(2, iBean.getName());
			pstat.setInt(3, iBean.getId());
			num=pstat.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, null);
		}
		return num;
	}

	public int delCodeOne(ImportCodeBean iBean) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			String sql="delete from "+iBean.getTable()+" where id="+iBean.getId()+"";
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			num=stat.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

}




















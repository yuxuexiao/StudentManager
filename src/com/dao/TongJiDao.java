package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


import com.tools.JDBCTools;

public class TongJiDao {

	public Vector<String> getAllDepart(String education) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<String> vector=new Vector<String>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select department from student2 where education='"+education+"' group by department";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				vector.add(rs.getString("department"));
			}
			return vector;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return null;
	}

	public Vector<String> getAllAddress() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<String> vector=new Vector<String>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select left(address,2) as adds from student2 group by left(address,2)";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				vector.add(rs.getString("adds"));
			}
			return vector;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return null;
	}

	public Vector<String> getAllProfessionByDeprtment(String department) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<String> vector=new Vector<String>(); 
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select profession from student2 where department='"+department+"' group by profession";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				vector.add(rs.getString("profession"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return vector;
	}

	public Vector<Vector> getCount(String education, Vector<String> profession, Vector<String> adds) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<Vector> vector=new Vector<Vector>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			for(String pro:profession)
			{
				Vector v=new Vector();
				v.add(pro);
				for(String ad:adds)
				{
					String sql="select count(1) as ct from student2 where profession='"+pro+"' and left(address,2)='"+ad+"' "
							+ "and education='"+education+"'";
					rs=stat.executeQuery(sql);
					if(rs.next())
					{
						v.add(rs.getInt("ct"));
					}
				}
				vector.add(v);
			}
			return vector;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return null;
	}

	public Vector getCounts(String education, String department, Vector<String> adds) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector vector=new Vector();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			
				
				vector.add("×Ü¼Æ");
				for(String ad:adds)
				{
					String sql="select count(*) as ct from student2 where department='"+department+"' and education='"+education+"' and "
							+ "left(address,2)='"+ad+"' ";
					rs=stat.executeQuery(sql);
					if(rs.next())
					{
						vector.add(rs.getInt("ct"));
					}
				}
				
			
			return vector;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return null;
	}

	
}

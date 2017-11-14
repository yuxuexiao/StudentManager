package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.bean.UserKeyBean;




import com.tools.JDBCTools;

public class UserDao {

	public UserKeyBean userkey(UserKeyBean bean)
	{
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		UserKeyBean uBean=new UserKeyBean();
		try{
			String sql="select id,username,password,rid from userkey where username=? and password=? and rid=(select id from role where id=?)";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, bean.getUsername());
			pstat.setString(2, bean.getPassword());
			pstat.setInt(3, bean.getRid());
			rs=pstat.executeQuery();
			if(rs.next())
			{
				
				uBean.setId(rs.getInt("id"));
				uBean.setUsername(rs.getString("username"));
				uBean.setPassword(rs.getString("password"));
				uBean.setRid(rs.getInt("rid"));
				return uBean;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		}

	public Vector<Vector> getRoot() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<Vector> vector=new Vector<Vector>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select * from userkey where rid in(3,4)";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("username"));
				v.add(rs.getString("password"));
				v.add("√‹¬Î÷ÿ÷√");
				v.add("…æ≥˝");
				vector.add(v);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return vector;
	}

	public Vector<Vector> getTeacher() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<Vector> vector=new Vector<Vector>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select * from userkey where rid=2";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("username"));
				v.add(rs.getString("password"));
				v.add("»®œﬁ–ﬁ∏ƒ");
				v.add("√‹¬Î÷ÿ÷√");
				v.add("…æ≥˝");
				vector.add(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return vector;
		}

	public int initPassRoot(int id) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="update userkey set password='000000' where id="+id+"";
			num=stat.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

	public int delRoot(int id) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="delete from userkey where id="+id+"";
			num=stat.executeUpdate(sql);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

	public UserKeyBean getAthority(int aid) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		UserKeyBean ukb=new UserKeyBean();
		ukb.setId(aid);
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select authority from userkey where id="+aid+"";
			rs=stat.executeQuery(sql);
			if(rs.next())
			{
				ukb.setAuthority(rs.getString("authority").toCharArray());
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return ukb;
	}

	public int updateAuthority(UserKeyBean ukbHave) {
		Connection conn=null;
		Statement stat=null;
		String authority=String.valueOf(ukbHave.getAuthority());
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="update userkey set authority='"+authority+"' where id="+ukbHave.getId()+"";
			num=stat.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
		}

	public UserKeyBean getControlAut(UserKeyBean bean) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		UserKeyBean ukb=new UserKeyBean();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select * from userkey where username='"+bean.getUsername()+"' and password='"+bean.getPassword()+"' ";
			rs=stat.executeQuery(sql);
			if(rs.next())
			{
				ukb.setId(rs.getInt("id"));
				ukb.setUsername(rs.getString("username"));
				ukb.setPassword(rs.getString("password"));
				ukb.setRid(rs.getInt("rid"));
				ukb.setAuthority(rs.getString("authority").toCharArray());
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return ukb;
	}

	public int addUser(UserKeyBean ukb) {
		Connection conn=null;
		PreparedStatement pstat=null;
		int num=0;
		String authority=String.valueOf(ukb.getAuthority());
		try {
			String sql="insert into userkey values(null,?,?,?,?)";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, ukb.getUsername());
			pstat.setString(2, ukb.getPassword());
			pstat.setInt(3, ukb.getRid());
			pstat.setString(4, authority);
			num=pstat.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, null);
		}
		return num;
	}

	public Vector<String> getNotExsistTeacher() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<String> notExsistTeacher=new Vector<String>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select department from department where department not in (select username from userkey) group by department";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				notExsistTeacher.add(rs.getString("department"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return notExsistTeacher;
	}

	public int updatePass(String username, String newPass) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="update userkey set password='"+newPass+"' where username='"+username+"'";
			num=stat.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

	public Vector<Vector> getStudentById(String id) {
		Connection conn=null;
		Statement stat=null;
		Vector<Vector> vector=new Vector<Vector>();
		ResultSet rs=null;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select * from student2 where sid='"+id+"'";
			rs=stat.executeQuery(sql);
			if(rs.next())
			{
				Vector v=new Vector();
				v.add(rs.getString("sid"));
				v.add(rs.getString("name"));
				v.add(rs.getString("profession"));
				v.add("÷ÿ÷√√‹¬Î");
				vector.add(v);
			}
//			for(Vector v:vector)
//			{
//				for(Object o:v)
//				{
//					System.out.println(o);
//				}
//			}
			return vector;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return null;
	}

	public int initStudentPass(String values) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="update student2 set password='000000' where sid='"+values+"'";
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


		

	
	
	
	
	
	


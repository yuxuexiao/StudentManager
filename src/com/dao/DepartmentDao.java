package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.bean.DepartmentBean;
import com.tools.JDBCTools;

public class DepartmentDao {

	public List<String> selAllDepartment() {
		Connection conn=null;
		Statement stat=null;
		
		ResultSet rs=null;
		
		
		List<String> list=new ArrayList<String>();
		try {
			String sql="select department from student2 group by department";
			
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			
			rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				list.add(rs.getString("department"));
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return list;
	}

	public Vector<Vector> selAllNormalProfession(List<String> listD) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		
		Vector<Vector> vector=new Vector<Vector>();
	
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			
				for(String str:listD)
				{
					String sql="select prfscode,department,profession,education from student2 where "
					+ "education in('专科','本科') "
					+ "and department='"+str+"' "
							+ "group by profession";
					rs=stat.executeQuery(sql);
					
					while(rs.next())
					{
						Vector v=new Vector();
						
						v.add(rs.getString("department")+"");
						v.add(rs.getString("profession")+"");
						v.add(rs.getString("prfscode")+"");
						v.add(rs.getString("education")+"");
						vector.add(v);
					}
					
				}
			}catch(Exception e)
		{
				e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return vector;
	}

	public Vector<Vector> selAllGraduationProfession(List<String> listD) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		
		Vector<Vector> vector=new Vector<Vector>();
	
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			
				for(String str:listD)
				{
					String sql="select prfscode,department,profession,education from student2 where "
					+ "education in('硕士','博士') "
					+ "and department='"+str+"' "
							+ "group by profession";
					rs=stat.executeQuery(sql);
					
					while(rs.next())
					{
						Vector v=new Vector();
						
						v.add(rs.getString("department")+"");
						v.add(rs.getString("profession")+"");
						v.add(rs.getString("prfscode")+"");
						v.add(rs.getString("education")+"");
						vector.add(v);
					}
					System.out.println();
					
				}
			}catch(Exception e)
		{
				e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return vector;
		
	}

	public int importProfession(Vector<Vector> vecNormalP, Vector<Vector> vecGraduationP) {
		Connection conn=JDBCTools.getconn();
		Statement stat=null;
		int in=0;
		try {
			conn.setAutoCommit(false);
			stat=conn.createStatement();
			String s="delete from department";
			stat.addBatch(s);
			for(Vector vec:vecNormalP)
			{
				String sql="insert into department values(null,'"+vec.get(0)+"','"+vec.get(1)+"','"+vec.get(2)+"','"+vec.get(3)+"')";
				stat.addBatch(sql);
			}
			for(Vector vec:vecGraduationP)
			{
				String sql="insert into department values(null,'"+vec.get(0)+"','"+vec.get(1)+"','"+vec.get(2)+"','"+vec.get(3)+"')";
				stat.addBatch(sql);
			}
			int i[]=stat.executeBatch();
			conn.commit();
			in=i.length;
		}catch(Exception e)
		{	try {
			conn.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
			e.printStackTrace();
			in=0;
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return in;
	}

	public Vector countRows() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector vector=new Vector();
		
		try {
			String sql="select department from department group by department";
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				vector.add(rs.getString("department"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return vector;
	}

	public Vector<Vector> getDepartmentTable(Vector count, int rowsPage, int currentPage) {
		Connection conn1=null;
		Statement stat1=null;
		ResultSet rs1=null;
		
		Connection conn2=null;
		Statement stat2=null;
		ResultSet rs2=null;
		
		Vector<Vector> vector=new Vector<Vector>();
		try {
			conn1=JDBCTools.getconn();
			stat1=conn1.createStatement();
			conn2=JDBCTools.getconn();
			stat2=conn2.createStatement();
			int i=0;
			String sql1="";
			String sql2="";
			int view1=(currentPage-1)*rowsPage;
			int view2=currentPage*rowsPage;
				for(Object str:count)
				{
					i++;
					if(view1<i&&view2>=i)
					{
					String normal="";
					
					Vector v=new Vector();
					
					sql1="select profession from department where education in('本科','专科') and department='"+str+"'";
					rs1=stat1.executeQuery(sql1);
					while(rs1.next())
					{
						normal+=rs1.getString("profession");
						
					}
					
					
					String graduation="";
					sql2="select profession from department where education in('硕士','博士') and department='"+str+"'";
					rs2=stat2.executeQuery(sql2);
					while(rs2.next())
					{
						
						graduation+=rs2.getString("profession");
					}
					
					v.add(i);
					v.add(str);
					v.add(normal);
					v.add("详细");
					v.add(graduation);
					v.add("详细");
					vector.add(v);
				}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}finally {
				JDBCTools.release(conn1, stat1, rs1);
				JDBCTools.release(conn2, stat2, rs2);
			}
			return vector;
			
		}

	public Vector<Vector> getDepartOne(String value, String[] ng) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<Vector> vector=new Vector<Vector>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select * from department where department='"+value+"' and education in('"+ng[0]+"','"+ng[1]+"')";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(false);
				v.add(rs.getInt("id"));
				System.out.println("xuh:"+rs.getInt("id"));
				v.add(rs.getString("prfscode"));
				
				v.add(rs.getString("profession"));
				
				v.add("修改");
				v.add("删除");
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

	public int updateDepartOne(int id, String code, String profession) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="update department set prfscode='"+code+"',profession='"+profession+"' where id="+id+"";
			num=stat.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

	public int delDepartOne(int id) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="delete from department where id="+id+"";
			num=stat.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

	public int addDepartmentOne(String name) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="insert into department values(null,'"+name+"',null,null,null)";
			num=stat.executeUpdate(sql);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

	public int addProfessionOne(DepartmentBean dBeanN) {
		Connection conn=null;
		PreparedStatement pstat=null;
		int num=0;
		try {
			String sql="insert into department values(null,?,?,?,?)";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, dBeanN.getDepartment());
			pstat.setString(2, dBeanN.getProfession());
			pstat.setString(3, dBeanN.getCode());
			pstat.setString(4, dBeanN.getEducation());
			num=pstat.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, null);
		}
		return num;
	}

	public int delBatchProfession(Vector<Vector> delBatch) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			for(Vector v:delBatch)
			{
				if(v.get(0).equals(true))
				{
					String sql="delete from department where id="+v.get(1)+"";
					stat.addBatch(sql);
				}
			}
			
			int n[]=stat.executeBatch();
			num=n.length;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}
		
	

}


























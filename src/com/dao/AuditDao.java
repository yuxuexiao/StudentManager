package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.bean.AuditBean;
import com.bean.StudentBean;
import com.bean.UserKeyBean;
import com.tools.JDBCTools;

public class AuditDao {

	public Vector<String> getAllDepart() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<String> allDepart=new Vector<String>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select department from department group by department";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				allDepart.add(rs.getString("department"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return allDepart;
	}

	public Vector<Vector> getStudentAuditCenter(AuditBean bean, int currentPage2, int rowsPage) {
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		Vector<Vector> result=new Vector<Vector>();
		try {
			String sql="select * from student2 where department=? and education=? and audit>2 ";
			sql+=" order by audit ";
			sql+="limit "+(currentPage2-1)*rowsPage+","+rowsPage+"";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			
			pstat.setString(1, bean.getDepartment());
			pstat.setString(2, bean.getEducation());
			rs=pstat.executeQuery();
			while(rs.next())
			{
				
					Vector v=new Vector();
					v.add(false);
					v.add(rs.getString("sid"));
					v.add(rs.getString("name"));
					v.add(rs.getString("department"));
					v.add(rs.getString("profession"));
					if(rs.getInt("audit")==3)
					{
						v.add("等待审核");
					}
					if(rs.getInt("audit")>3)
					{
						v.add("通过");
					}
					v.add("审核");
					result.add(v);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, rs);
		}
		return result;
	}

	public StudentBean getStudentByKey(UserKeyBean uBean) {
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		StudentBean sBean=new StudentBean();
		try {
			String sql="select * from student2 where sid=? and password=?";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, uBean.getUsername());
			pstat.setString(2, uBean.getPassword());
			rs=pstat.executeQuery();
			if(rs.next())
			{
				sBean.setCandid(rs.getString("candid"));
				sBean.setName(rs.getString("name"));
				sBean.setSex(rs.getString("sex"));
				sBean.setSid(rs.getString("sid"));
				sBean.setNation(rs.getString("nation"));
				sBean.setPoliticstatu(rs.getString("politicstatu"));
				sBean.setEducation(rs.getString("education"));
				sBean.setProfession(rs.getString("profession"));
				sBean.setTrainingmode(rs.getString("trainingmode"));
				sBean.setAddress(rs.getString("address"));
				sBean.setBirthday(rs.getString("birthday"));
				sBean.setIdcard(rs.getString("idcard"));
				sBean.setEnrollment(rs.getString("enrollment"));
				sBean.setEducationsystem(rs.getString("educationsystem"));
				sBean.setDirectional(rs.getString("directional"));
				sBean.setLanguage(rs.getString("language"));
				sBean.setStatuschanges(rs.getString("statuschanges"));
				sBean.setGraduation(rs.getString("graduation"));
				sBean.setContactway(rs.getString("contactway"));
				sBean.setEmail(rs.getString("email"));
				sBean.setAtschool(rs.getString("atschool"));
				sBean.setEnglish(rs.getString("english"));
				sBean.setRewardpunishment(rs.getString("rewardspunishment"));
				sBean.setDepartment(rs.getString("department"));
				sBean.setGrade(rs.getString("grade"));
				sBean.setAudit(rs.getInt("audit"));
				sBean.setReason(rs.getString("reason"));
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, rs);
		}
		return sBean;
	}

	public int updateStudentOwn(StudentBean selfBean) {
		Connection conn=null;
		PreparedStatement pstat=null;
		int num=0;
		try {
			String sql="update student2 set address=?,idcard=?,contactway=?,email=?,english=?,rewardspunishment=?,audit=? where "
					+ "sid=? and password=?";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, selfBean.getAddress());
			pstat.setString(2, selfBean.getIdcard());
			pstat.setString(3, selfBean.getContactway());
			pstat.setString(4, selfBean.getEmail());
			pstat.setString(5, selfBean.getEnglish());
			pstat.setString(6, selfBean.getRewardpunishment());
			pstat.setInt(7, selfBean.getAudit());
			pstat.setString(8, selfBean.getSid());
			pstat.setString(9, selfBean.getPassword());
			num=pstat.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, null);
		}
		return num;
	}

	public Vector<Vector> getStudentAuditTeacher(AuditBean bean, int currentPage2, int rowsPage) {
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		Vector<Vector> result=new Vector<Vector>();
		try {
			String sql="select * from student2 where department=? and education=? and audit>1 ";
			sql+=" order by audit ";
			sql+="limit "+(currentPage2-1)*rowsPage+","+rowsPage+"";
			
			
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, bean.getDepartment());
			pstat.setString(2, bean.getEducation());
			rs=pstat.executeQuery();
			while(rs.next()) {
//				if(rs.getInt("audit")>1)
//				{
					Vector v=new Vector();
					v.add(false);
					v.add(rs.getString("sid"));
					v.add(rs.getString("name"));
					v.add(rs.getString("department"));
					v.add(rs.getString("profession"));
					if(rs.getInt("audit")==2)
					{
						v.add("等待审核");
					}
					if(rs.getInt("audit")>2)
					{
						v.add("通过");
					}
					v.add("审核");
					result.add(v);
//				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, rs);
		}
		return result;
	}

	public int updateStudentTeacher(String values,String reason,int audit) {
		Connection conn=JDBCTools.getconn();
		Statement stat=null;
		int num=0;
		try {
			
			String sql="update student2 set reason='"+reason+"',audit="+audit+" where sid='"+values+"'";
			System.out.println(sql);
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

	public String[] tempStudent(String values) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		String str[]=new String[38];
		try {
			
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select * from student2 where sid="+values+"";
			
			rs=stat.executeQuery(sql);
			if(rs.next())
			{
				str[0]=rs.getString("candid");
				str[1]=rs.getString("name");
				str[2]=rs.getString("sex");
				str[3]=rs.getString("sid");
				str[4]=rs.getString("nation");
				str[5]=rs.getString("nacode");
				str[6]=rs.getString("politicstatu");
				str[7]=rs.getString("policode");
				str[8]=rs.getString("education");
				str[9]=rs.getString("educode");
				str[10]=rs.getString("profession");
				str[11]=rs.getString("prfscode");
				str[12]=rs.getString("trainingmode");
				str[13]=rs.getString("traincode");
				str[14]=rs.getString("address");
				str[15]=rs.getString("addcode");
				str[16]=rs.getString("birthday");
				str[17]=rs.getString("idcard");
				str[18]=rs.getString("enrollment");
				str[19]=rs.getString("educationsystem");
				str[20]=rs.getString("directional");
				str[21]=rs.getString("language");
				str[22]=rs.getString("lancode");
				str[23]=rs.getString("statuschanges");
				str[24]=rs.getString("statuchangecode");
				str[25]=rs.getString("graduation");
				str[26]=rs.getString("contactway");
				str[27]=rs.getString("email");
				str[28]=rs.getString("atschool");
				str[29]=rs.getString("english");
				str[30]=rs.getString("rewardspunishment");
				str[31]=rs.getString("department");
				str[32]=rs.getString("grade");
				str[33]=rs.getString("password");
				str[34]=String.valueOf(rs.getInt("audit"));
				str[35]=String.valueOf(rs.getInt("question"));
				str[36]=String.valueOf(rs.getString("id"));
				str[37]=rs.getString("reason");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return str;
	}

	public int getCountRows(int rid, String department, String education) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		int count=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="";
			if(rid==2)
			{
			sql="select count(*) from student2 where department='"+department+"' and education='"+education+"' and audit>1";
			rs=stat.executeQuery(sql);
			}
			if(rid==3)
			{
			sql="select count(*) from student2 where department='"+department+"' and education='"+education+"' and audit>2";
			rs=stat.executeQuery(sql);
			}
			
			
			if(rs.next())
			{
				count=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return count;
	}

	public Vector<String[]> tempBatchStudent(Vector<Vector> vector) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<String[]> result=new Vector<String[]>();
		try {
			for(Vector v: vector)
			{
				conn=JDBCTools.getconn();
				stat=conn.createStatement();
				if(v.get(0).equals(true)&&v.get(2).equals("不通过"))
				{
					String sql="select * from student2 where sid='"+v.get(1)+"'";
					rs=stat.executeQuery(sql);
					if(rs.next())
					{
						String str[]=new String[38];
						str[0]=rs.getString("candid");
						str[1]=rs.getString("name");
						str[2]=rs.getString("sex");
						str[3]=rs.getString("sid");
						str[4]=rs.getString("nation");
						str[5]=rs.getString("nacode");
						str[6]=rs.getString("politicstatu");
						str[7]=rs.getString("policode");
						str[8]=rs.getString("education");
						str[9]=rs.getString("educode");
						str[10]=rs.getString("profession");
						str[11]=rs.getString("prfscode");
						str[12]=rs.getString("trainingmode");
						str[13]=rs.getString("traincode");
						str[14]=rs.getString("address");
						str[15]=rs.getString("addcode");
						str[16]=rs.getString("birthday");
						str[17]=rs.getString("idcard");
						str[18]=rs.getString("enrollment");
						str[19]=rs.getString("educationsystem");
						str[20]=rs.getString("directional");
						str[21]=rs.getString("language");
						str[22]=rs.getString("lancode");
						str[23]=rs.getString("statuschanges");
						str[24]=rs.getString("statuchangecode");
						str[25]=rs.getString("graduation");
						str[26]=rs.getString("contactway");
						str[27]=rs.getString("email");
						str[28]=rs.getString("atschool");
						str[29]=rs.getString("english");
						str[30]=rs.getString("rewardspunishment");
						str[31]=rs.getString("department");
						str[32]=rs.getString("grade");
						str[33]=rs.getString("password");
						str[34]=String.valueOf(rs.getInt("audit"));
						str[35]=String.valueOf(rs.getInt("question"));
						str[36]=String.valueOf(rs.getInt("id"));
						str[37]=rs.getString("reason");
						result.add(str);
						
					}
				}
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		
		return result;
	}

	public int updateBatchTeacher(Vector<Vector> Batch, int audit) {
		Connection conn=JDBCTools.getconn();
		Statement stat=null;
		int num=0;
		try {
			conn.setAutoCommit(false);
			stat=conn.createStatement();
			for(Vector vec:Batch)
			{
				if(vec.get(0).equals(true)&&vec.get(2).equals("等待审核"))
				{
					String sql="update student2 set audit="+audit+",reason=null where sid='"+vec.get(1)+"'";
					stat.addBatch(sql);
				}
				
				
			}
			int n[]=stat.executeBatch();
			
			conn.commit();
			num=n.length;
		}catch(Exception e)
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			num=0;
			e.printStackTrace();
			
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}
	
}

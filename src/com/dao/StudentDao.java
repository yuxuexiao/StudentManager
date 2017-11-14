package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.bean.StudentBean;
import com.bean.UserKeyBean;
import com.tools.JDBCTools;

public class StudentDao {
//	public static void main(String[] args) {
//		StudentBean sBean=new StudentBean();
//		List<Vector> list=selAllStudent(sBean);
//		for(Vector v:list)
//		{
//			for(Object s:v)
//			{
//				System.out.println(s);
//			}
//		}
//	}



	public List<Vector> selAllStudent(StudentBean sBean, int rowsPage2, int currentPage2, String stuTableName, String teacher) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		List<Vector> list=new ArrayList<Vector>();
		try
		{
			String sql="select * from student2 where education='"+stuTableName+"' ";
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			if(sBean!=null)
			{
			if(sBean.getSid()!=null&&!"".equals(sBean.getSid()))
			{
				sql+="and sid='"+sBean.getSid()+"' ";
			}
			if(sBean.getName()!=null&&!"".equals(sBean.getName()))
			{
				sql+="and name like '%"+sBean.getName()+"%' ";
			}
			if(sBean.getSex()!=null&&!"".equals(sBean.getSex()))
			{
				sql+="and sex='"+sBean.getSex()+"' ";
			}
			if(sBean.getProfession()!=null&&!"".equals(sBean.getProfession()))
			{
				sql+="and profession='"+sBean.getProfession()+"' ";
			}
			}
			sql=sql+teacher;
			
			sql+="limit "+(currentPage2-1)*rowsPage2+","+rowsPage2+"";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				Vector vector=new Vector();
				vector.add(false);
				vector.add(rs.getString("sid"));
				vector.add(rs.getString("name"));
				vector.add(rs.getString("sex"));
				vector.add(rs.getString("profession"));
				
				list.add(vector);
				
			}
			return list;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			JDBCTools.release(conn, stat, rs);
		}
		return null;
	}

	public int deleteStudent(String value, String stuTableName) {
		 Connection conn=null;
		 PreparedStatement pstat=null;
		 try{
			 String sql="delete from student2 where sid=? and education='"+stuTableName+"'";
			 conn=JDBCTools.getconn();
			 pstat=conn.prepareStatement(sql);
			 pstat.setString(1, value);
			 int num=pstat.executeUpdate();
			 return num;
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }finally{
			 JDBCTools.release(conn, pstat, null);
		 }
		return 0;
	}

	public int getCountRows(StudentBean stuBean, String stuTableName, String teacher) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		
		int num=0;
		try
		{
			String sql="select count(*) from student2 where education='"+stuTableName+"' ";
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			if(stuBean!=null)
			{
			if(stuBean.getSid()!=null&&!"".equals(stuBean.getSid()))
			{
				sql+="and sid='"+stuBean.getSid()+"' ";
			}
			if(stuBean.getName()!=null&&!"".equals(stuBean.getName()))
			{
				sql+="and name like '%"+stuBean.getName()+"%' ";
			}
			if(stuBean.getSex()!=null&&!"".equals(stuBean.getSex()))
			{
				sql+="and sex='"+stuBean.getSex()+"' ";
			}
			if(stuBean.getProfession()!=null&&!"".equals(stuBean.getProfession()))
			{
				sql+="and profession='"+stuBean.getProfession()+"' ";
			}
			sql=sql+teacher;
			}
			rs=stat.executeQuery(sql);
			if(rs.next())
			{
				num=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			JDBCTools.release(conn, stat, rs);
		}
		return num;
	}

	public StudentBean getStudentById(String stuTableName,String values) {
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		StudentBean sBean=new StudentBean();
		try {
			String sql="select * from student2 where sid=? ";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, values);
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
				sBean.setReason(rs.getString("reason"));
				return sBean;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, rs);
		}
		return null;
	}

	public int UpdateStudent(StudentBean updateBean, String studentTableName) {
		Connection conn=null;
		PreparedStatement pstat=null;
		int num=0;
		try {
			String sql="update student2 set address=?,idcard=?,contactway=?,email=?,english=?,rewardspunishment=? where sid=? and education='"+studentTableName+"'";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, updateBean.getAddress());
			
			pstat.setString(2, updateBean.getIdcard());
			pstat.setString(3, updateBean.getContactway());
			pstat.setString(4, updateBean.getEmail());
			pstat.setString(5, updateBean.getEnglish());
			pstat.setString(6, updateBean.getRewardpunishment());
			pstat.setString(7, updateBean.getSid());
			num=pstat.executeUpdate();
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			JDBCTools.release(conn, pstat, null);
		}
		return num;
	}

	public boolean addBatchStudent(String[][] result, String studentTableName) {
		Connection conn=JDBCTools.getconn();;
		Statement stat=null;
		boolean flag=false;
		try {
			conn.setAutoCommit(false);
			
			stat=conn.createStatement();
			String sql="";
			for(String s[]:result)
			{
				
					sql="insert into student2 values('"+s[0]+"','"+s[1]+"','"+s[2]+"','"+s[3]+"','"+s[4]+"','"+s[5]+"','"+s[6]+"','"+s[7]+"','"+s[8]+"','"+s[9]+"','"+s[10]+"','"+s[11]+"','"+s[12]+"','"+s[13]+"','"+s[14]+"','"+s[15]+"','"+s[16]+"','"+s[17]+"','"+s[18]+"','"+s[19]+"','"+s[20]+"','"+s[21]+"','"+s[22]+"','"+s[23]+"','"+s[24]+"','"+s[25]+"','"+s[26]+"','"+s[27]+"','"+s[28]+"','"+s[29]+"','"+s[30].replaceAll("\"", "").replaceAll("\'", "")+"','"+s[31]+"','"+s[32]+"','"+s[33]+"',"+s[34]+","+s[35]+",null,null)";
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
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return flag;
	}

	public List<String[]> getStudentAll(String studentTableName, String department) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		List<String[]> list=new ArrayList<String[]>();
		try {
			String sql="select * from student2 where education='"+studentTableName+"' and audit>3 ";
			if(!"".equals(department))
			{
				sql+="and department='"+department+"'";
			}
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			while(rs.next())
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

	public int delBatchStudent(Vector<Vector> result, String studentTableName) {
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
					String sql="delete from student2 where sid='"+rs.get(1)+"' and education='"+studentTableName+"'";
					stat.addBatch(sql);
				}
			}
			int num[]=stat.executeBatch();
			for(int i:num)
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

	public UserKeyBean studentLogin(UserKeyBean bean) {
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		UserKeyBean uBean=new UserKeyBean();
		try {
			String sql="select * from student2 where sid=? and password=?";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, bean.getUsername());
			pstat.setString(2, bean.getPassword());
			rs=pstat.executeQuery();
			if(rs.next()) {
				uBean.setUsername(rs.getString("sid"));
				uBean.setPassword(rs.getString("password"));
				return uBean;
			}
		}catch(Exception e)
		{
			
		}
		return null;
	}

	public int updatePassStudent(String username, String newPass) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="update student2 set password='"+newPass+"' where sid='"+username+"'";
			num=stat.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

	public int getQuesion(String username) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select question from student2 where sid='"+username+"'";
			rs=stat.executeQuery(sql);
			if(rs.next())
			{
				num=rs.getInt("question");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return num;
	}

	public int updateStudentQuestion(String username, int question) {
		Connection conn=null;
		Statement stat=null;
		int rs=0;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="update student2 set question="+question+" where sid='"+username+"'";
			rs=stat.executeUpdate(sql);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return rs;
	}

	public int resettingStudent(String sid) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="update student2 set password='000000' where sid='"+sid+"'";
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

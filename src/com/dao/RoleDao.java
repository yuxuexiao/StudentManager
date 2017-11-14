package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.RoleBean;
import com.tools.JDBCTools;

public class RoleDao {
	
	
	public List<RoleBean> getNames() {
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		List<RoleBean> list=new ArrayList<RoleBean>();
		try{
			String sql="select * from role";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			rs=pstat.executeQuery();
			while(rs.next())
			{
				RoleBean bean=new RoleBean();
				bean.setName(rs.getString("name"));
				list.add(bean);
			}
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			JDBCTools.release(conn, pstat, rs);
		}
		return list;
	}
	public int getRid(String role)
	{
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		List<RoleBean> list=new ArrayList<RoleBean>();
		try{
			String sql="select * from role where name=?";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, role);
			rs=pstat.executeQuery();
			if(rs.next())
			{
				return rs.getInt("id");
			}
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			JDBCTools.release(conn, pstat, rs);
		}
		return 0;
	}

}

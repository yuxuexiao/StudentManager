package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.UserKeyBean;
import com.tools.JDBCTools;

public class R_SDao {

	public List<Integer> getSid(UserKeyBean uBean) {
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		List<Integer> list=new ArrayList<Integer>();
		try{
			String sql="select * from r_s where rid=?";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1,uBean.getRid());
			rs=pstat.executeQuery();
			while(rs.next())
			{
				list.add(rs.getInt("sid"));
			}
			return list;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			JDBCTools.release(conn, pstat, rs);
		}
		return null;
	}

}

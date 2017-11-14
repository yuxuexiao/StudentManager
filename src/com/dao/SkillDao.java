package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.SkillBean;
import com.tools.JDBCTools;

public class SkillDao {

	public List<SkillBean> skill(int pid)
	{
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		List<SkillBean> list=new ArrayList<SkillBean>();
		try
		{
			conn=JDBCTools.getconn();
			String sql="select * from skill where pid=?";
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, pid);
			rs=pstat.executeQuery();
			while(rs.next())
			{
				SkillBean sb=new SkillBean();
				sb.setId(rs.getInt("id"));
				sb.setName(rs.getString("name"));
				sb.setPid(rs.getInt("pid"));
				sb.setUrl(rs.getString("url"));
				list.add(sb);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public String getRoot()
	{
		
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		String rootName=null;
		try{
			String sql="select * from skill where pid=-1";
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			
			rs=stat.executeQuery(sql);
			
				if(rs.next())
				{
					rootName=rs.getString("name");
					
				}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return rootName;
		}
}

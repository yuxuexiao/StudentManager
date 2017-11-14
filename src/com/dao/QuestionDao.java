package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.bean.QuestionBean;
import com.tools.JDBCTools;

public class QuestionDao {

	public int addQuestionOne(String question) {
		Connection conn=null;
		PreparedStatement pstat=null;
		int num=0;
		try {
			String sql="insert into question values(null,?,?)";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, question);
			pstat.setInt(2, 0);
			num=pstat.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, null);
		}
		return num;
	}

	public Vector<QuestionBean> getAllQuestion() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<QuestionBean> vector=new Vector<QuestionBean>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select * from question";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				QuestionBean question=new QuestionBean();
				question.setId(rs.getInt("id"));
				question.setQuestion(rs.getString("question"));
				question.setOpen(rs.getInt("open"));
				vector.add(question);
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

	public int getIdByQuestion(String question) {
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		int qid=0;
		try {
			String sql="select id from question where question=? ";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, question);
			rs=pstat.executeQuery();
			if(rs.next())
			{
				qid=rs.getInt("id");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, rs);
		}
		
		return qid;
	}

	public int addAnswer(String answer) {
		Connection conn=null;
		PreparedStatement pstat=null;
		int num=0;
		try {
			String sql="insert into answer values(null,?,0)";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, answer);
			num=pstat.executeUpdate();
		}catch(Exception e )
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, null);
		}
		return num;
	}

	public int getIdByAnswer(String answer) {
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		int aid=0;
		try {
			String sql="select id from answer where answer=?";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, answer);
			rs=pstat.executeQuery();
			if(rs.next())
			{
				aid=rs.getInt("id");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, rs);
		}
		return aid;
	}

	public int relevance(int qid, int aid) {
		Connection conn=null;
		PreparedStatement pstat=null;
		int result=0;
		try {
			String sql="insert into a_q values(null,?,?)";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, aid);
			pstat.setInt(2, qid);
			result=pstat.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, null);
		}
		return result;
	}

	public Vector<Integer> getAidByQuestion(String question) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<Integer> aid=new Vector<Integer>();
		try {
			String sql="select aid from a_q where qid=(select id from question where question='"+question+"')";
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				aid.add(rs.getInt("aid"));
			}
			return aid;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return null;
	}

	public Vector<Vector> getAnswerByAid(Vector<Integer> aid) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<Vector> answer=new Vector<Vector>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			
			for(int id:aid)
			{
				
				String sql="select id,answer from answer where id="+id+"";
				rs=stat.executeQuery(sql);
				
				if(rs.next())
				{	
					
					Vector v=new Vector();
					v.add(rs.getInt("id"));
					v.add(rs.getString("answer"));	
					answer.add(v);
				}
			}
			return answer;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return null;
	}

	public int delQuestion(int value) {
		Connection conn=null;
		PreparedStatement pstat=null;
		int result=0;
		try {
			String sql="delete from question where id=?";
			conn=JDBCTools.getconn();
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, value);
			result=pstat.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, pstat, null);
		}
		return result;
	}

	public int updateQuestion(int value, String question) {
		Connection conn=null;
		Statement stat=null;
		int result=0;
		try {
			String sql="update question set question='"+question+"' where id="+value+"";
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			result=stat.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return result;
	}

	public int updateAnswer(int id, String answer) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="update answer set answer='"+answer+"' where id="+id+"";
			num=stat.executeUpdate(sql);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

	public int delAnswer(int id) {
		Connection conn = null;
		Statement stat = null;
		int num = 0;
		try {
			conn = JDBCTools.getconn();
			stat = conn.createStatement();
			String sql = "delete from answer where id=" + id + "";
			num = stat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

	public Vector<Vector> getIsNotAction(int isnot) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<Vector> vector=new Vector<Vector>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select * from question where open="+isnot+"";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("question"));
				vector.add(v);
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

	public int updateOpen(int open, int value) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="update question set open="+open+" where id="+value+"";
			num=stat.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
	}

	public Vector<QuestionBean> getAllOpenQuestion() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<QuestionBean> vector=new Vector<QuestionBean>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="select * from question where open=1";
			rs=stat.executeQuery(sql);
			while(rs.next())
			{
				QuestionBean question=new QuestionBean();
				question.setId(rs.getInt("id"));
				question.setQuestion(rs.getString("question"));
				question.setOpen(rs.getInt("open"));
				vector.add(question);
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

	public int updateAnswerTime(int value) {
		Connection conn=null;
		Statement stat=null;
		int num=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			String sql="update answer set time=time+1 where id="+value+"";
			num=stat.executeUpdate(sql);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, null);
		}
		return num;
		
	}

	public int getCountTimeByAid(Vector<Integer> aid) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		
		int countTime=0;
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			for(int id:aid)
			{
				String sql="select time from answer where id="+id+"";
				rs=stat.executeQuery(sql);
				if(rs.next())
				{
					countTime=countTime+rs.getInt("time");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTools.release(conn, stat, rs);
		}
		return countTime;
	}

	public Vector<Vector> getTimeByAid(Vector<Integer> aid, int countTimes) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		Vector<Vector> vector=new Vector<Vector>();
		try {
			conn=JDBCTools.getconn();
			stat=conn.createStatement();
			for(int id:aid)
			{
				String sql="select * from answer where id="+id+"";
				rs=stat.executeQuery(sql);
				if(rs.next())
				{
					Vector v=new Vector();
					v.add(rs.getInt("id"));
					v.add(rs.getString("answer"));
					v.add(rs.getInt("time"));
					Double zhanbi=new Double(rs.getInt("time")*1.0/countTimes*1.0);
					v.add(zhanbi);
					vector.add(v);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return vector;
	}

}

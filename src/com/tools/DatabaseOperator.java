package com.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class DatabaseOperator {

	/**
	 * 数据库备份
	 * 
	 * @param username
	 *            数据库用户名
	 * @param password
	 *            数据库密码
	 * @param database
	 *            数据库名称
	 * @param savepath
	 *            存储路径
	 * @param backupname
	 *            备份的文件名
	 * @return boolean 是否成功
	 */

	public static boolean backup(String username, String password, String database, 
			String savepath, String backupname) {

		boolean falg = false;

		try {
			Runtime rt = Runtime.getRuntime();
			Process child = rt.exec("cmd /c mysqldump  -u" + username + "  -p"
					+ password + "  --set-charset=utf8   " + database );
			
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。
			// 注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			InputStream in = child.getInputStream();// 控制台的输出信息作为输入流

			InputStreamReader xx = new InputStreamReader(in, "utf8");// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱码

			String inStr;

			StringBuffer sb = new StringBuffer("");

			String outStr;
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(xx);

			while ((inStr = br.readLine()) != null) {

				sb.append(inStr + "\r\n");

			}

			outStr = sb.toString();
			// 要用来做导入用的sql目标文件：
			FileOutputStream fout = new FileOutputStream(savepath + "/"
					+ backupname);
			
			//utf8国际化    gb2312中文 

			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");

			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
			writer.flush();
			// 别忘记关闭输入输出流
			in.close();

			xx.close();

			br.close();

			writer.close();

			fout.close();

			falg = true;

		} catch (Exception e) {

			e.printStackTrace();

			falg = false;

		}

		return falg;

	}
	
	/**
	 * 数据库恢复
	 * 
	 * @param username
	 *            数据库用户名
	 * @param password
	 *            数据库密码 
	 * @param database
	 *            数据库名称
	 * @param savepath
	 *            存储路径
	 * @param backupname
	 *            备份的文件名
	 * @return boolean 是否成功
	 */ 

	
	public static boolean recover(String username, String password,String database, 
			String savepath, String backupname) {

		boolean falg = false;

		try {

			String fPath = savepath + "/" + backupname;

			Runtime rt = Runtime.getRuntime();
			
			Process child = rt.exec("cmd /c mysql -u" + username + "  -p" + password
					+ "   " + database);

			OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流

			String inStr;

			StringBuffer sb = new StringBuffer("");

			String outStr;

			BufferedReader br = new BufferedReader(new InputStreamReader(

			new FileInputStream(fPath), "utf8"));

			while ((inStr = br.readLine()) != null) {

				sb.append(inStr + "\r\n");

			}

			outStr = sb.toString();

			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");

			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
			writer.flush();
			// 别忘记关闭输入输出流
			out.close();

			br.close();

			writer.close();

			falg = true;

		} catch (Exception e) {

			e.printStackTrace();

			falg = false;

		}

		return falg;

	}


	
}

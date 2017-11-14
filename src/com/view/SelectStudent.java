package com.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.action.StudentAction;
import com.bean.StudentBean;
import com.bean.UserKeyBean;
import com.tools.CheckHeaderCellRenderer;
import com.tools.CheckTableModle;
import com.tools.ExcelExportCollege;
import com.tools.ExcelOperate;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SelectStudent extends JPanel {
//	private JTable table;

	
	List<Vector> list=new ArrayList<Vector>();
	StudentAction sAction=new StudentAction();
	
	JScrollPane scrollPane = new JScrollPane();
	private JTextField textSearch;
	
	static UserKeyBean autBean=new UserKeyBean();//登陆信息
	int countPage=0;//总页数	
	int countRows=0;//总行数
	int rowsPage=20;//每页行数
	int currentPage=1;//当前页数
	StudentBean sBean=new StudentBean();
	static String studentTableName="";
	int raw=0;//鼠标点击表格行
	int column=0;//鼠标点击表格列
	private JTable table;
	JLabel lblNow = new JLabel();
	private JTextField textDrumpPage;
	/**
	 * Create the panel.
	 */
	/**
	 * 查询所有学生
	 */
	public SelectStudent() {
		setLayout(null);
		setVisible(true);
		
		
		
		scrollPane.setBounds(63, 117, 604, 246);
		
		
		
		add(scrollPane);
		
		JButton btnLast = new JButton("\u5C3E\u9875");
		btnLast.addActionListener(new ActionListener() {//尾页
			public void actionPerformed(ActionEvent e) {
				currentPage=countPage;
				initTable(sBean,currentPage,studentTableName);
			}
		});
		btnLast.setBounds(342, 372, 82, 23);
		add(btnLast);
		
		JButton btnStart = new JButton("\u9996\u9875");
		btnStart.addActionListener(new ActionListener() {//首页
			public void actionPerformed(ActionEvent e) {
				currentPage=1;
				initTable(sBean,currentPage,studentTableName);
			}
		});
		btnStart.setBounds(63, 372, 82, 23);
		add(btnStart);
		
		
		
		
		
		JLabel lblSearch = new JLabel("\u7B5B\u9009");
		lblSearch.setBounds(63, 62, 29, 15);
		add(lblSearch);
		
		textSearch = new JTextField();
		textSearch.setBounds(117, 59, 179, 21);
		add(textSearch);
		textSearch.setColumns(10);
		
		final JComboBox cmbSearch = new JComboBox();
		cmbSearch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {//下拉框监听，下拉框值改变则清空sBean
				 sBean=new StudentBean();
				 initTable(sBean,1,studentTableName);
			}
		});
		cmbSearch.addItem("学号");
		cmbSearch.addItem("专业");
		cmbSearch.addItem("姓名");
		cmbSearch.addItem("性别");
		
		cmbSearch.setBounds(342, 62, 82, 21);
		add(cmbSearch);
		
		
		
		JButton btnSelect = new JButton("\u7B5B\u9009");
		btnSelect.addActionListener(new ActionListener() {//条件查询学生
			public void actionPerformed(ActionEvent arg0) {
				String search=textSearch.getText();
				
				if("学号".equals(cmbSearch.getSelectedItem()))
				{
					sBean.setSid(search);
				}
				if("专业".equals(cmbSearch.getSelectedItem()))
				{
					sBean.setProfession(search);
				}
				if("姓名".equals(cmbSearch.getSelectedItem()))
				{
					sBean.setName(search);
				}
				if("性别".equals(cmbSearch.getSelectedItem()))
				{
					sBean.setSex(search);
				}
//				try {
//					Class cla=Class.forName("com.view.StudentMainFrame.StudentMainFrame().tree");//反射实例化树  获得树叶名;
//					JTree tree=(JTree)cla.newInstance();
//					String get=tree.getLastSelectedPathComponent()+"";
//				if("本科生信息管理".equals(get))//获取对应学历学生表名
//				{
//					studentTableName="student2";
//				}
//				if("硕士生信息管理".equals(get))
//				{
//					studentTableName="student3";
//				}
//				if("博士生信息管理".equals(get))
//				{
//					studentTableName="student4";
//				}
//				if("专科生信息管理".equals(get))
//				{
//					studentTableName="student1";
//				}
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				
				
				initTable(sBean,1,studentTableName);
				
				
			}
		});
		btnSelect.setBounds(448, 61, 66, 23);
		add(btnSelect);
		
		
		
		JButton btnNext = new JButton("\u4E0B\u4E00\u9875");//下一页
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=countPage)
				{
					initTable(sBean,++currentPage,studentTableName);
				}else {
					initTable(sBean,currentPage,studentTableName);
				}
			}
		});
		btnNext.setBounds(247, 373, 82, 23);
		add(btnNext);
		
		JButton btnUp = new JButton("\u4E0A\u4E00\u9875");//上一页
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=1)
				{
					initTable(sBean,--currentPage,studentTableName);
				}else {
					initTable(sBean,currentPage,studentTableName);
				}
			}
		});
		btnUp.setBounds(155, 373, 82, 23);
		add(btnUp);
		
		JButton buttonOut = new JButton("\u6279\u91CF\u5BFC\u51FA");
		buttonOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//批量导出
				JFileChooser jfc=new JFileChooser();
				int num=jfc.showSaveDialog(null);
				
				if(num==0)
				{
					File file=jfc.getSelectedFile();
					List<String> header=new ArrayList<String>();
					header.add("考生号");
					header.add("姓名");
					header.add("性别");
					header.add("学号");
					header.add("民族");
					header.add("代码");
					header.add("政治面貌");
					header.add("代码");
					header.add("学历");
					header.add("代码");
					header.add("专业");
					header.add("代码");
					header.add("培养方式");
					header.add("代码");
					header.add("地址");
					header.add("代码");
					header.add("生日");
					header.add("身份证号");
					header.add("入学日期");
					header.add("学制");
					header.add("定向或委培");
					header.add("主修外语");
					header.add("代码");
					header.add("学籍变动");
					header.add("代码");
					header.add("毕业时间");
					header.add("联系方式");
					header.add("邮箱");
					header.add("户口是否在校");
					header.add("英语等级");
					header.add("学校奖惩记录");
					header.add("院系");
					header.add("年级");
					header.add("密码");
					header.add("审核");
					header.add("不知道写啥名");
					header.add("ID");
					header.add("审核未通过原因");
					
					String department="";
					if(StudentMainFrame.uBean.getRid()==2)
					{
						department=StudentMainFrame.uBean.getUsername();//如果为管理员 则只能导出本院学生
					
					}
					List<String[]> colleges=sAction.getStudentAll(studentTableName,department);//
					
					boolean result;
					try {
						result = ExcelExportCollege.exportExcel(header, colleges, file);
						if(result)
						{
							JOptionPane.showMessageDialog(null, "导出成功");
						}else {
							JOptionPane.showMessageDialog(null, "导出失败");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		buttonOut.setBounds(535, 58, 93, 23);
		if(autBean.getAuthority()[4]=='e')//导出权限控制
		{
			add(buttonOut);
		}
		JButton buttonIn = new JButton("\u6279\u91CF\u5BFC\u5165");
		buttonIn.addActionListener(new ActionListener() {//批量导入学生信息
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();
				int num=jfc.showOpenDialog(null);
				if(num==0)
				{
					File file=jfc.getSelectedFile();
					try {
						String result[][]=ExcelOperate.getData(file, 1);
						
						boolean flag=false;
								flag=sAction.addBatchStudent(result,studentTableName);
						if(flag)
						{
							JOptionPane.showMessageDialog(null, "导入成功");
						}else {
							JOptionPane.showMessageDialog(null, "导入失败");
						}
					}catch(Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		buttonIn.setBounds(535, 84, 93, 23);
		if(autBean.getAuthority()[3]=='d')//导入权限控制
		{
			add(buttonIn);
		}
		
		JButton buttonDel = new JButton("\u6279\u91CF\u5220\u9664");
		buttonDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Vector> result=new Vector<Vector>();
				for(int i=0;i<rowsPage;i++)
				{
					Vector rs=new Vector();
					rs.add(table.getValueAt(i, 0));
					rs.add(table.getValueAt(i, 1));
					result.add(rs);
				}
				int num=sAction.delBatchStudent(result,studentTableName);
				if(num>0)
				{
					JOptionPane.showMessageDialog(null, num+"条信息已删除");
					countRows=countRows-num;
					initTable(sBean,currentPage,studentTableName);
				}else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
				
			}
		});
		buttonDel.setBounds(136, 84, 93, 23);
		add(buttonDel);
		
		
		lblNow.setBounds(434, 373, 90, 23);
		add(lblNow);
		
		textDrumpPage = new JTextField();
		textDrumpPage.setBounds(530, 373, 45, 23);
		add(textDrumpPage);
		textDrumpPage.setColumns(10);
		
		JButton btnDrump = new JButton("\u8DF3\u8F6C");
		btnDrump.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.valueOf(textDrumpPage.getText())>=1&&Integer.valueOf(textDrumpPage.getText())<=countPage)
				{
					currentPage=Integer.valueOf(textDrumpPage.getText());
					initTable(sBean,currentPage,studentTableName);
				}
			}
		});
		btnDrump.setBounds(585, 372, 82, 23);
		add(btnDrump);
		
		
		
		
//		StudentBean sBean =new StudentBean();
		//initTable(null,1,studentTableName);
		
		this.repaint();
		
	}
	//初始化 树
	private void initTable(final StudentBean stuBean,int currentPage2,String stuTableName)
	{
		String teacher="";
		if(autBean.getRid()==2)//如果为辅导员  则仅获得本院系学生;
		{
			teacher="and department='"+autBean.getUsername()+"' ";
			
		}
		countRows=sAction.getCountRows(stuBean,stuTableName,teacher);//获取总总条数

		
		countPage=countRows%rowsPage>0?countRows/rowsPage+1:countRows/rowsPage;
		
		
		lblNow.setText(currentPage+"/"+countPage);
		Vector<Vector> data=new Vector<Vector>();
		list=sAction.selAllStudent(stuBean,rowsPage,currentPage2,stuTableName,teacher);
		for(Vector v:list)
		{
			if(autBean.getAuthority()[1]=='b')//修改学生信息控制
			{
			v.add("修改");
			}
			
			v.add("删除");
			
			data.add(v);
		}
		
		Vector header=new Vector();
		header.add("全选/反选");
		header.add("学号");
		header.add("姓名");
		
		header.add("性别");
		header.add("专业");
		if(autBean.getAuthority()[2]=='c')//删除学生信息控制
		{
		header.add("");
		}
		
			
		
		if(autBean.getAuthority()[1]=='b')//修改学生信息权限控制
		{
		header.add("");
		}
		
		CheckTableModle tableModel=new CheckTableModle(data, header);
	
		if(autBean.getAuthority()[0]=='a')//查看生源信息权限控制
		{
			table = new JTable(tableModel){  
	            @Override  
	            public boolean isCellEditable(int row,int column){  
	            	if(column==1) {
	                return false;  
	            	}else {
	            		return true;
	            	}
	            }  
	        };
			
			table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					column=table.getSelectedColumn();
					raw=table.getSelectedRow();
					int columnx=5;
					int columny=6;
					if(autBean.getAuthority()[1]=='o')
					{
						columnx=10;
						columny=5;
					}
					
					String value=(String)table.getValueAt(raw, 1);//获取选择行的学号
					if(column==columnx)
					{
						JOptionPane.showMessageDialog(null, "修改学生信息");
						UpdateStudentDialog updateStudent=new UpdateStudentDialog(stuTableName,value);
						
						updateStudent.setVisible(true);
						initTable(stuBean,currentPage,stuTableName);
					}
					if(column==columny)
					{
						int result=JOptionPane.showConfirmDialog(null,"是否删除信息?","删除学生信息", JOptionPane.YES_NO_CANCEL_OPTION);
						if(result==0)
						{
							int num=sAction.deleteStudent(value,stuTableName);
							if(num>0)
							{
								JOptionPane.showMessageDialog(null, "删除成功");	
								countRows--;
							initTable(stuBean,currentPage,stuTableName);
							
							}else{
								JOptionPane.showMessageDialog(null, "删除失败");
							}
						}
					}
				}
			});
		}
		
		
		
		
		
		
		
		
		
		scrollPane.setViewportView(table);
	}
}

package com.view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.action.AuditAction;
import com.bean.AuditBean;
import com.tools.CheckHeaderCellRenderer;
import com.tools.CheckTableModle;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuditTeacherPanel extends JPanel {
	
	private JTextField textCountPage;
	private JTextField textDrumpPage;
	private JTable table;
	JScrollPane scrollPane = new JScrollPane();
	JComboBox comboEducation = new JComboBox();
	AuditAction aAction=new AuditAction();
	AuditBean aBean=new AuditBean();
	int countPage=0;//总页数	
	int countRows=0;//总行数
	int rowsPage=20;//每页行数
	int currentPage=1;//当前页数
	/**
	 * Create the panel.
	 */
	public AuditTeacherPanel() {
		this.setLayout(null);
		
		
		scrollPane.setBounds(47, 73, 637, 346);
		add(scrollPane);
		
		
		
		comboEducation.setBounds(333, 42, 100, 21);
		comboEducation.addItem("本科");
		comboEducation.addItem("专科");
		comboEducation.addItem("硕士");
		comboEducation.addItem("博士");
		
		add(comboEducation);
		Vector<String> allDepart=aAction.getAllDepart();
		
		comboEducation.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				aBean.setDepartment(StudentMainFrame.uBean.getUsername()+"");
				aBean.setEducation(comboEducation.getSelectedItem()+"");
				initTable(aBean,currentPage);
			}
		});
		
		JButton btnBatchAudit = new JButton("\u6279\u91CF\u5BA1\u6838");
		btnBatchAudit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Vector> vector=new Vector<Vector>();
				int rp=0;
				if(currentPage==countPage)
				{
					rp=countRows-rowsPage*(countPage-1);//防止最后一页少于每页行数而无法取值的情况
				}
				else {
					rp=rowsPage;
				}
				for(int i=0;i<rp;i++)//获取选择遍历
				{
					Vector v=new Vector();
					v.add(table.getValueAt(i, 0));
					v.add(table.getValueAt(i, 1));
					v.add(table.getValueAt(i, 5));
					vector.add(v);
					
				}
				
				
//				Vector<String[]> tempBatch=aAction.tempBatchStudent(vector);//通过学号获取学生信息存入数组
				
//				for(String str[]:tempBatch)
//				{
//					str[34]=String.valueOf(3);
//					str[37]="";
//				}
				int num=aAction.updateBatchTeacher(vector,3);//批量通过
				if(num>0)
				{
					JOptionPane.showMessageDialog(null, num+"个学生审核完毕");
					initTable(aBean,currentPage);
				}else {
					JOptionPane.showMessageDialog(null, "审核失败");
				}
				
			}
		});
		btnBatchAudit.setBounds(443, 40, 93, 23);
		add(btnBatchAudit);
		
		JButton btnStart = new JButton("\u9996\u9875");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=1)
				{
					currentPage=1;
					initTable(aBean,currentPage);
				}
			}
		});
		btnStart.setBounds(47, 429, 82, 25);
		add(btnStart);
		
		JButton btnUp = new JButton("\u4E0A\u4E00\u9875");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=1)
				{
					currentPage--;
					initTable(aBean,currentPage);
				}
			}
		});
		btnUp.setBounds(139, 429, 82, 25);
		add(btnUp);
		
		JButton btnDown = new JButton("\u4E0B\u4E00\u9875");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=countPage)
				{
					currentPage++;
					initTable(aBean,currentPage);
				}
			}
		});
		btnDown.setBounds(231, 429, 87, 25);
		add(btnDown);
		
		JButton btnLast = new JButton("\u5C3E\u9875");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=countPage)
				{
					currentPage=countPage;
					initTable(aBean,currentPage);
				}
			}
		});
		btnLast.setBounds(328, 429, 82, 25);
		add(btnLast);
		
		JLabel lblNewLabel_1 = new JLabel("\u5171");
		lblNewLabel_1.setBounds(420, 434, 16, 20);
		add(lblNewLabel_1);
		
		textCountPage = new JTextField();
		
		textCountPage.setEditable(false);
		textCountPage.setBounds(446, 430, 46, 24);
		add(textCountPage);
		textCountPage.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u9875");
		lblNewLabel_2.setBounds(502, 433, 24, 21);
		add(lblNewLabel_2);
		
		textDrumpPage = new JTextField();
		textDrumpPage.setColumns(10);
		textDrumpPage.setBounds(616, 430, 46, 24);
		add(textDrumpPage);
		
		JButton btnDrump = new JButton("\u8DF3\u8F6C");
		btnDrump.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(textDrumpPage.getText())>=1&&Integer.parseInt(textDrumpPage.getText())<=countPage)
				{
				currentPage=Integer.parseInt(textDrumpPage.getText());
				initTable(aBean,currentPage);
				}
			}
		});
		btnDrump.setBounds(526, 429, 82, 25);
		add(btnDrump);
		
		
		
		JLabel label = new JLabel("\u9875");
		label.setBounds(670, 434, 24, 20);
		add(label);
		
		JLabel labEducation = new JLabel("\u5B66\u5386");
		labEducation.setBounds(266, 42, 46, 21);
		add(labEducation);
		
		JLabel lblDepartment = new JLabel(StudentMainFrame.uBean.getUsername()+" 辅导员");
		lblDepartment.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		lblDepartment.setBounds(47, 10, 174, 53);
		add(lblDepartment);
		
		aBean.setDepartment(StudentMainFrame.uBean.getUsername()+"");
		aBean.setEducation(comboEducation.getSelectedItem()+"");
		initTable(aBean,currentPage);
		
	}
	private void initTable(AuditBean bean,int currentPage2)
	{
		countRows=aAction.getCountRows(StudentMainFrame.uBean.getRid(),StudentMainFrame.uBean.getUsername(),comboEducation.getSelectedItem()+"");//获取总总条数
		
		
		countPage=countRows%rowsPage>0?countRows/rowsPage+1:countRows/rowsPage;
		Vector<Vector> data=new Vector<Vector>();
		data=aAction.getStudentAuditTeacher(bean,currentPage2,rowsPage);
		Vector<String> header=new Vector<String>();
		header.add("全选/反选");
		header.add("学号");
		header.add("姓名");
		header.add("学院");
		header.add("专业");
		header.add("审核状态");
		header.add("");
		textCountPage.setText(String.valueOf(currentPage2)+"/"+String.valueOf(countPage));
		CheckTableModle tableModel=new CheckTableModle(data,header);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=table.getSelectedRow();
				int column=table.getSelectedColumn();
				
				if(column==6&&table.getValueAt(row, 5).equals("等待审核"))
				{
					AuditTeacherCenterDialog atcd=new AuditTeacherCenterDialog(StudentMainFrame.uBean.getRid(),table.getValueAt(row, 3)+"",table.getValueAt(row, 1)+"");
					initTable(bean,currentPage);
				}
			}
		});
		table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
		scrollPane.setViewportView(table);
	}
}

package com.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.action.DepartmentAction;
import com.tools.CheckHeaderCellRenderer;
import com.tools.CheckTableModle;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DepartmentMaintain extends JPanel {
	private JTable table;
	private JTextField textPage;
	JScrollPane scrollPane = new JScrollPane();
	DepartmentAction dAction=new DepartmentAction();
	List<String> listD=new ArrayList<String>();
	Vector<Vector> vecNormalP=new Vector<Vector>();
	Vector<Vector> vecGraduationP=new Vector<Vector>();
	
	Vector<Vector> vector=new Vector<Vector>();
	int countRows=0;
	int countPage=0;
	int currentPage=1;
	int rowsPage=5;
	
	/**
	 * Create the panel.
	 */
	public DepartmentMaintain() {
		this.setLayout(null);
		
		
		scrollPane.setBounds(29, 71, 552, 315);
		add(scrollPane);
		
		
		
		
		
		JButton btnAddDepartment = new JButton("\u6DFB\u52A0\u9662\u7CFB");
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDepartOne ado=new AddDepartOne();
				
			}
		});
		btnAddDepartment.setBounds(356, 38, 93, 23);
		add(btnAddDepartment);
		
		JButton buttonStart = new JButton("\u9996\u9875");
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage=1;
				initTable();
			}
		});
		buttonStart.setBounds(29, 396, 83, 23);
		add(buttonStart);
		
		JButton btnUp = new JButton("\u4E0A\u4E00\u9875");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=1)
				{
					currentPage--;
					initTable();
				}
			}
		});
		btnUp.setBounds(122, 396, 83, 23);
		add(btnUp);
		
		JButton btnNext = new JButton("\u4E0B\u4E00\u9875");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=countPage)
				{
					currentPage++;
					initTable();
				}
			}
		});
		btnNext.setBounds(215, 396, 83, 23);
		add(btnNext);
		
		JButton btnLast = new JButton("\u5C3E\u9875");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage=countPage;
				initTable();
			}
		});
		btnLast.setBounds(308, 396, 83, 23);
		add(btnLast);
		
		JLabel lblPage = new JLabel("\u9875");
		lblPage.setBounds(466, 398, 22, 19);
		add(lblPage);
		
		JButton btnDrump = new JButton("\u8DF3\u8F6C");
		btnDrump.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(textPage.getText())<=countPage&&Integer.parseInt(textPage.getText())>=1)
				{
				currentPage=Integer.parseInt(textPage.getText());
				initTable();
				}
			}
		});
		btnDrump.setBounds(498, 396, 83, 23);
		add(btnDrump);
		
		textPage = new JTextField();
		textPage.setBounds(401, 397, 55, 21);
		add(textPage);
		textPage.setColumns(10);
		
		JButton buttonImport = new JButton("\u4ECE\u5B66\u751F\u8868\u5BFC\u5165");
		buttonImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listD=dAction.selAllDepartment();//获得所有院系
				vecNormalP=dAction.selAllNormalProfession(listD);//通过院系获得对应的本专科专业
				vecGraduationP=dAction.selAllGraduationProfession(listD);
				
				int n=dAction.importProfession(vecNormalP,vecGraduationP);
				if(n>0)
				{
					JOptionPane.showMessageDialog(null, "导入成功");
				}else {
					JOptionPane.showMessageDialog(null, "导入失败");
				}
				
				
				
				
				
				
				
				
				
				initTable();
			}
		});
		buttonImport.setBounds(128, 38, 130, 23);
		add(buttonImport);
		initTable();
		this.setVisible(true);
	}
	private void initTable()
	{
		Vector count=dAction.countRows();//获得所有学院
		countRows=count.size();
		countPage=countRows%rowsPage>0?countRows/rowsPage+1:countRows/rowsPage;
		Vector<Vector> result=dAction.getDepartmentTable(count,rowsPage,currentPage);
		Vector header=new Vector();
		header.add("序号");
		header.add("学院");
		header.add("本专科专业");
		header.add("详细");
		header.add("研究生专业");
		header.add("详细");
		DefaultTableModel tableModel=new DefaultTableModel(result,header);
		
		
		
//		for(Vector v: data)
//		{
//			tableModel.addRow(v);
//		}
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int column=table.getSelectedColumn();
				int row=table.getSelectedRow();
				String value=table.getValueAt(row,1)+"";
				String N[]= {"专科","本科"};
				String G[]= {"硕士","博士"};
				if(column==3)
				{
					JOptionPane.showMessageDialog(null, "详细");
					GetDepartOne gdo=new GetDepartOne(value,N);
					initTable();
					
				}
				if(column==5)
				{
					JOptionPane.showMessageDialog(null, "详细");
					GetDepartOne gdo=new GetDepartOne(value,G);
				}
			}
		});
		table.setRowHeight(80);
		
		table.setDefaultRenderer(Object.class, new TableViewRenderer());
		
		scrollPane.setViewportView(table);
	}
	
	
}

class TableViewRenderer extends JTextArea implements TableCellRenderer 
{ 
       public TableViewRenderer() 
       { 
            //将表格设为自动换行
       setLineWrap(true); //利用JTextArea的自动换行方法
       }
       public Component getTableCellRendererComponent(JTable jtable, Object obj, //obj指的是单元格内容
            boolean isSelected, boolean hasFocus, int row, int column) 
       { 
       setText(obj == null ? "" : obj.toString()); //利用JTextArea的setText设置文本方法
       return this; 
       } 
    } 

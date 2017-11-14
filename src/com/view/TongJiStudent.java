package com.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.action.DepartmentAction;
import com.action.TongJiAction;
import com.tools.ExcelExportCollege;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TongJiStudent extends JPanel {
	private JTable table;
	TongJiAction tAction=new TongJiAction();
	JScrollPane scrollPane = new JScrollPane();
	
	Vector<Vector> data=new Vector();
	
	/**
	 * Create the panel.
	 */
	public TongJiStudent() {
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u751F\u6E90\u5730\u7EDF\u8BA1");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblNewLabel.setBounds(199, 23, 291, 72);
		add(lblNewLabel);
		
		
		scrollPane.setBounds(27, 179, 524, 242);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		JLabel lblEducation = new JLabel("\u5B66\u5386");
		lblEducation.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblEducation.setBounds(59, 126, 46, 24);
		add(lblEducation);
		
		
		JComboBox comboEducation = new JComboBox();
		JComboBox comboDepartment = new JComboBox();
		
		
		comboEducation.addItem("本科");
		comboEducation.addItem("专科");
		comboEducation.addItem("硕士");
		comboEducation.addItem("博士");
		comboEducation.setBounds(115, 127, 90, 24);
		add(comboEducation);
		
		JLabel labDepartment = new JLabel("\u5B66\u9662");
		labDepartment.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		labDepartment.setBounds(259, 126, 46, 24);
		add(labDepartment);
		
		
		
		comboDepartment.setBounds(315, 127, 90, 24);
		add(comboDepartment);
		
		
		
	
		Vector<String> depart=tAction.getAllDepart(comboEducation.getSelectedItem()+"");
		for(String str:depart)
		{
			comboDepartment.addItem(str);
		}
		
		String education=comboEducation.getSelectedItem()+"";
		String department=comboDepartment.getSelectedItem()+"";
		
		comboEducation.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String edu=comboEducation.getSelectedItem()+"";
				String dep=comboDepartment.getSelectedItem()+"";
				initTable(edu,dep);
			}
		});
		comboDepartment.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String edu=comboEducation.getSelectedItem()+"";
				String dep=comboDepartment.getSelectedItem()+"";
				initTable(edu,dep);
			}
		});
		
		JButton btnOut = new JButton("\u5BFC\u51FA");
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String education=comboEducation.getSelectedItem()+"";
				String department=comboDepartment.getSelectedItem()+"";
				initTable(education,department);
				JFileChooser jfc=new JFileChooser();
				int num=jfc.showSaveDialog(null);
				if(num==0)
				{
					File file=jfc.getSelectedFile();
					
					
					Vector<Vector> data=new Vector();
					Vector<String> header=new Vector<String>();//获取地址||表头
					Vector<String> adds=tAction.getAllAddress();//地址前两个字
					
					header.add("");
					for(String str:adds)//获取地址||表头  优化
					{
						if(str.equals("黑龙"))
						{
							str=str+"江";
						}if(str.equals("未知")) {
							header.add(str);
							continue;
						}
						header.add(str);
					 }
					Vector<String> profession=tAction.getAllProfessionByDepartmen(department);//获取专业
					
						data=tAction.getCount(education,profession,adds);//根据学历,院系对应专业  统计
							Vector counts=	tAction.getCounts(education,department, adds);
							data.add(counts);
							
							
					List<String> header1=new ArrayList<String>();
					List<String[]> colleges=new ArrayList<String[]>();
					
						for(String str:header)
						{
							header1.add(str);
						}
					for(Vector v:data)
					{
						String no[]=new String[32];
						int x=0;
						for(Object s:v)
						{
							no[x]=(s+"");
							x++;
						}
						colleges.add(no);
					}
						
					boolean result=false;
					try {
						result = ExcelExportCollege.exportExcel(header1, colleges, file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(result)
					{
						JOptionPane.showMessageDialog(null, "导出成功");
					}else {
						JOptionPane.showMessageDialog(null, "导出失败");
					}
				}
				
				
			}
		});
		btnOut.setBounds(458, 128, 93, 23);
		add(btnOut);
		
		initTable(education,department);
	}
	private void initTable(String education, String department)
	{
		
		Vector<String> adds=tAction.getAllAddress();//地址前两个字
		Vector<String> header=new Vector<String>();//获取地址||表头
		header.add("");
		for(String str:adds)//获取地址||表头  优化
		{
			if(str.equals("黑龙"))
			{
				str=str+"江";
			}if(str.equals("未知")) {
				header.add(str);
				continue;
			}
			header.add(str);
		 }
		Vector<String> profession=tAction.getAllProfessionByDepartmen(department);//获取专业
		
			data=tAction.getCount(education,profession,adds);//根据学历,院系对应专业  统计
				Vector counts=	tAction.getCounts(education,department, adds);
				data.add(counts);
		DefaultTableModel tableModel=new DefaultTableModel(data,header);
		table = new JTable(tableModel);
		table.setSize(30, 30);
		TableColumn firsetColumn=table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(150);
		firsetColumn.setMaxWidth(150);
		firsetColumn.setMinWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		table.getColumnModel().getColumn(8).setPreferredWidth(40);
		table.getColumnModel().getColumn(9).setPreferredWidth(40);
		table.getColumnModel().getColumn(10).setPreferredWidth(40);
		table.getColumnModel().getColumn(11).setPreferredWidth(40);
		table.getColumnModel().getColumn(12).setPreferredWidth(40);
		table.getColumnModel().getColumn(13).setPreferredWidth(40);
		table.getColumnModel().getColumn(14).setPreferredWidth(40);
		table.getColumnModel().getColumn(15).setPreferredWidth(40);
		table.getColumnModel().getColumn(16).setPreferredWidth(40);
		table.getColumnModel().getColumn(17).setPreferredWidth(40);
		table.getColumnModel().getColumn(18).setPreferredWidth(40);
		table.getColumnModel().getColumn(19).setPreferredWidth(40);
		table.getColumnModel().getColumn(20).setPreferredWidth(40);
		table.getColumnModel().getColumn(21).setPreferredWidth(40);
		table.getColumnModel().getColumn(22).setPreferredWidth(40);
		table.getColumnModel().getColumn(23).setPreferredWidth(40);
		table.getColumnModel().getColumn(24).setPreferredWidth(40);
		table.getColumnModel().getColumn(25).setPreferredWidth(40);
		table.getColumnModel().getColumn(26).setPreferredWidth(40);
		table.getColumnModel().getColumn(27).setPreferredWidth(40);
		table.getColumnModel().getColumn(28).setPreferredWidth(40);
		table.getColumnModel().getColumn(29).setPreferredWidth(40);
		table.getColumnModel().getColumn(30).setPreferredWidth(40);
		table.getColumnModel().getColumn(31).setPreferredWidth(50);
	
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
	}
}

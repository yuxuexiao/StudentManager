package com.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.action.QuestionAction;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class commitQuestion extends JPanel {
	private JTable tableIs;
	private JTable tableNot;
	JScrollPane scrollPaneIs = new JScrollPane();
	JScrollPane scrollPaneNot = new JScrollPane();
	QuestionAction qAction=new QuestionAction();
	/**
	 * Create the panel.
	 */
	public commitQuestion() {
		this.setLayout(null);
		
		
		scrollPaneIs.setBounds(41, 96, 540, 136);
		add(scrollPaneIs);
		
		
		
		
		scrollPaneNot.setBounds(41, 278, 540, 136);
		add(scrollPaneNot);
		
		
		
		JLabel lblNewLabel = new JLabel("\u95EE\u9898\u53D1\u5E03");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		lblNewLabel.setBounds(237, 10, 279, 46);
		add(lblNewLabel);
		
		JLabel lblIs = new JLabel("\u5DF2\u53D1\u5E03\u95EE\u9898");
		lblIs.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		lblIs.setBounds(41, 56, 150, 26);
		add(lblIs);
		
		JLabel lblNot = new JLabel("\u672A\u53D1\u5E03\u95EE\u9898");
		lblNot.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		lblNot.setBounds(41, 242, 150, 26);
		add(lblNot);
		initTableIs();
		initTableNot();
	}
	private void initTableIs()
	{
		
		Vector<Vector> result=qAction.getIsNotAction(1);
		Vector<Vector> data=new Vector<Vector>();
		for(Vector v:result)
		{
			Vector vec=new Vector();
			vec.add(v.get(0));
			vec.add(v.get(1));
			vec.add("收回");
			data.add(vec);
			
		}
		Vector header=new Vector();
		header.add("编号");
		header.add("问题");
		header.add("");
		DefaultTableModel tableModel=new DefaultTableModel(data,header);
		tableIs = new JTable(tableModel);
		tableIs.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=tableIs.getSelectedRow();
				int column=tableIs.getSelectedColumn();
				int value=(int)tableIs.getValueAt(row, 0);
				if(column==2)
				{
					int num=qAction.updateOpen(0,value);
					if(num>0)
					{
						JOptionPane.showMessageDialog(null, "成功收回");
						initTableIs();
						initTableNot();
					}else {
						JOptionPane.showMessageDialog(null, "收回失败");
					}
				}
			}
		});
		TableColumn firsetColumn=tableIs.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(30);
		firsetColumn.setMaxWidth(30);
		firsetColumn.setMinWidth(30);
		TableColumn secondColumn=tableIs.getColumnModel().getColumn(2);
		secondColumn.setPreferredWidth(30);
		secondColumn.setMaxWidth(30);
		secondColumn.setMinWidth(30);
		scrollPaneIs.setViewportView(tableIs);
	}
	private void initTableNot()
	{
		Vector<Vector> result=qAction.getIsNotAction(0);
		Vector<Vector> data=new Vector<Vector>();
		for(Vector v:result)
		{
			Vector vec=new Vector();
			vec.add(v.get(0));
			vec.add(v.get(1));
			vec.add("发布");
			data.add(vec);
			
		}
		Vector header=new Vector();
		header.add("编号");
		header.add("问题");
		header.add("");
		DefaultTableModel tableModel=new DefaultTableModel(data,header);
		tableNot = new JTable(tableModel);
		tableNot.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=tableNot.getSelectedRow();
				int column=tableNot.getSelectedColumn();
				int value=(int)tableNot.getValueAt(row, 0);
				if(column==2)
				{
					int num=qAction.updateOpen(1,value);
					if(num>0)
					{
						JOptionPane.showMessageDialog(null, "发布成功");
						initTableIs();
						initTableNot();
					}else {
						JOptionPane.showMessageDialog(null, "发布失败");
					}
				}
			}
		});
		TableColumn firsetColumn=tableNot.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(30);
		firsetColumn.setMaxWidth(30);
		firsetColumn.setMinWidth(30);
		TableColumn secondColumn=tableNot.getColumnModel().getColumn(2);
		secondColumn.setPreferredWidth(30);
		secondColumn.setMaxWidth(30);
		secondColumn.setMinWidth(30);
		scrollPaneNot.setViewportView(tableNot);
	}

}

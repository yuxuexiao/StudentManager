package com.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.action.QuestionAction;
import com.bean.QuestionBean;

import java.awt.Font;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelQuestion extends JPanel {
	private JTable table;
	JScrollPane scrollPane = new JScrollPane();
	QuestionAction qAction=new QuestionAction();
	/**
	 * Create the panel.
	 */
	public SelQuestion() {
		this.setLayout(null);
		
		
		scrollPane.setBounds(43, 96, 535, 349);
		add(scrollPane);
		
		
		
		JLabel lblNewLabel = new JLabel("\u67E5\u770B\u9898\u5E93\u4FE1\u606F");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(212, 10, 274, 66);
		add(lblNewLabel);
		initTable();
	}
	private void initTable()
	{
		
		Vector header=new Vector();
		Vector<Vector> data=new Vector<Vector>();
		Vector<QuestionBean> question=qAction.getAllQuestion();
		for(QuestionBean bean:question)
		{
			Vector v=new Vector();
			v.add(bean.getId());
			v.add(bean.getQuestion());
			v.add("详情");
			v.add("修改");
			v.add("删除");
			data.add(v);
		}
		header.add("编号");
		header.add("问题");
		header.add("");
		header.add("");
		header.add("");
		DefaultTableModel tableModel=new DefaultTableModel(data,header);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=table.getSelectedRow();
				int column=table.getSelectedColumn();
				int value=(int)table.getValueAt(row, 0);
				String question=table.getValueAt(row, 1)+"";
				if(column==2)
				{
					QuestionDialog qDialog=new QuestionDialog(value,question);
				}
				if(column==3)
				{
					int num=JOptionPane.showConfirmDialog(null, "确认修改","修改问题",JOptionPane.OK_CANCEL_OPTION);
					if(num==0)
					{
						int result=qAction.updateQuestion(value,question);
						if(result>0)
						{
							JOptionPane.showMessageDialog(null, "修改成功");
							initTable();
						}else {
							JOptionPane.showMessageDialog(null, "修改失败");
						}
					}
				}
				if(column==4)
				{
					int num=JOptionPane.showConfirmDialog(null, "确认删除","删除问题",JOptionPane.OK_CANCEL_OPTION);
					if(num==0)
					{
						int result=qAction.delQuestion(value);
						if(result>0)
						{
							JOptionPane.showMessageDialog(null, "删除成功");
							initTable();
						}else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
					}
				}
				
			}
		});
		TableColumn firsetColumn=table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(30);
		firsetColumn.setMaxWidth(30);
		firsetColumn.setMinWidth(30);
		TableColumn secondColumn=table.getColumnModel().getColumn(2);
		secondColumn.setPreferredWidth(30);
		secondColumn.setMaxWidth(30);
		secondColumn.setMinWidth(30);
		TableColumn thirdColumn=table.getColumnModel().getColumn(3);
		thirdColumn.setPreferredWidth(30);
		thirdColumn.setMaxWidth(30);
		thirdColumn.setMinWidth(30);
		TableColumn fourColumn=table.getColumnModel().getColumn(4);
		fourColumn.setPreferredWidth(30);
		fourColumn.setMaxWidth(30);
		fourColumn.setMinWidth(30);
		scrollPane.setViewportView(table);
	}
}

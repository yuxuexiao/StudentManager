package com.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.action.QuestionAction;
import com.tools.SizeTools;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuestionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textQ;
	private JTable table;
	JScrollPane scrollPane = new JScrollPane();

	QuestionAction qAction=new QuestionAction();
	/**
	 * Create the dialog.
	 */
	public QuestionDialog(int value,String question) {
		setModal(true);
		setBounds(100, 100, 456, 334);
		setSize(456,334);
		SizeTools.dialogSize(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		System.out.println(question);
		
		scrollPane.setBounds(10, 63, 420, 193);
		contentPanel.add(scrollPane);
		
		
		
		textQ = new JTextField();
		
		textQ.setEditable(false);
		textQ.setBounds(20, 10, 396, 32);
		
		contentPanel.add(textQ);
		textQ.setColumns(10);
		textQ.setText(question);
		initTable(question);
		setVisible(true);
		contentPanel.setVisible(true);
		
	}
	private void initTable(String question)
	{
		Vector header=new Vector();
		header.add("编号");
		header.add("答案");
		header.add("");
		header.add("");
		Vector<Vector> data=new Vector<Vector>();
		Vector<Integer> aid=qAction.getAidByQuestion(question);
		
		Vector<Vector> answer=qAction.getAnswerByAid(aid);
		for(Vector v:answer)
		{
			Vector vec=new Vector();
			vec.add(v.get(0));
			vec.add(v.get(1));
			vec.add("修改");
			vec.add("删除");
			data.add(vec);
		}
		DefaultTableModel tableModel=new DefaultTableModel(data,header);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=table.getSelectedRow();
				int column=table.getSelectedColumn();
				int id=(int)table.getValueAt(row, 0);
				String answer=table.getValueAt(row, 1)+"";
				if(column==2)
				{
					int num=JOptionPane.showConfirmDialog(null, "确认修改","修改答案",JOptionPane.OK_CANCEL_OPTION);
					if(num==0)
					{
						int result=qAction.updateAnswer(id,answer);
						if(result>0)
						{
							JOptionPane.showMessageDialog(null, "修改成功");
							initTable(question);
						}else {
							JOptionPane.showMessageDialog(null, "修改失败");
						}
					}
				}
				if(column==3)
				{
					int num=JOptionPane.showConfirmDialog(null, "确认修改","修改答案",JOptionPane.OK_CANCEL_OPTION);
					if(num==0)
					{
						int result=qAction.delAnswer(id);
						if(result>0)
						{
							JOptionPane.showMessageDialog(null, "删除成功");
							initTable(question);
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
		scrollPane.setViewportView(table);
	}

}

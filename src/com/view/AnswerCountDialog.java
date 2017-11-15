package com.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.action.QuestionAction;
import com.tools.SizeTools;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AnswerCountDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	QuestionAction qAction=new QuestionAction();
	JScrollPane scrollPane = new JScrollPane();
	/**
	 * Create the dialog.
	 * @param countTimes 
	 * @param question 
	 */
	public AnswerCountDialog(String question, int countTimes) {
		this.setModal(true);
//		setBounds(100, 100, 450, 300);
		setSize(450,300);
		SizeTools.dialogSize(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		scrollPane.setBounds(10, 44, 414, 176);
		contentPanel.add(scrollPane);
		
		
		initTable(question,countTimes);
		contentPanel.setVisible(true);
		setVisible(true);
	}
	private void initTable(String question, int countTimes)
	{
		Vector<Integer> aid=qAction.getAidByQuestion(question);
		
		Vector<Vector> data=qAction.getTimeByAid(aid,countTimes);
		Vector header=new Vector();
		header.add("编号");
		header.add("答案");
		header.add("票数");
		header.add("占比");
		DefaultTableModel tableModel=new DefaultTableModel(data,header);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
	}
}

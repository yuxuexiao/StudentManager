package com.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.action.QuestionAction;
import com.bean.QuestionBean;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AddQuestion extends JPanel {
	private JTextField textAddQ;
	private JTextField textInputA;
	private JTable table;
	
	JScrollPane scrollPane = new JScrollPane();
	QuestionAction qAction=new QuestionAction();
	/**
	 * Create the panel.
	 */
	public AddQuestion() {
		this.setLayout(null);
		
		JLabel lblAddQuestion = new JLabel("\u6DFB\u52A0\u9898\u5E93\u4FE1\u606F");
		lblAddQuestion.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		lblAddQuestion.setBounds(236, 24, 221, 49);
		add(lblAddQuestion);
		
		JLabel lblInputQ = new JLabel("\u8BF7\u8F93\u5165\u95EE\u9898");
		lblInputQ.setBounds(93, 97, 68, 23);
		add(lblInputQ);
		
		textAddQ = new JTextField();
		textAddQ.setBounds(181, 96, 327, 23);
		add(textAddQ);
		textAddQ.setColumns(10);
		
		JButton btnAddQ = new JButton("\u6DFB\u52A0");
		btnAddQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null, "确认添加","添加问题",JOptionPane.OK_CANCEL_OPTION);
				if(n==0)
				{
				int num=qAction.addQuestionOne(textAddQ.getText());
				if(num>0)
				{
					JOptionPane.showMessageDialog(null, "添加成功");
				}else {
					JOptionPane.showMessageDialog(null, "添加失败");
				}
				}
			}
		});
		btnAddQ.setBounds(261, 130, 93, 23);
		add(btnAddQ);
		
		JLabel lblQ = new JLabel("\u8BF7\u9009\u62E9\u95EE\u9898");
		lblQ.setBounds(79, 197, 68, 20);
		add(lblQ);
		
		JComboBox comboBoxQ = new JComboBox();
		
		comboBoxQ.setBounds(181, 197, 327, 20);
		Vector<QuestionBean> question=qAction.getAllQuestion();
		for(QuestionBean bean:question)
		{
			comboBoxQ.addItem(bean.getQuestion());
			
			
		}
		add(comboBoxQ);
		
		
		scrollPane.setBounds(181, 240, 327, 154);
		add(scrollPane);
		
		
		
		JLabel lblAddA = new JLabel("\u6DFB\u52A0\u7B54\u6848");
		lblAddA.setBounds(93, 404, 68, 23);
		add(lblAddA);
		
		textInputA = new JTextField();
		textInputA.setBounds(181, 404, 327, 23);
		add(textInputA);
		textInputA.setColumns(10);
		
		JButton btnAddA = new JButton("\u6DFB\u52A0");
		btnAddA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int qid=qAction.getIdByQuestion(comboBoxQ.getSelectedItem()+"");
				if(qid>0)
				{
					int num=qAction.addAnswer(textInputA.getText());
					if(num>0)
					{
						int aid=qAction.getIdByAnswer(textInputA.getText());
						if(aid>0)
						{
							int result=qAction.relevance(qid,aid);
							if(result>0)
							{
								JOptionPane.showMessageDialog(null, "添加成功");
								initTable(comboBoxQ.getSelectedItem()+"");
							}else {
								JOptionPane.showMessageDialog(null, "添加失败");
							}
						}else {
							JOptionPane.showMessageDialog(null, "添加失败");
						}
					}else {
						JOptionPane.showMessageDialog(null, "添加失败");
					}
				}else {
					JOptionPane.showMessageDialog(null, "添加失败");
				}
				
				
				
				
			}
		});
		btnAddA.setBounds(261, 449, 93, 23);
		add(btnAddA);
		
		JLabel lblAddQ = new JLabel("\u6DFB\u52A0\u95EE\u9898");
		lblAddQ.setBounds(93, 60, 84, 27);
		add(lblAddQ);
		
		JLabel labAddA = new JLabel("\u6DFB\u52A0\u7B54\u6848");
		labAddA.setBounds(93, 163, 84, 27);
		add(labAddA);
		
		comboBoxQ.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				initTable(comboBoxQ.getSelectedItem()+"");
			}
		});
		
		initTable(comboBoxQ.getSelectedItem()+"");
	}
	private void initTable(String question)
	{
		Vector header=new Vector();
		header.add("编号");
		header.add("答案");
		Vector<Vector> data=new Vector<Vector>();
		Vector<Integer> aid=qAction.getAidByQuestion(question);
		data=qAction.getAnswerByAid(aid);
		DefaultTableModel tableModel=new DefaultTableModel(data,header);
		
		table = new JTable(tableModel);
		TableColumn firsetColumn=table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(30);
		firsetColumn.setMaxWidth(30);
		firsetColumn.setMinWidth(30);
		scrollPane.setViewportView(table);
	}

}

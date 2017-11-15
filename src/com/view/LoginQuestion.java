package com.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.action.QuestionAction;
import com.action.StudentAction;
import com.bean.QuestionBean;
import com.tools.SizeTools;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginQuestion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textQuestion;
	private JTable table;
	JScrollPane scrollPane = new JScrollPane();
	QuestionAction qAction=new QuestionAction();
	StudentAction sAction=new StudentAction();
	JLabel lblNewLabel = new JLabel();
	Vector<QuestionBean> question=qAction.getAllOpenQuestion();
	int count=0;
	/**
	 * Create the dialog.
	 */
	public LoginQuestion(String username) {
//		setBounds(100, 100, 512, 357);
		setSize(512,357);
		SizeTools.dialogSize(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblNewLabel.setBounds(204, 10, 173, 43);
		contentPanel.add(lblNewLabel);
		
		
		scrollPane.setBounds(40, 104, 415, 154);
		contentPanel.add(scrollPane);
		
		
		
		textQuestion = new JTextField();
		textQuestion.setEditable(false);
		textQuestion.setBounds(40, 49, 415, 45);
		contentPanel.add(textQuestion);
		textQuestion.setColumns(10);
		if(question.size()<=0)
		{
			JOptionPane.showMessageDialog(null, "欢迎使用大学生管理系统");
			StudentMainFrame smf = new StudentMainFrame();
			smf.setVisible(true);
			dispose();
		}
		initTable(question,username);
		
		
		
		
		setVisible(true);
		contentPanel.setVisible(true);
	
	}
	private void initTable(Vector<QuestionBean> ques,String user)
	{
		textQuestion.setText(ques.get(count).getQuestion());
		lblNewLabel.setText("第"+(count+1)+"题");
		Vector header=new Vector();
		header.add("编号");
		header.add("选项");
		header.add("");
		Vector<Vector> data=new Vector<Vector>();
		Vector<Integer> aid=qAction.getAidByQuestion(ques.get(count).getQuestion());
		
		Vector<Vector> answer=qAction.getAnswerByAid(aid);
		for(Vector v:answer)
		{
			Vector vec=new Vector();
			vec.add(v.get(0));
			vec.add(v.get(1));
			vec.add("回答");
			
			data.add(vec);
		}
		DefaultTableModel tableModel=new DefaultTableModel(data,header);
		table = new JTable(tableModel);
		TableColumn firsetColumn=table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(30);
		firsetColumn.setMaxWidth(30);
		firsetColumn.setMinWidth(30);
		TableColumn secondColumn=table.getColumnModel().getColumn(2);
		secondColumn.setPreferredWidth(30);
		secondColumn.setMaxWidth(30);
		secondColumn.setMinWidth(30);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=table.getSelectedRow();
				int column=table.getSelectedColumn();
				int value=(int)table.getValueAt(row, 0);
				if(column==2)
				{
					int num=qAction.updateAnswerTime(value);
					if(num>0)
					{
						JOptionPane.showMessageDialog(null, "回答成功");
						count++;
						if(count==ques.size())
						{
							int rs=sAction.updateStudentQuestion(user,3);
							if(rs>0)
							{
							JOptionPane.showMessageDialog(null, "欢迎使用大学生管理系统");
							StudentMainFrame smf = new StudentMainFrame();
							smf.setVisible(true);
							dispose();
							return;
							}else {
								JOptionPane.showMessageDialog(null, "登陆失败");
								
							}
						}
						initTable(ques,user);
					}else {
						JOptionPane.showMessageDialog(null, "回答失败");
					}
				
				}
			}
		});
		
		scrollPane.setViewportView(table);
		
	}

}

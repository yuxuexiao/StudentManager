package com.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.action.QuestionAction;
import com.bean.QuestionBean;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TongJiQuestion extends JPanel {
	private JTable table;
	JScrollPane scrollPane = new JScrollPane();
	QuestionAction qAction=new QuestionAction();
	/**
	 * Create the panel.
	 */
	public TongJiQuestion() {
		this.setLayout(null);
		
		
		scrollPane.setBounds(33, 104, 537, 308);
		add(scrollPane);
		
		
		
		JLabel lblNewLabel = new JLabel("\u95EE\u5377\u8C03\u67E5\u4FE1\u606F\u7EDF\u8BA1");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 30));
		lblNewLabel.setBounds(167, 25, 274, 55);
		add(lblNewLabel);
		initTable();
		this.setVisible(true);
	}
	private void initTable()
	{
		Vector<QuestionBean> question=qAction.getAllQuestion();
		Vector<Vector> data=new Vector<Vector>();
		Vector<Integer> aid=new Vector<Integer>();
		int countTime=0;//×ÜÆ±Êý
		for(QuestionBean bean:question)
		{
			Vector v=new Vector();
			v.add(bean.getId());
			v.add(bean.getQuestion());
			aid=qAction.getAidByQuestion(bean.getQuestion());
			countTime=qAction.getCountTimeByAid(aid);
			v.add(countTime);
			v.add("ÏêÏ¸");
			data.add(v);
		}
		Vector header=new Vector();
		header.add("±àºÅ");
		header.add("ÎÊÌâ");
		header.add("×ÜÆ±Êý");
		header.add("");
		DefaultTableModel tableModel=new DefaultTableModel(data,header);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=table.getSelectedRow();
				int column=table.getSelectedColumn();
				String question=table.getValueAt(row, 1)+"";
				int countTimes=(int)table.getValueAt(row, 2);
				AnswerCountDialog acd=new AnswerCountDialog(question,countTimes);
				
			}
		});
		TableColumn firsetColumn=table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(30);
		firsetColumn.setMaxWidth(30);
		firsetColumn.setMinWidth(30);
		TableColumn secondColumn=table.getColumnModel().getColumn(2);
		secondColumn.setPreferredWidth(50);
		secondColumn.setMaxWidth(50);
		secondColumn.setMinWidth(50);
		TableColumn thirdColumn=table.getColumnModel().getColumn(3);
		thirdColumn.setPreferredWidth(30);
		thirdColumn.setMaxWidth(30);
		thirdColumn.setMinWidth(30);
		scrollPane.setViewportView(table);
		
		
	}

}

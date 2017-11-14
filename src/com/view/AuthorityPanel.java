package com.view;

import javax.swing.JPanel;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.action.StudentAction;
import com.action.UserKeyAction;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthorityPanel extends JPanel {
	private JTable tableRoot;
	private JTable tableTeacher;
	JScrollPane scrollRoot = new JScrollPane();
	JScrollPane scrollTeacher = new JScrollPane();
	UserKeyAction uka=new UserKeyAction();
	StudentAction sAction=new StudentAction();
	JScrollPane scrollStudent = new JScrollPane();
	private JTextField textStudentSid;
	private JTable tableStudent;
	private final JButton button = new JButton("\u67E5\u8BE2");
	/**
	 * Create the panel.
	 */
	public AuthorityPanel() {
		this.setLayout(null);
		
		JLabel lblRoot = new JLabel("\u7BA1\u7406\u5458/\u5C31\u4E1A\u6307\u5BFC\u4E2D\u5FC3");
		lblRoot.setBounds(236, 41, 124, 22);
		add(lblRoot);
		
		
		scrollRoot.setBounds(51, 61, 498, 137);
		add(scrollRoot);
		
		
		
		JLabel lblTeacher = new JLabel("\u8F85\u5BFC\u5458");
		lblTeacher.setBounds(276, 208, 97, 22);
		add(lblTeacher);
		initRoot();
		initTeacher();
		scrollTeacher.setBounds(51, 228, 498, 137);
		add(scrollTeacher);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u5BC6\u7801\u91CD\u7F6E");
		lblNewLabel.setBounds(257, 375, 135, 36);
		add(lblNewLabel);
		
		textStudentSid = new JTextField();
		textStudentSid.setBounds(51, 422, 135, 22);
		add(textStudentSid);
		textStudentSid.setColumns(10);
		
		
		scrollStudent.setBounds(51, 454, 498, 78);
		add(scrollStudent);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id=textStudentSid.getText();
				initStudent(id);
			}
		});
		button.setBounds(456, 421, 93, 23);
		
		add(button);
	
		
		
		
	}
	private void initRoot()
	{
		
		Vector<Vector> data = uka.getRoot();
		Vector header = new Vector();
		header.add("���");
		header.add("�û���");
		header.add("����");
		header.add("");
		header.add("");
		DefaultTableModel dtm = new DefaultTableModel(data, header);
		tableRoot = new JTable(dtm);
		tableRoot.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = tableRoot.getSelectedRow();
				int column = tableRoot.getSelectedColumn();
				int id = (int) tableRoot.getValueAt(row, 0);
				if (column == 3) {
					int num = JOptionPane.showConfirmDialog(null, "ȷ������", "��������", JOptionPane.OK_CANCEL_OPTION);
					if (num == 0) {
						int rs1 = uka.initPassRoot(id);
						if (rs1 > 0) {
							JOptionPane.showMessageDialog(null, "�ɹ���������");
							initRoot();
						} else {
							JOptionPane.showMessageDialog(null, "����ʧ��");
						}
					}
				}
				if (column == 4) {
					int num = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��", "ɾ���û�", JOptionPane.OK_CANCEL_OPTION);
					if (num == 0) {
						int rs2 = uka.delRoot(id);
						if (rs2 > 0) {
							JOptionPane.showMessageDialog(null, "�ɹ�ɾ���û�");
							initRoot();
						} else {
							JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
						}
					}
				}
			}
		});
		scrollRoot.setViewportView(tableRoot);
	}
	private void initTeacher()
	{
		Vector<Vector> data=uka.getTeacher();
		Vector header=new Vector();
		header.add("���");
		header.add("�û���");
		header.add("����");
		header.add("");
		header.add("");
		header.add("");
		DefaultTableModel dtm=new DefaultTableModel(data,header);
		tableTeacher = new JTable(dtm);
		tableTeacher.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=tableTeacher.getSelectedRow();
				int column=tableTeacher.getSelectedColumn();
				int id=(int)tableTeacher.getValueAt(row, 0);
				if(column==3)
				{
					JOptionPane.showMessageDialog(null, "����Ȩ�޷���");
					AuthorityDialog ad=new AuthorityDialog(id);
				}
				if(column==4)
				{
					int num=JOptionPane.showConfirmDialog(null,"ȷ������","��������",JOptionPane.OK_CANCEL_OPTION);
					if(num==0) {
						int rs1=uka.initPassRoot(id);
						if(rs1>0)
						{
							JOptionPane.showMessageDialog(null, "�ɹ������û�����");
							initTeacher();
						}else {
							JOptionPane.showMessageDialog(null, "����ʧ��,���¯����");
						}
					}
				}
				if(column==5)
				{
					int num=JOptionPane.showConfirmDialog(null,"ȷ��ɾ��","ɾ���û�",JOptionPane.OK_CANCEL_OPTION);
					if(num==0)
					{
						int rs2=uka.delRoot(id);
						if(rs2>0)
						{
							JOptionPane.showMessageDialog(null, "�ɹ�ɾ���û�");
							initTeacher();
						}else {
							JOptionPane.showMessageDialog(null, "ɾ��ʧ��,���¯����");
						}
					}
				}
			}
		});
		
		
		scrollTeacher.setViewportView(tableTeacher);
	}
	private void initStudent(String id)
	{
		Vector<Vector> data=uka.getStudentById(id);
//		for(Vector v:data)
//		{
//			for(Object o:v)
//			{
//				System.out.println(o);
//			}
//		}
		Vector header=new Vector();
		header.add("ѧ��");
		header.add("����");
		header.add("רҵ");
		header.add("");
		DefaultTableModel dtm=new DefaultTableModel(data,header);
		tableStudent = new JTable(dtm);
		tableStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=tableStudent.getSelectedRow();
				int column=tableStudent.getSelectedColumn();
				String values=tableStudent.getValueAt(row, 0)+"";
				int num=JOptionPane.showConfirmDialog(null, "ȷ������","��������",JOptionPane.OK_CANCEL_OPTION);
				if(num==0)
				{
					int n=uka.initStudentPass(values);
					if(n>0)
					{
						JOptionPane.showMessageDialog(null, "���óɹ�");
					}else {
						JOptionPane.showMessageDialog(null, "����ʧ��");
					}
				}
			}
		});
		scrollStudent.setViewportView(tableStudent);
	}
}

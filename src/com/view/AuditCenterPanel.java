package com.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.action.AuditAction;
import com.bean.AuditBean;
import com.tools.CheckHeaderCellRenderer;
import com.tools.CheckTableModle;

import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class AuditCenterPanel extends JPanel {//δ�����
	private JTextField textCountPage;
	private JTextField textDrumpPage;
	private JTable table;
	JScrollPane scrollPane = new JScrollPane();
	JComboBox comboDepart = new JComboBox();
	JComboBox comboEducation = new JComboBox();
	AuditAction aAction=new AuditAction();
	AuditBean aBean=new AuditBean();
	int countPage=0;//��ҳ��	
	int countRows=0;//������
	int rowsPage=20;//ÿҳ����
	int currentPage=1;//��ǰҳ��
	/**
	 * Create the panel.
	 */
	public AuditCenterPanel() {
		this.setLayout(null);
		
		
		scrollPane.setBounds(47, 73, 637, 346);
		add(scrollPane);
		
		
		
		comboEducation.setBounds(333, 42, 100, 21);
		comboEducation.addItem("����");
		comboEducation.addItem("ר��");
		comboEducation.addItem("˶ʿ");
		comboEducation.addItem("��ʿ");
		
		add(comboEducation);
		Vector<String> allDepart=aAction.getAllDepart();
		
		
		
		comboDepart.setBounds(121, 42, 100, 21);
		for(String str:allDepart)
		{
			
			comboDepart.addItem(str);
		}
		add(comboDepart);
		
		comboEducation.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				aBean.setDepartment(comboDepart.getSelectedItem()+"");
				aBean.setEducation(comboEducation.getSelectedItem()+"");
				initTable(aBean,currentPage);
			}
		});
		comboDepart.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				aBean.setDepartment(comboDepart.getSelectedItem()+"");
				aBean.setEducation(comboEducation.getSelectedItem()+"");
				initTable(aBean,currentPage);
			}
		});
		
		JButton btnBatchAudit = new JButton("\u6279\u91CF\u5BA1\u6838");
		btnBatchAudit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Vector> vector=new Vector<Vector>();
				int rp=0;
				if(currentPage==countPage)
				{
					rp=countRows-rowsPage*(countPage-1);//��ֹ���һҳ����ÿҳ�������޷�ȡֵ�����
				}
				else {
					rp=rowsPage;
				}
				for(int i=0;i<rp;i++)
				{
					Vector v=new Vector();
					v.add(table.getValueAt(i, 0));
					v.add(table.getValueAt(i, 1));
					v.add(table.getValueAt(i, 5));
					vector.add(v);
					
				}
				
				
//				Vector<String[]> tempBatch=aAction.tempBatchStudent(vector);//ͨ��ѧ�Ż�ȡѧ����Ϣ��������
				
//				for(String str[]:tempBatch)
//				{
//					str[34]=String.valueOf(4);
//					str[37]="";
//				}
				int num=aAction.updateBatchTeacher(vector,4);//��ҵָ����������ͨ��
				if(num>0)
				{
					JOptionPane.showMessageDialog(null, num+"��ѧ��������");
					initTable(aBean,currentPage);
				}else {
					JOptionPane.showMessageDialog(null, "���ʧ��");
				}
				
			}
		});
		btnBatchAudit.setBounds(443, 40, 93, 23);
		add(btnBatchAudit);
		
		JButton btnStart = new JButton("\u9996\u9875");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=1)
				{
					currentPage=1;
					initTable(aBean,currentPage);
				}
			}
		});
		btnStart.setBounds(47, 429, 82, 25);
		add(btnStart);
		
		JButton btnUp = new JButton("\u4E0A\u4E00\u9875");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=1)
				{
					currentPage--;
					initTable(aBean,currentPage);
				}
			}
		});
		btnUp.setBounds(139, 429, 82, 25);
		add(btnUp);
		
		JButton btnDown = new JButton("\u4E0B\u4E00\u9875");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=countPage)
				{
					currentPage++;
					initTable(aBean,currentPage);
				}
			}
		});
		btnDown.setBounds(231, 429, 87, 25);
		add(btnDown);
		
		JButton btnLast = new JButton("\u5C3E\u9875");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=countPage)
				{
					currentPage=countPage;
					initTable(aBean,currentPage);
				}
			}
		});
		btnLast.setBounds(328, 429, 82, 25);
		add(btnLast);
		
		JLabel lblNewLabel_1 = new JLabel("\u5171");
		lblNewLabel_1.setBounds(420, 434, 16, 20);
		add(lblNewLabel_1);
		
		textCountPage = new JTextField();
		
		textCountPage.setEditable(false);
		textCountPage.setBounds(446, 430, 46, 24);
		add(textCountPage);
		textCountPage.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u9875");
		lblNewLabel_2.setBounds(502, 433, 24, 21);
		add(lblNewLabel_2);
		
		JButton btnDrump = new JButton("\u8DF3\u8F6C");
		btnDrump.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(textDrumpPage.getText())>=1&&Integer.parseInt(textDrumpPage.getText())<=countPage)
				{
				currentPage=Integer.parseInt(textDrumpPage.getText());
				initTable(aBean,currentPage);
				}
			}
		});
		btnDrump.setBounds(526, 429, 82, 25);
		add(btnDrump);
		
		textDrumpPage = new JTextField();
		textDrumpPage.setColumns(10);
		textDrumpPage.setBounds(616, 430, 46, 24);
		add(textDrumpPage);
		
		JLabel label = new JLabel("\u9875");
		label.setBounds(670, 434, 24, 20);
		add(label);
		
		JLabel labEducation = new JLabel("\u5B66\u5386");
		labEducation.setBounds(266, 42, 46, 21);
		add(labEducation);
		
		JLabel labDepart = new JLabel("\u5B66\u9662");
		labDepart.setBounds(54, 42, 46, 21);
		add(labDepart);
		
		
		
		aBean.setDepartment(comboDepart.getSelectedItem()+"");
		aBean.setEducation(comboEducation.getSelectedItem()+"");
		initTable(aBean,currentPage);
		
	}
	private void initTable(AuditBean bean,int currentPage2)
	{
		countRows=aAction.getCountRows(StudentMainFrame.uBean.getRid(),comboDepart.getSelectedItem()+"",comboEducation.getSelectedItem()+"");//��ȡ��������
		
		
		countPage=countRows%rowsPage>0?countRows/rowsPage+1:countRows/rowsPage;
		
		Vector<Vector> data=new Vector<Vector>();
		data=aAction.getStudentAuditCenter(bean,currentPage2,rowsPage);//��ȡ����Աͨ�����
		Vector<String> header=new Vector<String>();
		header.add("ȫѡ/��ѡ");
		header.add("ѧ��");
		header.add("����");
		header.add("ѧԺ");
		header.add("רҵ");
		header.add("���״̬");
		header.add("");
		textCountPage.setText(String.valueOf(currentPage2)+"/"+String.valueOf(countPage));
		CheckTableModle tableModel=new CheckTableModle(data,header);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=table.getSelectedRow();
				int column=table.getSelectedColumn();
				
				if(column==6&&table.getValueAt(row, 5).equals("�ȴ����"))
				{
					AuditTeacherCenterDialog atcd=new AuditTeacherCenterDialog(StudentMainFrame.uBean.getRid(),table.getValueAt(row, 3)+"",table.getValueAt(row, 1)+"");
					initTable(bean,currentPage);
				}
			}
		});
		table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
		scrollPane.setViewportView(table);
	}


}

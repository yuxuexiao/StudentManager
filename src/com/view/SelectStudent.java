package com.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.action.StudentAction;
import com.bean.StudentBean;
import com.bean.UserKeyBean;
import com.tools.CheckHeaderCellRenderer;
import com.tools.CheckTableModle;
import com.tools.ExcelExportCollege;
import com.tools.ExcelOperate;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SelectStudent extends JPanel {
//	private JTable table;

	
	List<Vector> list=new ArrayList<Vector>();
	StudentAction sAction=new StudentAction();
	
	JScrollPane scrollPane = new JScrollPane();
	private JTextField textSearch;
	
	static UserKeyBean autBean=new UserKeyBean();//��½��Ϣ
	int countPage=0;//��ҳ��	
	int countRows=0;//������
	int rowsPage=20;//ÿҳ����
	int currentPage=1;//��ǰҳ��
	StudentBean sBean=new StudentBean();
	static String studentTableName="";
	int raw=0;//����������
	int column=0;//����������
	private JTable table;
	JLabel lblNow = new JLabel();
	private JTextField textDrumpPage;
	/**
	 * Create the panel.
	 */
	/**
	 * ��ѯ����ѧ��
	 */
	public SelectStudent() {
		setLayout(null);
		setVisible(true);
		
		
		
		scrollPane.setBounds(63, 117, 604, 246);
		
		
		
		add(scrollPane);
		
		JButton btnLast = new JButton("\u5C3E\u9875");
		btnLast.addActionListener(new ActionListener() {//βҳ
			public void actionPerformed(ActionEvent e) {
				currentPage=countPage;
				initTable(sBean,currentPage,studentTableName);
			}
		});
		btnLast.setBounds(342, 372, 82, 23);
		add(btnLast);
		
		JButton btnStart = new JButton("\u9996\u9875");
		btnStart.addActionListener(new ActionListener() {//��ҳ
			public void actionPerformed(ActionEvent e) {
				currentPage=1;
				initTable(sBean,currentPage,studentTableName);
			}
		});
		btnStart.setBounds(63, 372, 82, 23);
		add(btnStart);
		
		
		
		
		
		JLabel lblSearch = new JLabel("\u7B5B\u9009");
		lblSearch.setBounds(63, 62, 29, 15);
		add(lblSearch);
		
		textSearch = new JTextField();
		textSearch.setBounds(117, 59, 179, 21);
		add(textSearch);
		textSearch.setColumns(10);
		
		final JComboBox cmbSearch = new JComboBox();
		cmbSearch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {//�����������������ֵ�ı������sBean
				 sBean=new StudentBean();
				 initTable(sBean,1,studentTableName);
			}
		});
		cmbSearch.addItem("ѧ��");
		cmbSearch.addItem("רҵ");
		cmbSearch.addItem("����");
		cmbSearch.addItem("�Ա�");
		
		cmbSearch.setBounds(342, 62, 82, 21);
		add(cmbSearch);
		
		
		
		JButton btnSelect = new JButton("\u7B5B\u9009");
		btnSelect.addActionListener(new ActionListener() {//������ѯѧ��
			public void actionPerformed(ActionEvent arg0) {
				String search=textSearch.getText();
				
				if("ѧ��".equals(cmbSearch.getSelectedItem()))
				{
					sBean.setSid(search);
				}
				if("רҵ".equals(cmbSearch.getSelectedItem()))
				{
					sBean.setProfession(search);
				}
				if("����".equals(cmbSearch.getSelectedItem()))
				{
					sBean.setName(search);
				}
				if("�Ա�".equals(cmbSearch.getSelectedItem()))
				{
					sBean.setSex(search);
				}
//				try {
//					Class cla=Class.forName("com.view.StudentMainFrame.StudentMainFrame().tree");//����ʵ������  �����Ҷ��;
//					JTree tree=(JTree)cla.newInstance();
//					String get=tree.getLastSelectedPathComponent()+"";
//				if("��������Ϣ����".equals(get))//��ȡ��Ӧѧ��ѧ������
//				{
//					studentTableName="student2";
//				}
//				if("˶ʿ����Ϣ����".equals(get))
//				{
//					studentTableName="student3";
//				}
//				if("��ʿ����Ϣ����".equals(get))
//				{
//					studentTableName="student4";
//				}
//				if("ר������Ϣ����".equals(get))
//				{
//					studentTableName="student1";
//				}
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				
				
				initTable(sBean,1,studentTableName);
				
				
			}
		});
		btnSelect.setBounds(448, 61, 66, 23);
		add(btnSelect);
		
		
		
		JButton btnNext = new JButton("\u4E0B\u4E00\u9875");//��һҳ
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=countPage)
				{
					initTable(sBean,++currentPage,studentTableName);
				}else {
					initTable(sBean,currentPage,studentTableName);
				}
			}
		});
		btnNext.setBounds(247, 373, 82, 23);
		add(btnNext);
		
		JButton btnUp = new JButton("\u4E0A\u4E00\u9875");//��һҳ
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=1)
				{
					initTable(sBean,--currentPage,studentTableName);
				}else {
					initTable(sBean,currentPage,studentTableName);
				}
			}
		});
		btnUp.setBounds(155, 373, 82, 23);
		add(btnUp);
		
		JButton buttonOut = new JButton("\u6279\u91CF\u5BFC\u51FA");
		buttonOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//��������
				JFileChooser jfc=new JFileChooser();
				int num=jfc.showSaveDialog(null);
				
				if(num==0)
				{
					File file=jfc.getSelectedFile();
					List<String> header=new ArrayList<String>();
					header.add("������");
					header.add("����");
					header.add("�Ա�");
					header.add("ѧ��");
					header.add("����");
					header.add("����");
					header.add("������ò");
					header.add("����");
					header.add("ѧ��");
					header.add("����");
					header.add("רҵ");
					header.add("����");
					header.add("������ʽ");
					header.add("����");
					header.add("��ַ");
					header.add("����");
					header.add("����");
					header.add("���֤��");
					header.add("��ѧ����");
					header.add("ѧ��");
					header.add("�����ί��");
					header.add("��������");
					header.add("����");
					header.add("ѧ���䶯");
					header.add("����");
					header.add("��ҵʱ��");
					header.add("��ϵ��ʽ");
					header.add("����");
					header.add("�����Ƿ���У");
					header.add("Ӣ��ȼ�");
					header.add("ѧУ���ͼ�¼");
					header.add("Ժϵ");
					header.add("�꼶");
					header.add("����");
					header.add("���");
					header.add("��֪��дɶ��");
					header.add("ID");
					header.add("���δͨ��ԭ��");
					
					String department="";
					if(StudentMainFrame.uBean.getRid()==2)
					{
						department=StudentMainFrame.uBean.getUsername();//���Ϊ����Ա ��ֻ�ܵ�����Ժѧ��
					
					}
					List<String[]> colleges=sAction.getStudentAll(studentTableName,department);//
					
					boolean result;
					try {
						result = ExcelExportCollege.exportExcel(header, colleges, file);
						if(result)
						{
							JOptionPane.showMessageDialog(null, "�����ɹ�");
						}else {
							JOptionPane.showMessageDialog(null, "����ʧ��");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		buttonOut.setBounds(535, 58, 93, 23);
		if(autBean.getAuthority()[4]=='e')//����Ȩ�޿���
		{
			add(buttonOut);
		}
		JButton buttonIn = new JButton("\u6279\u91CF\u5BFC\u5165");
		buttonIn.addActionListener(new ActionListener() {//��������ѧ����Ϣ
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();
				int num=jfc.showOpenDialog(null);
				if(num==0)
				{
					File file=jfc.getSelectedFile();
					try {
						String result[][]=ExcelOperate.getData(file, 1);
						
						boolean flag=false;
								flag=sAction.addBatchStudent(result,studentTableName);
						if(flag)
						{
							JOptionPane.showMessageDialog(null, "����ɹ�");
						}else {
							JOptionPane.showMessageDialog(null, "����ʧ��");
						}
					}catch(Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		buttonIn.setBounds(535, 84, 93, 23);
		if(autBean.getAuthority()[3]=='d')//����Ȩ�޿���
		{
			add(buttonIn);
		}
		
		JButton buttonDel = new JButton("\u6279\u91CF\u5220\u9664");
		buttonDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Vector> result=new Vector<Vector>();
				for(int i=0;i<rowsPage;i++)
				{
					Vector rs=new Vector();
					rs.add(table.getValueAt(i, 0));
					rs.add(table.getValueAt(i, 1));
					result.add(rs);
				}
				int num=sAction.delBatchStudent(result,studentTableName);
				if(num>0)
				{
					JOptionPane.showMessageDialog(null, num+"����Ϣ��ɾ��");
					countRows=countRows-num;
					initTable(sBean,currentPage,studentTableName);
				}else {
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
				}
				
			}
		});
		buttonDel.setBounds(136, 84, 93, 23);
		add(buttonDel);
		
		
		lblNow.setBounds(434, 373, 90, 23);
		add(lblNow);
		
		textDrumpPage = new JTextField();
		textDrumpPage.setBounds(530, 373, 45, 23);
		add(textDrumpPage);
		textDrumpPage.setColumns(10);
		
		JButton btnDrump = new JButton("\u8DF3\u8F6C");
		btnDrump.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.valueOf(textDrumpPage.getText())>=1&&Integer.valueOf(textDrumpPage.getText())<=countPage)
				{
					currentPage=Integer.valueOf(textDrumpPage.getText());
					initTable(sBean,currentPage,studentTableName);
				}
			}
		});
		btnDrump.setBounds(585, 372, 82, 23);
		add(btnDrump);
		
		
		
		
//		StudentBean sBean =new StudentBean();
		//initTable(null,1,studentTableName);
		
		this.repaint();
		
	}
	//��ʼ�� ��
	private void initTable(final StudentBean stuBean,int currentPage2,String stuTableName)
	{
		String teacher="";
		if(autBean.getRid()==2)//���Ϊ����Ա  �����ñ�Ժϵѧ��;
		{
			teacher="and department='"+autBean.getUsername()+"' ";
			
		}
		countRows=sAction.getCountRows(stuBean,stuTableName,teacher);//��ȡ��������

		
		countPage=countRows%rowsPage>0?countRows/rowsPage+1:countRows/rowsPage;
		
		
		lblNow.setText(currentPage+"/"+countPage);
		Vector<Vector> data=new Vector<Vector>();
		list=sAction.selAllStudent(stuBean,rowsPage,currentPage2,stuTableName,teacher);
		for(Vector v:list)
		{
			if(autBean.getAuthority()[1]=='b')//�޸�ѧ����Ϣ����
			{
			v.add("�޸�");
			}
			
			v.add("ɾ��");
			
			data.add(v);
		}
		
		Vector header=new Vector();
		header.add("ȫѡ/��ѡ");
		header.add("ѧ��");
		header.add("����");
		
		header.add("�Ա�");
		header.add("רҵ");
		if(autBean.getAuthority()[2]=='c')//ɾ��ѧ����Ϣ����
		{
		header.add("");
		}
		
			
		
		if(autBean.getAuthority()[1]=='b')//�޸�ѧ����ϢȨ�޿���
		{
		header.add("");
		}
		
		CheckTableModle tableModel=new CheckTableModle(data, header);
	
		if(autBean.getAuthority()[0]=='a')//�鿴��Դ��ϢȨ�޿���
		{
			table = new JTable(tableModel){  
	            @Override  
	            public boolean isCellEditable(int row,int column){  
	            	if(column==1) {
	                return false;  
	            	}else {
	            		return true;
	            	}
	            }  
	        };
			
			table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					column=table.getSelectedColumn();
					raw=table.getSelectedRow();
					int columnx=5;
					int columny=6;
					if(autBean.getAuthority()[1]=='o')
					{
						columnx=10;
						columny=5;
					}
					
					String value=(String)table.getValueAt(raw, 1);//��ȡѡ���е�ѧ��
					if(column==columnx)
					{
						JOptionPane.showMessageDialog(null, "�޸�ѧ����Ϣ");
						UpdateStudentDialog updateStudent=new UpdateStudentDialog(stuTableName,value);
						
						updateStudent.setVisible(true);
						initTable(stuBean,currentPage,stuTableName);
					}
					if(column==columny)
					{
						int result=JOptionPane.showConfirmDialog(null,"�Ƿ�ɾ����Ϣ?","ɾ��ѧ����Ϣ", JOptionPane.YES_NO_CANCEL_OPTION);
						if(result==0)
						{
							int num=sAction.deleteStudent(value,stuTableName);
							if(num>0)
							{
								JOptionPane.showMessageDialog(null, "ɾ���ɹ�");	
								countRows--;
							initTable(stuBean,currentPage,stuTableName);
							
							}else{
								JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
							}
						}
					}
				}
			});
		}
		
		
		
		
		
		
		
		
		
		scrollPane.setViewportView(table);
	}
}

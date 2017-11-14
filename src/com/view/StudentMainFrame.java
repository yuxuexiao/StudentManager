package com.view;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import com.action.R_SAction;
import com.action.SkillAction;
import com.bean.SkillBean;
import com.bean.UserKeyBean;
import com.tools.SizeTools;

import java.awt.Color;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class StudentMainFrame extends JFrame {

	private JPanel contentPane;
	SkillAction sa=new SkillAction();
	Map<String, String> map=new HashMap<String, String>();
	
	
	
	static UserKeyBean uBean=null;
	R_SAction rsAction=new R_SAction();
	List<Integer> sidList=null;
	/**
	 * Create the frame.
	 */
	public StudentMainFrame() {
		setBackground(new Color(192, 192, 192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
//		setBounds(100, 100, 889, 641);
		setSize(900,650);
		SizeTools.frameSize(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.WHITE);
		panelTop.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTop.setLayout(new BorderLayout());
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel labelStudent = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u5927\u5B66\u751F\u7BA1\u7406\u7CFB\u7EDF");
		labelStudent.setHorizontalAlignment(SwingConstants.CENTER);
		labelStudent.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		panelTop.add(labelStudent,BorderLayout.NORTH);
		
		JLabel lblusename = new JLabel("�û���:"+uBean.getUsername());
		panelTop.add(lblusename, BorderLayout.WEST);
		
		JButton button = new JButton("\u9000\u51FA\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame lf=new LoginFrame();
			}
		});
		panelTop.add(button, BorderLayout.EAST);
		
		final JPanel panelRight = new JPanel();
		panelRight.setBackground(Color.WHITE);
		
//		panelRight.setBackground(Color.WHITE);
		panelRight.setLayout(null);
		panelRight.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		contentPane.add(panelRight, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(StudentMainFrame.class.getResource("/com/view/QQ\u56FE\u724720170817173006.jpg")));
		lblNewLabel.setBounds(0, 0, 775, 553);
		panelRight.add(lblNewLabel);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setLayout(new BorderLayout());
		panelLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelLeft, BorderLayout.WEST);
		
		JScrollPane scrollPane = new JScrollPane();
		panelLeft.add(scrollPane,BorderLayout.CENTER);
		
		
		
		
		
		String rootName = sa.getRoot();
		DefaultMutableTreeNode root=new DefaultMutableTreeNode(rootName);

		
		
		sidList=rsAction.getSid(uBean);//��ȡȨ����Ϣ
		
		
		/**
		 * �����ӽڵ�
		 * 
		 */
		tree(root,1);
		
		
		final JTree tree = new JTree(root);
		tree.setBackground(Color.WHITE);
		tree.setForeground(Color.BLACK);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				//��ȡ���ѡ�����Ҷ
				
				String value= tree.getLastSelectedPathComponent()+"";
				String url=map.get(value);//�õ���Ҷ����Ӧ���ݿ��д�ĵ�ַ
				if("��������Ϣ����".equals(value))//��ȡ��Ӧѧ��ѧ������
				{
					SelectStudent.studentTableName="����";
				}
				if("˶ʿ����Ϣ����".equals(value))
				{
					SelectStudent.studentTableName="˶ʿ";
				}
				if("��ʿ����Ϣ����".equals(value))
				{
					SelectStudent.studentTableName="��ʿ";
				}
				if("ר������Ϣ����".equals(value))
				{
					SelectStudent.studentTableName="ר��";
				}
				
				//��̬���������л�
				if("��ר��רҵ�����".equals(value))
				{
					ImportCode.codeBean.setName("רҵ");
					ImportCode.codeBean.setTable("normalspecialty");
					
				}
				if("��������".equals(value))
				{
					ImportCode.codeBean.setName("����");
					ImportCode.codeBean.setTable("nation");
				}
				if("������ʽרҵ�����".equals(value))
				{
					ImportCode.codeBean.setName("������ʽ");
					ImportCode.codeBean.setTable("trainingmode");
				}
				
				if("ѧ���䶯�����".equals(value))
				{
					ImportCode.codeBean.setName("ѧ���䶯");
					ImportCode.codeBean.setTable("statuschange");
				}
				
				if("ѧ����Դ�ش����".equals(value))
				{
					ImportCode.codeBean.setName("��Դ��");
					ImportCode.codeBean.setTable("address");
				}
				
				if("�о���רҵ�����".equals(value))
				{
					ImportCode.codeBean.setName("רҵ");
					ImportCode.codeBean.setTable("graduateprogram");
				}
				
				if("������ò�����".equals(value))
				{
					ImportCode.codeBean.setName("������ò");
					ImportCode.codeBean.setTable("politicstatus");
				}
				
				if("�������ִ����".equals(value))
				{
					ImportCode.codeBean.setName("����");
					ImportCode.codeBean.setTable("language");
				}
				
				
				if(url!=null)    
				{
					try {
						Class cla=Class.forName(url);    
						JPanel jPanel = (JPanel)cla.newInstance();//���÷���ʵ����panel
						panelRight.removeAll();
						
						jPanel.setVisible(true);
						jPanel.setSize(panelRight.getSize());
						
						panelRight.add(jPanel);
						panelRight.revalidate();
						panelRight.repaint();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
		
			}
		});

		
		
		
		
		
		
		scrollPane.setViewportView(tree);
		
		
		
		
		
	}
	
/**
 * 	
 * @param root���ڵ�
 * @param id  ���ڵ�id
 */
	
	private void tree(DefaultMutableTreeNode root, int id) {
		List<SkillBean> list=sa.getChildNode(id);
		
		for(SkillBean l:list)
		{
			if(isExists(l.getId()))
			{

			DefaultMutableTreeNode child=new DefaultMutableTreeNode(l.getName());
			root.add(child);
			map.put(l.getName(), l.getUrl());
			tree(child,l.getId());
			}
			
		}
		
	}
	
	private boolean isExists(int sid)
	{
		
		for(int i:sidList)
		{
			if(sid==i)
			{
				
				return true;
			}
			
		}
		return false;
	}
	

}

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
		labelStudent.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panelTop.add(labelStudent,BorderLayout.NORTH);
		
		JLabel lblusename = new JLabel("用户名:"+uBean.getUsername());
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

		
		
		sidList=rsAction.getSid(uBean);//获取权限信息
		
		
		/**
		 * 加载子节点
		 * 
		 */
		tree(root,1);
		
		
		final JTree tree = new JTree(root);
		tree.setBackground(Color.WHITE);
		tree.setForeground(Color.BLACK);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				//获取最后选择的树叶
				
				String value= tree.getLastSelectedPathComponent()+"";
				String url=map.get(value);//得到树叶所对应数据库中存的地址
				if("本科生信息管理".equals(value))//获取对应学历学生表名
				{
					SelectStudent.studentTableName="本科";
				}
				if("硕士生信息管理".equals(value))
				{
					SelectStudent.studentTableName="硕士";
				}
				if("博士生信息管理".equals(value))
				{
					SelectStudent.studentTableName="博士";
				}
				if("专科生信息管理".equals(value))
				{
					SelectStudent.studentTableName="专科";
				}
				
				//动态代码库界面切换
				if("本专科专业代码库".equals(value))
				{
					ImportCode.codeBean.setName("专业");
					ImportCode.codeBean.setTable("normalspecialty");
					
				}
				if("民族代码库".equals(value))
				{
					ImportCode.codeBean.setName("民族");
					ImportCode.codeBean.setTable("nation");
				}
				if("培养方式专业代码库".equals(value))
				{
					ImportCode.codeBean.setName("培养方式");
					ImportCode.codeBean.setTable("trainingmode");
				}
				
				if("学籍变动代码库".equals(value))
				{
					ImportCode.codeBean.setName("学籍变动");
					ImportCode.codeBean.setTable("statuschange");
				}
				
				if("学生生源地代码库".equals(value))
				{
					ImportCode.codeBean.setName("生源地");
					ImportCode.codeBean.setTable("address");
				}
				
				if("研究生专业代码库".equals(value))
				{
					ImportCode.codeBean.setName("专业");
					ImportCode.codeBean.setTable("graduateprogram");
				}
				
				if("政治面貌代码库".equals(value))
				{
					ImportCode.codeBean.setName("政治面貌");
					ImportCode.codeBean.setTable("politicstatus");
				}
				
				if("主修语种代码库".equals(value))
				{
					ImportCode.codeBean.setName("语种");
					ImportCode.codeBean.setTable("language");
				}
				
				
				if(url!=null)    
				{
					try {
						Class cla=Class.forName(url);    
						JPanel jPanel = (JPanel)cla.newInstance();//利用反射实例化panel
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
 * @param root根节点
 * @param id  根节点id
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

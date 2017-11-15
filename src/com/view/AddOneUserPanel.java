package com.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.action.UserKeyAction;
import com.bean.UserKeyBean;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class AddOneUserPanel extends JPanel {
	private JTextField textUserRoot;
	private JTextField textPassRoot;
	private JTextField textPassTeach;
	UserKeyAction uka=new UserKeyAction();
	final String authority="abcde";
	final char DefaultAut[]=authority.toCharArray();
	/**
	 * Create the panel.
	 */
	public AddOneUserPanel() {
		
		this.setLayout(null);
		
		JLabel lblRoot = new JLabel("\u7BA1\u7406\u5458/\u5C31\u4E1A\u6307\u5BFC\u4E2D\u5FC3\u7528\u6237\u6DFB\u52A0");
		lblRoot.setBounds(119, 27, 202, 23);
		add(lblRoot);
		
		JLabel lblUserRoot = new JLabel("\u7528\u6237\u540D");
		lblUserRoot.setBounds(138, 71, 54, 15);
		add(lblUserRoot);
		
		textUserRoot = new JTextField();
		textUserRoot.setBounds(202, 68, 119, 21);
		add(textUserRoot);
		textUserRoot.setColumns(10);
		
		JLabel lblPassRoot = new JLabel("\u5BC6\u7801");
		lblPassRoot.setBounds(138, 112, 54, 15);
		add(lblPassRoot);
		
		textPassRoot = new JTextField();
		textPassRoot.setBounds(202, 109, 119, 21);
		add(textPassRoot);
		textPassRoot.setColumns(10);
		
		JComboBox comboClass = new JComboBox();
		comboClass.setBounds(202, 152, 119, 21);
		comboClass.addItem("管理员");
		comboClass.addItem("就业指导中心");
		add(comboClass);
		
		JLabel lblClass = new JLabel("\u7C7B\u522B");
		lblClass.setBounds(138, 155, 54, 18);
		add(lblClass);
		
		JButton btnRoot = new JButton("\u6DFB\u52A0");
		btnRoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null,"确认添加","添加用户",JOptionPane.OK_CANCEL_OPTION);
				if(n==0)
				{
				UserKeyBean ukb=new UserKeyBean();
				if(comboClass.getSelectedItem().equals("管理员"))
				{
					ukb.setRid(4);
				}
				if(comboClass.getSelectedItem().equals("就业指导中心")) {
					ukb.setRid(3);
				}
				ukb.setUsername(textUserRoot.getText());
				ukb.setPassword(textPassRoot.getText());
				
				ukb.setAuthority(DefaultAut);
				int num=uka.addUser(ukb);
				if(num>0){
					JOptionPane.showMessageDialog(null, "添加成功");
				}else {
					JOptionPane.showMessageDialog(null, "添加失败");
				}
				}
			}
		});
		btnRoot.setBounds(380, 108, 93, 23);
		add(btnRoot);
		
		JLabel labTeach = new JLabel("\u8F85\u5BFC\u5458\u6DFB\u52A0");
		labTeach.setBounds(119, 195, 202, 23);
		add(labTeach);
		
		JLabel lblDepartTeach = new JLabel("\u5B66\u9662");
		lblDepartTeach.setBounds(138, 242, 54, 15);
		add(lblDepartTeach);
		
		JComboBox comboDepartTeach = new JComboBox();
		comboDepartTeach.setBounds(202, 239, 119, 23);
		
		Vector<String> notExsistTeacher=uka.getNotExsistTeacher();//获取未注册辅导员学院名称
		for(String str:notExsistTeacher)
		{
			comboDepartTeach.addItem(str);
		}
		add(comboDepartTeach);
		
		JLabel lblPassTeach = new JLabel("\u5BC6\u7801");
		lblPassTeach.setBounds(138, 295, 54, 15);
		add(lblPassTeach);
		
		textPassTeach = new JTextField();
		textPassTeach.setBounds(202, 291, 119, 23);
		add(textPassTeach);
		textPassTeach.setColumns(10);
		
		JButton buttonTeach = new JButton("\u6DFB\u52A0");
		buttonTeach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null,"确认添加","添加用户",JOptionPane.OK_CANCEL_OPTION);
				if(n==0)
				{
				UserKeyBean ukbTeach=new UserKeyBean();
				
				ukbTeach.setUsername(comboDepartTeach.getSelectedItem()+"");				
				ukbTeach.setPassword(textPassTeach.getText());
				ukbTeach.setRid(2);
				ukbTeach.setAuthority(DefaultAut);
				int num=uka.addUser(ukbTeach);
				if(num>0)
				{
					JOptionPane.showMessageDialog(null, "添加成功");
				}else {
					JOptionPane.showMessageDialog(null,"添加失败");
				}
				}
			}
		});
		buttonTeach.setBounds(380, 238, 93, 23);
		add(buttonTeach);
		this.setVisible(true);
	}

}

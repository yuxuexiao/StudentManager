package com.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.action.StudentAction;
import com.action.UserKeyAction;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdatePass extends JPanel {
	private JTextField textOldPss;
	private JTextField textNewPass;
	private JTextField textAgain;
	StudentAction sAction=new StudentAction();
	UserKeyAction uAction=new UserKeyAction();
	/**
	 * Create the panel.
	 */
	public UpdatePass() {
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5BC6\u7801\u4FEE\u6539");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		lblNewLabel.setBounds(228, 24, 242, 59);
		add(lblNewLabel);
		
		JLabel lblOldPass = new JLabel("\u539F\u5BC6\u7801");
		lblOldPass.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblOldPass.setBounds(126, 99, 78, 31);
		add(lblOldPass);
		
		textOldPss = new JTextField();
		textOldPss.setBounds(214, 99, 209, 31);
		add(textOldPss);
		textOldPss.setColumns(10);
		
		JLabel labNewPass = new JLabel("\u65B0\u5BC6\u7801");
		labNewPass.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		labNewPass.setBounds(126, 168, 78, 31);
		add(labNewPass);
		
		textNewPass = new JTextField();
		textNewPass.setColumns(10);
		textNewPass.setBounds(214, 168, 209, 31);
		add(textNewPass);
		
		JLabel labelAgain = new JLabel("\u518D\u6B21\u8F93\u5165");
		labelAgain.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		labelAgain.setBounds(126, 231, 78, 31);
		add(labelAgain);
		
		textAgain = new JTextField();
		textAgain.setColumns(10);
		textAgain.setBounds(214, 231, 209, 31);
		add(textAgain);
		
		JButton btnCommit = new JButton("\u63D0\u4EA4");
		btnCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=StudentMainFrame.uBean.getUsername();
				String password=StudentMainFrame.uBean.getPassword();
				String newPass=textNewPass.getText();
				int rid=StudentMainFrame.uBean.getRid();
				if(rid==1)//修改学生密码
				{
					if(textNewPass.getText().equals(textAgain.getText()))
					{
						if(textOldPss.getText().equals(password))
						{
							int num=sAction.updatePassStudent(username,newPass);//这是个标记  太尼玛乱了
							if(num>0)
							{
								JOptionPane.showMessageDialog(null, "修改成功");
							}else {
								JOptionPane.showMessageDialog(null, "修改失败");
							}
						}else {
							JOptionPane.showMessageDialog(null, "密码错误");
						}
					}else {
						JOptionPane.showMessageDialog(null, "两次密码不一致");
					}
				}else {//修改非学生密码
					if(textNewPass.getText().equals(textAgain.getText()))
					{
						if(textOldPss.getText().equals(password))
						{
							int num=uAction.updatePass(username,newPass);
							if(num>0)
							{
								JOptionPane.showMessageDialog(null, "修改成功");
							}else {
								JOptionPane.showMessageDialog(null, "修改失败");
							}
						}else {
							JOptionPane.showMessageDialog(null, "密码错误");
						}
					}else {
						JOptionPane.showMessageDialog(null, "两次密码不一致");
					}
				}
			}
		});
		btnCommit.setBounds(228, 312, 103, 37);
		add(btnCommit);
	}

}

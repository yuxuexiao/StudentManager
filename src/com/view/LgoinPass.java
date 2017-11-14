package com.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.action.StudentAction;
import com.action.UserKeyAction;
import com.tools.SizeTools;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LgoinPass extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textOldPass;
	private JTextField textNewPass;
	private JTextField textPassAgain;
	StudentAction sAction=new StudentAction();
	UserKeyAction uAction=new UserKeyAction();
	

	/**
	 * Create the dialog.
	 * @param username,String password 
	 */
	public LgoinPass(String username,String password,int rid) {
//		setBounds(100, 100, 540, 394);
		setSize(540,394);
		SizeTools.dialogSize(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u5BC6\u7801\u4FEE\u6539");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 27));
		lblNewLabel.setBounds(163, 10, 277, 62);
		contentPanel.add(lblNewLabel);
		
		JLabel lblOldPass = new JLabel("\u539F\u5BC6\u7801");
		lblOldPass.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		lblOldPass.setBounds(126, 87, 70, 25);
		contentPanel.add(lblOldPass);
		
		textOldPass = new JTextField();
		textOldPass.setBounds(205, 82, 188, 35);
		contentPanel.add(textOldPass);
		textOldPass.setColumns(10);
		
		JLabel labelNewPass = new JLabel("\u65B0\u5BC6\u7801");
		labelNewPass.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		labelNewPass.setBounds(126, 132, 70, 25);
		contentPanel.add(labelNewPass);
		
		textNewPass = new JTextField();
		textNewPass.setColumns(10);
		textNewPass.setBounds(205, 127, 188, 35);
		contentPanel.add(textNewPass);
		
		JLabel labAgain = new JLabel("\u518D\u6B21\u8F93\u5165");
		labAgain.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		labAgain.setBounds(126, 186, 70, 25);
		contentPanel.add(labAgain);
		
		textPassAgain = new JTextField();
		textPassAgain.setColumns(10);
		textPassAgain.setBounds(205, 181, 188, 35);
		contentPanel.add(textPassAgain);
		
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPass=textNewPass.getText();
				
				if(rid==1)//�޸�ѧ������
				{
					if(textNewPass.getText().equals(textPassAgain.getText()))
					{
						if(textOldPass.getText().equals(password))
						{
							int num=sAction.updatePassStudent(username,newPass);//���Ǹ����  ̫��������
							
							if(num>0)
							{
								int rs=sAction.updateStudentQuestion(username,2);
								if(rs>0)
								{
									JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
									dispose();
									LoginFrame lFrame=new LoginFrame();
								}else {
									JOptionPane.showMessageDialog(null, "�޸�ʧ��");
								}
							}else {
								JOptionPane.showMessageDialog(null, "�޸�ʧ��");
							}
						}else {
							JOptionPane.showMessageDialog(null, "�������");
						}
					}else {
						JOptionPane.showMessageDialog(null, "�������벻һ��");
					}
				}
			}
		});
		btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		btnNewButton.setBounds(205, 258, 93, 23);
		contentPanel.add(btnNewButton);
		contentPanel.setVisible(true);
		setVisible(true);
	}

}

package com.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.action.DepartmentAction;
import com.bean.DepartmentBean;
import com.tools.SizeTools;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class AddDepartOne extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textDepartName;
	private JTextField textPrfsNameN;
	private JTextField textPrfsCodeN;
	private JTextField textPrfsNameG;
	private JTextField textPrfsCodeG;
	DepartmentAction dAction=new DepartmentAction();
	

	/**
	 * Create the dialog.
	 */
	public AddDepartOne() {
		
		
		this.setModal(true);
		setBounds(100, 100, 479, 451);
		setSize(479,451);
		SizeTools.dialogSize(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel labelAddDpart = new JLabel("\u6DFB\u52A0\u5B66\u9662");
		labelAddDpart.setBounds(48, 27, 67, 15);
		contentPanel.add(labelAddDpart);
		
		JLabel lblDepartName = new JLabel("\u5B66\u9662\u540D\u79F0");
		lblDepartName.setBounds(48, 66, 54, 15);
		contentPanel.add(lblDepartName);
		
		textDepartName = new JTextField();
		textDepartName.setBounds(132, 63, 131, 21);
		contentPanel.add(textDepartName);
		textDepartName.setColumns(10);
		
		JButton btnAddDepart = new JButton("\u6DFB\u52A0");
		btnAddDepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null,"确认添加","添加学院",JOptionPane.OK_CANCEL_OPTION);
				if(n==0)
				{
				String name=textDepartName.getText();
				int num=dAction.addDepartmentOne(name);
				if(num>0)
				{
					JOptionPane.showMessageDialog(null, "添加成功");
				}
				else {
					JOptionPane.showMessageDialog(null, "添加失败");
				}
				}
			}
		});
		btnAddDepart.setBounds(293, 62, 93, 23);
		contentPanel.add(btnAddDepart);
		
		JLabel lblN = new JLabel("\u6DFB\u52A0\u672C\u4E13\u79D1\u4E13\u4E1A");
		lblN.setBounds(48, 110, 99, 15);
		contentPanel.add(lblN);
		
		JLabel lblDpartN = new JLabel("\u9009\u62E9\u9662\u7CFB");
		lblDpartN.setBounds(61, 135, 54, 23);
		contentPanel.add(lblDpartN);
		
		JComboBox comboBoxN = new JComboBox();
		comboBoxN.setBounds(132, 135, 131, 23);
		Vector comboN=dAction.countRows();
		for(Object o:comboN)
		{
			comboBoxN.addItem(o);
		}
		contentPanel.add(comboBoxN);
		
		JLabel labPrfsNameN = new JLabel("\u4E13\u4E1A\u540D\u79F0");
		labPrfsNameN.setBounds(61, 197, 54, 21);
		contentPanel.add(labPrfsNameN);
		
		textPrfsNameN = new JTextField();
		textPrfsNameN.setColumns(10);
		textPrfsNameN.setBounds(132, 197, 131, 21);
		contentPanel.add(textPrfsNameN);
		
		JLabel labPrfsCodeN = new JLabel("\u4E13\u4E1A\u4EE3\u7801");
		labPrfsCodeN.setBounds(61, 228, 54, 18);
		contentPanel.add(labPrfsCodeN);
		
		textPrfsCodeN = new JTextField();
		textPrfsCodeN.setColumns(10);
		textPrfsCodeN.setBounds(132, 225, 131, 21);
		contentPanel.add(textPrfsCodeN);
		
		JLabel labDepartG = new JLabel("\u9009\u62E9\u9662\u7CFB");
		labDepartG.setBounds(58, 281, 54, 23);
		contentPanel.add(labDepartG);
		
		JComboBox comboBoxG = new JComboBox();
		comboBoxG.setBounds(132, 281, 131, 23);
		Vector comboG=dAction.countRows();
		for(Object o:comboG)
		{
			comboBoxG.addItem(o);
		}
		contentPanel.add(comboBoxG);
		
		JLabel labPrfsNameG = new JLabel("\u4E13\u4E1A\u540D\u79F0");
		labPrfsNameG.setBounds(61, 346, 54, 21);
		contentPanel.add(labPrfsNameG);
		
		textPrfsNameG = new JTextField();
		textPrfsNameG.setColumns(10);
		textPrfsNameG.setBounds(132, 346, 131, 21);
		contentPanel.add(textPrfsNameG);
		
		JLabel labPrfsCodeG = new JLabel("\u4E13\u4E1A\u4EE3\u7801");
		labPrfsCodeG.setBounds(61, 377, 54, 18);
		contentPanel.add(labPrfsCodeG);
		
		textPrfsCodeG = new JTextField();
		textPrfsCodeG.setColumns(10);
		textPrfsCodeG.setBounds(132, 374, 131, 21);
		contentPanel.add(textPrfsCodeG);
		
		JLabel labelG = new JLabel("\u6DFB\u52A0\u7814\u7A76\u751F\u4E13\u4E1A");
		labelG.setBounds(48, 256, 99, 15);
		contentPanel.add(labelG);
		
		JComboBox comboEducationN = new JComboBox();
		comboEducationN.setBounds(132, 166, 131, 21);
		comboEducationN.addItem("本科");
		comboEducationN.addItem("专科");
		contentPanel.add(comboEducationN);
		
		JLabel lblEducationN = new JLabel("\u5B66\u5386");
		lblEducationN.setBounds(61, 168, 54, 19);
		contentPanel.add(lblEducationN);
		
		JComboBox comboEducationG = new JComboBox();
		comboEducationG.setBounds(132, 314, 131, 21);
		comboEducationG.addItem("硕士");
		comboEducationG.addItem("博士");
		contentPanel.add(comboEducationG);
		
		JLabel labEducationG = new JLabel("\u5B66\u5386");
		labEducationG.setBounds(61, 316, 54, 19);
		contentPanel.add(labEducationG);
		
		JButton buttonAddPrfsN = new JButton("\u6DFB\u52A0");
		buttonAddPrfsN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null,"确认添加","添加专业",JOptionPane.OK_CANCEL_OPTION);
				if(n==0)
				{
				DepartmentBean dBeanN=new DepartmentBean(); 
				dBeanN.setDepartment(comboBoxN.getSelectedItem()+"");
				dBeanN.setProfession(textPrfsNameN.getText());
				dBeanN.setCode(textPrfsCodeN.getText());
				dBeanN.setEducation(comboEducationN.getSelectedItem()+"");
				int num=dAction.addProfessionOne(dBeanN);
				if(num>0)
				{
					JOptionPane.showMessageDialog(null, "添加成功");
				}else {
					JOptionPane.showMessageDialog(null, "添加失败");
				}
				}
			}
		});
		buttonAddPrfsN.setBounds(293, 196, 93, 23);
		contentPanel.add(buttonAddPrfsN);
		
		JButton buttonAddPrfsG = new JButton("\u6DFB\u52A0");
		buttonAddPrfsG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null,"确认添加","添加专业",JOptionPane.OK_CANCEL_OPTION);
				if(n==0)
				{
				DepartmentBean dBeanG=new DepartmentBean(); 
				dBeanG.setDepartment(comboBoxG.getSelectedItem()+"");
				dBeanG.setProfession(textPrfsNameG.getText());
				dBeanG.setCode(textPrfsCodeG.getText());
				dBeanG.setEducation(comboEducationG.getSelectedItem()+"");
				int num=dAction.addProfessionOne(dBeanG);
				if(num>0)
				{
					JOptionPane.showMessageDialog(null, "添加成功");
				}else {
					JOptionPane.showMessageDialog(null, "添加失败");
				}
				}
			}
		});
		buttonAddPrfsG.setBounds(293, 345, 93, 23);
		contentPanel.add(buttonAddPrfsG);
		
		
		contentPanel.setVisible(true);
		setVisible(true);
	}
}

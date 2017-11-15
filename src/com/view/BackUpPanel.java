package com.view;

import javax.swing.JPanel;

import com.tools.DatabaseOperator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class BackUpPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JComboBox comboBox = new JComboBox();
	public BackUpPanel() {
		this.setLayout(null);
		
		JButton btnBackups = new JButton("\u5907\u4EFD");
		btnBackups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username="root";
				String password="yuxuexiao";
				String database="studentmanager";
				Date date=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
				
				String name=sdf.format(date)+".sql";
				String savepath="d:/备份";
				boolean flag=DatabaseOperator.backup(username, password, database, savepath, name);
				if(flag)
				{
					JOptionPane.showMessageDialog(null, "备份成功");
					initItem();
				}else {
					JOptionPane.showMessageDialog(null, "备份失败");
				}
			}
		});
		btnBackups.setBounds(217, 27, 142, 56);
		add(btnBackups);
		
		JButton buttonRestore = new JButton("\u8FD8\u539F");
		buttonRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username="root";
				String password="yuxuexiao";
				String database="studentmanager";
				String savepath="d:/备份";
				String name=comboBox.getSelectedItem()+"";
				boolean flag=DatabaseOperator.recover(username, password, database, savepath, name);
				if(flag)
				{
					JOptionPane.showMessageDialog(null, "还原成功");
				}else {
					JOptionPane.showMessageDialog(null, "还原失败");
				}
			}
		});
		buttonRestore.setBounds(217, 170, 142, 56);
		add(buttonRestore);
		
		
		comboBox.setBounds(39, 182, 149, 33);
		
		add(comboBox);
		initItem();
		
		this.setVisible(true);
		
	}
	
	public void initItem()
	{
		comboBox.removeAllItems();
		File file=new File("d:/备份");
		File files[]=file.listFiles();
		for(File f:files)
		{
			comboBox.addItem(f.getName());
		}
		
	}
}

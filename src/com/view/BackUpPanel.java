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
				String savepath="d:/����";
				boolean flag=DatabaseOperator.backup(username, password, database, savepath, name);
				if(flag)
				{
					JOptionPane.showMessageDialog(null, "���ݳɹ�");
					initItem();
				}else {
					JOptionPane.showMessageDialog(null, "����ʧ��");
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
				String savepath="d:/����";
				String name=comboBox.getSelectedItem()+"";
				boolean flag=DatabaseOperator.recover(username, password, database, savepath, name);
				if(flag)
				{
					JOptionPane.showMessageDialog(null, "��ԭ�ɹ�");
				}else {
					JOptionPane.showMessageDialog(null, "��ԭʧ��");
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
		File file=new File("d:/����");
		File files[]=file.listFiles();
		for(File f:files)
		{
			comboBox.addItem(f.getName());
		}
		
	}
}

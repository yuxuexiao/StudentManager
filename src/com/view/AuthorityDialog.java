package com.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.action.UserKeyAction;
import com.bean.UserKeyBean;
import com.tools.SizeTools;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthorityDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableHave;
	private JTable tableDtHave;
	UserKeyAction uka=new UserKeyAction();
	JScrollPane scrollHave = new JScrollPane();
	JScrollPane scrollDtHave = new JScrollPane();
	
	
	
	UserKeyBean ukbHave=new UserKeyBean();//拥有权限封装对象
	UserKeyBean ukbDtHave=new UserKeyBean();//未拥有权限封装对象

	/**
	 * Create the dialog.
	 */
	public AuthorityDialog(int id) {
		this.setModal(true);
		setBounds(100, 100, 663, 482);
		setSize(663,482);
		SizeTools.dialogSize(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblHave = new JLabel("\u5DF2\u62E5\u6709\u6743\u9650");
		lblHave.setBounds(46, 22, 106, 15);
		contentPanel.add(lblHave);
		
		
		scrollHave.setBounds(56, 48, 539, 161);
		contentPanel.add(scrollHave);
		
		
		
		JLabel labDtHave = new JLabel("\u672A\u62E5\u6709\u6743\u9650");
		labDtHave.setBounds(46, 219, 106, 15);
		contentPanel.add(labDtHave);
		
		
		scrollDtHave.setBounds(56, 244, 544, 161);
		contentPanel.add(scrollDtHave);
		
		
		initHave(id);
		initDtHave(id);
		contentPanel.setVisible(true);
		this.setVisible(true);
	}
	private void initHave(int aid)
	{
		
		 ukbHave=uka.getAthority(aid);
		
		Vector<Vector> data=new Vector<Vector>();
		
		for(Character c:ukbHave.getAuthority())
		{
			if(c.equals('a'))
			{
				Vector v=new Vector();
				v.add(1);
				v.add("查看生源信息");
				v.add("收回权限");
				data.add(v);
			}
			if(c.equals('b'))
			{
				Vector v=new Vector();
				v.add(2);
				v.add("修改生源信息");
				v.add("收回权限");
				data.add(v);
			}
			if(c.equals('c'))
			{
				Vector v=new Vector();
				v.add(3);
				v.add("删除生源信息");
				v.add("收回权限");
				data.add(v);
			}
			if(c.equals('d'))
			{
				Vector v=new Vector();
				v.add(4);
				v.add("导入生源信息");
				v.add("收回权限");
				data.add(v);
			}
			if(c.equals('e'))
			{
				Vector v=new Vector();
				v.add(5);
				v.add("导出生源信息");
				v.add("收回权限");
				data.add(v);
			}
		}
		Vector header=new Vector();
		header.add("序号");
		header.add("权限名");
		header.add("");
		DefaultTableModel dtm=new DefaultTableModel(data,header);
		tableHave = new JTable(dtm);
		tableHave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=tableHave.getSelectedRow();
				int column=tableHave.getSelectedColumn();
				int value=(int)tableHave.getValueAt(row, 0);
				if(column==2)
				{
					
					char aut[]=ukbHave.getAuthority();
					aut[value-1]='o';
					ukbHave.setAuthority(aut);
					int num=uka.updateAuthority(ukbHave);
					if(num>0){
						JOptionPane.showMessageDialog(null, "成功收回权限");
						initHave(aid);
						initDtHave(aid);
					}else {
						JOptionPane.showMessageDialog(null, "收回失败");
					}
				}
			}
		});
		scrollHave.setViewportView(tableHave);
	}
	private void initDtHave(int aid)
	{
		
		ukbDtHave=uka.getAthority(aid);
		Vector<Vector> data=new Vector<Vector>();
		if(ukbDtHave.getAuthority()[0]=='o')
		{
			Vector v=new Vector();
			v.add(1);
			v.add("查看生源信息");
			v.add("授予权限");
			data.add(v);
		}
		if(ukbDtHave.getAuthority()[1]=='o')
		{
			Vector v=new Vector();
			v.add(2);
			v.add("修改生源信息");
			v.add("授予权限");
			data.add(v);
		}
		if(ukbDtHave.getAuthority()[2]=='o')
		{
			Vector v=new Vector();
			v.add(3);
			v.add("删除生源信息");
			v.add("授予权限");
			data.add(v);
		}
		if(ukbDtHave.getAuthority()[3]=='o')
		{
			Vector v=new Vector();
			v.add(4);
			v.add("导入生源信息");
			v.add("授予权限");
			data.add(v);
		}
		if(ukbDtHave.getAuthority()[4]=='o')
		{
			Vector v=new Vector();
			v.add(5);
			v.add("导出生源信息");
			v.add("授予权限");
			data.add(v);
		}
		Vector header=new Vector();
		header.add("序号");
		header.add("权限名");
		header.add("");
		DefaultTableModel dtm=new DefaultTableModel(data,header);
		tableDtHave = new JTable(dtm);
		tableDtHave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=tableDtHave.getSelectedRow();
				int column=tableDtHave.getSelectedColumn();
				int value=(int)tableDtHave.getValueAt(row, 0);
				if(column==2)
				{
					
					char aut[]=ukbDtHave.getAuthority();
					if(value==1)
					{
					aut[value-1]='a';
					}
					if(value==2)
					{
						aut[value-1]='b';
					}
					if(value==3)
					{
						aut[value-1]='c';
					}
					if(value==4)
					{
						aut[value-1]='d';
					}
					if(value==5)
					{
						aut[value-1]='e';
					}
					ukbDtHave.setAuthority(aut);
					int num=uka.updateAuthority(ukbDtHave);
					if(num>0){
						JOptionPane.showMessageDialog(null, "成功授予权限");
						initHave(aid);
						initDtHave(aid);
					}else {
						JOptionPane.showMessageDialog(null, "授予失败");
					}
				}
				
			}
		});
		scrollDtHave.setViewportView(tableDtHave);
	}

}

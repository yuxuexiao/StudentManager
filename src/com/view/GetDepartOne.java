package com.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.action.DepartmentAction;
import com.tools.CheckHeaderCellRenderer;
import com.tools.CheckTableModle;
import com.tools.SizeTools;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GetDepartOne extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	DepartmentAction dAction=new DepartmentAction();
	JScrollPane scrollPane = null;
	int size=0;
	//对应表所选院系对话框
	/**
	 * Create the dialog.
	 * @param NG 
	 */
	public GetDepartOne(String value, String[] NG) {
		this.setModal(true);
		setBounds(100, 100, 513, 342);
		setSize(513,342);
		SizeTools.dialogSize(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	
			JButton button = new JButton("\u6279\u91CF\u5220\u9664");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Vector<Vector> delBatch=new Vector<Vector>();
					for(int i=0;i<size;i++)
					{	
						Vector del=new Vector();
						del.add(table.getValueAt(i, 0));
						del.add(table.getValueAt(i, 1));
						delBatch.add(del);
					}
					int num=JOptionPane.showConfirmDialog(null,"确认删除","批量删除",JOptionPane.OK_CANCEL_OPTION);
					if(num==0) {
						int n=dAction.delBatchProfession(delBatch);
						if(n>0) {
							JOptionPane.showMessageDialog(null, "删除成功");
							initTable(value,NG);
						}else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
					}
				}
			});
			button.setBounds(185, 271, 93, 23);
			contentPanel.add(button);
		
			scrollPane = new JScrollPane();
			scrollPane.setBounds(32, 10, 432, 241);
			contentPanel.add(scrollPane);
			
		initTable(value,NG);
		contentPanel.setVisible(true);
		this.setVisible(true);
	}
	
	private void initTable(String val,String[] ng) {

		Vector<Vector> data = dAction.getDepartOne(val, ng);
		size=data.size();
		System.out.println(size);
		Vector header = new Vector();
		header.add("全选/反选");
		header.add("序号");
		header.add("代码");
		header.add("专业");
		header.add("修改");
		header.add("删除");
		CheckTableModle tableModel = new CheckTableModle(data, header);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				int id = (int) table.getValueAt(row, 1);
				String code = (String) table.getValueAt(row, 2);
				String profession = (String) table.getValueAt(row, 3);
				if (column == 4) {
					int num = JOptionPane.showConfirmDialog(null, "是否确认修改", "修改专业", JOptionPane.OK_CANCEL_OPTION);
					if (num == 0) {
						int n = dAction.updateDepartOne(id, code, profession);
						if (n > 0) {

							JOptionPane.showMessageDialog(null, "修改成功");
							initTable(val, ng);
						} else {
							JOptionPane.showMessageDialog(null, "修改失败");
						}
					}
				}
				if (column == 5) {
					int num = JOptionPane.showConfirmDialog(null, "是否删除专业", "删除专业", JOptionPane.OK_CANCEL_OPTION);
					if (num == 0) {
						int n = dAction.delDepartOne(id);
						if (n > 0) {
							System.out.println(1111111111);
							JOptionPane.showMessageDialog(null, "删除成功");
							initTable(val, ng);

						} else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
					}
				}
			}
		});

		table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
		scrollPane.setViewportView(table);
			
		
	}

}

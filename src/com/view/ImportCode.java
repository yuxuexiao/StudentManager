package com.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.action.CodeAction;
import com.bean.ImportCodeBean;
import com.tools.CheckHeaderCellRenderer;
import com.tools.CheckTableModle;
import com.tools.ExcelExportCollege;
import com.tools.ExcelOperate;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImportCode extends JPanel {
	private JTable CodeTable;
	private JTextField textCode;
	private JTextField textName;

	
	int countPage=0;//总页数	
	int countRows=0;//总行数
	int rowsPage=20;//每页行数
	int currentPage=1;//当前页数
	
	CodeAction cAction=new CodeAction();
	JScrollPane scrollPane = new JScrollPane();
	static ImportCodeBean codeBean=new ImportCodeBean();
	/**
	 * Create the panel.
	 */
	public ImportCode() {
		setLayout(null);
		setVisible(true);
		
		scrollPane.setBounds(61, 59, 485, 277);
		add(scrollPane);
		
		
		
		
		JLabel lblCode = new JLabel("\u4EE3\u7801");
		lblCode.setBounds(51, 27, 54, 22);
		add(lblCode);
		
		textCode = new JTextField();
		textCode.setBounds(115, 28, 114, 21);
		add(textCode);
		textCode.setColumns(10);
		
		JLabel lblName = new JLabel(codeBean.getName());
		lblName.setBounds(256, 27, 66, 22);
		add(lblName);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(332, 28, 114, 21);
		add(textName);
		
		JButton btnOne = new JButton("\u5355\u6761\u6DFB\u52A0");
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null,"是否确认添加","单条添加",JOptionPane.YES_NO_CANCEL_OPTION);
				if(n==0)
				{
				ImportCodeBean b=new ImportCodeBean();
				String code=textCode.getText();
				String name=textName.getText();
				b.setName(name);
				b.setTable(codeBean.getTable());
				b.setCode(code);
				int num=cAction.addCodeOne(b);
				if(num>0)
				{
					JOptionPane.showMessageDialog(null,"添加成功");
				}else {
					JOptionPane.showMessageDialog(null, "添加失败");
				}
				}
			}
		});
		btnOne.setBounds(456, 27, 93, 23);
		add(btnOne);
		
		JButton btnUp = new JButton("\u4E0A\u4E00\u9875");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=1)
				{
				initTable(codeBean,--currentPage);
				}
			}
		});
		btnUp.setBounds(194, 346, 93, 23);
		add(btnUp);
		
		JButton btnDown = new JButton("\u4E0B\u4E00\u9875");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=countPage)
				{
					initTable(codeBean,++currentPage);
				}
			}
		});
		btnDown.setBounds(314, 346, 93, 23);
		add(btnDown);
		
		JButton buttonStart = new JButton("\u9996\u9875");
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=1)
				{
					currentPage=1;
					initTable(codeBean,currentPage);
				}
			}
		});
		buttonStart.setBounds(71, 346, 93, 23);
		add(buttonStart);
		
		JButton buttonLast = new JButton("\u5C3E\u9875");
		buttonLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage!=countPage)
				{
					currentPage=countPage;
					initTable(codeBean,currentPage);
				}
			}
		});
		buttonLast.setBounds(438, 346, 93, 23);
		add(buttonLast);
		
		JButton btnInt = new JButton("\u6279\u91CF\u5BFC\u5165");
		btnInt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();
				int num=jfc.showOpenDialog(null);
				if(num==0)
				{
					File file=jfc.getSelectedFile();
					try {
						String result[][]=ExcelOperate.getData(file, 1);
						boolean flag=cAction.addBatchCode(result,codeBean);
						if(flag)
						{
							JOptionPane.showMessageDialog(null, "导入成功");
							
						}else {
							JOptionPane.showMessageDialog(null, "导入失败");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnInt.setBounds(136, 380, 93, 23);
		add(btnInt);
		
		JButton btnOut = new JButton("\u6279\u91CF\u5BFC\u51FA");
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();
				int num=jfc.showSaveDialog(null);
				if(num==0)
				{
					File file=jfc.getSelectedFile();
					List<String> header=new ArrayList<String>();
				
				List<String[]> colleges=cAction.getCodeAll(codeBean);
				boolean result;
				try {
					result = ExcelExportCollege.exportExcel(header, colleges, file);
					if(result)
					{
						JOptionPane.showMessageDialog(null, "导出成功");
					}else {
						JOptionPane.showMessageDialog(null, "导出失败");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnOut.setBounds(373, 380, 93, 23);
		add(btnOut);
		
		JButton buttonDel = new JButton("\u6279\u91CF\u5220\u9664");
		buttonDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Vector> result=new Vector<Vector>();
				for(int i=0;i<rowsPage;i++)
				{
					Vector rs=new Vector();
					rs.add(CodeTable.getValueAt(i, 0));
					rs.add(CodeTable.getValueAt(i, 1));
					result.add(rs);
				}
				int num=cAction.delBatchCode(result,codeBean);
				if(num>0)
				{
					JOptionPane.showMessageDialog(null, num+"条记录已删除");
					countRows=countRows-num;
					initTable(codeBean, currentPage);
				}else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}
		});
		buttonDel.setBounds(256, 380, 93, 23);
		add(buttonDel);
		
		initTable(codeBean,1);
		setVisible(true);
	}
	private void initTable(ImportCodeBean bean,int currpage)
	{
		countRows=cAction.getCountRows(bean);//总行数
		countPage=countRows%rowsPage>0?countRows/rowsPage+1:countRows/rowsPage;//总页数
		List<Vector> list=cAction.getCodeTable(bean,currpage,rowsPage);
		Vector<Vector> data=new Vector<Vector>();
		for(Vector v:list)
		{
			v.add("修改");
			v.add("删除");
			data.add(v);
		}
		
		Vector<String> header=new Vector<String>();
		header.add("全选/反选");
		header.add("序号");
		header.add("代码");
		header.add(bean.getName());
		header.add("");
		header.add("");
		CheckTableModle tableModle=new CheckTableModle(data,header);
		CodeTable = new JTable(tableModle){  
            @Override  
            public boolean isCellEditable(int row,int column){  
            	if(column==1) {
                return false;  
            	}else {
            		return true;
            	}
            }  
        };  
		
		CodeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row=CodeTable.getSelectedRow();
				int column=CodeTable.getSelectedColumn();
				ImportCodeBean iBean=new ImportCodeBean();
				if(column==4)
				{
					int num=JOptionPane.showConfirmDialog(null,"是否确认修改","修改信息",JOptionPane.OK_CANCEL_OPTION);
					if(num==0)
					{
						iBean.setId((int)CodeTable.getValueAt(row,1));
						iBean.setCode(CodeTable.getValueAt(row, 2)+"");
						iBean.setName(CodeTable.getValueAt(row, 3)+"");
						iBean.setTable(codeBean.getTable());
						int result=cAction.updateCodeOne(iBean);
						if(result>0)
						{
							JOptionPane.showMessageDialog(null, "修改成功");
						}else {
							JOptionPane.showMessageDialog(null, "修改失败");
						}
					}
				}
				if(column==5)
				{
					int num=JOptionPane.showConfirmDialog(null, "是否确认删除","删除信息",JOptionPane.OK_CANCEL_OPTION);
					if(num==0)
					{
						iBean.setId((int)CodeTable.getValueAt(row, 1));
						iBean.setTable(codeBean.getTable());
						int result=cAction.delCodeOne(iBean);
						if(result>0)
						{
							JOptionPane.showMessageDialog(null, "删除成功");
							countRows--;
							initTable(codeBean,currentPage);
							
						}else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
					}
				}
			}
		});
		CodeTable.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(CodeTable));
		scrollPane.setViewportView(CodeTable);
	}
}

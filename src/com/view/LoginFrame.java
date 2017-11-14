package com.view;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import com.action.RoleAction;
import com.action.StudentAction;
import com.action.UserKeyAction;
import com.bean.RoleBean;
import com.bean.UserKeyBean;
import com.tools.SizeTools;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private
	UserKeyAction uka=new UserKeyAction();
	RoleAction rAction=new RoleAction();
	StudentAction sAction=new StudentAction();
	private JTextField textCheckCode;
	private CheckCode check;
	
	public LoginFrame()
	{
		init();
		
	}
	
	
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 349);
		setSize(530,349);
		SizeTools.frameSize(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setVisible(true);
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u5927\u5B66\u751F\u7BA1\u7406\u7CFB\u7EDF");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 21));
		label.setBounds(117, 10, 257, 31);
		contentPane.add(label);
		
		JLabel lblUsername = new JLabel("\u7528\u6237\u540D");
		lblUsername.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblUsername.setBounds(47, 66, 54, 15);
		contentPane.add(lblUsername);
		
		textUsername = new JTextField();
		textUsername.setBounds(100, 63, 132, 21);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel labelPassword = new JLabel("\u5BC6\u7801");
		labelPassword.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		labelPassword.setBounds(59, 108, 35, 15);
		contentPane.add(labelPassword);
		
		JButton button = new JButton("\u767B\u5F55");
		button.setBounds(117, 219, 105, 31);
		button.setBorder(null);
		contentPane.add(button);
		
		getRootPane().setDefaultButton(button);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(100, 105, 132, 21);
		contentPane.add(passwordField);
		
		final JComboBox comboRole = new JComboBox();
		List<RoleBean> list=rAction.getNames();
		for(RoleBean bean:list)
		{
			comboRole.addItem(bean.getName());
		}
		
		
		
		
		comboRole.setBounds(122, 172, 93, 21);
		contentPane.add(comboRole);
		
		JLabel lblCheckCode = new JLabel("\u9A8C\u8BC1\u7801");
		lblCheckCode.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblCheckCode.setBounds(47, 144, 45, 15);
		contentPane.add(lblCheckCode);
		
		textCheckCode = new JTextField();
		textCheckCode.setBounds(100, 141, 132, 21);
		contentPane.add(textCheckCode);
		textCheckCode.setColumns(10);
		
		
		
//		JPanel panel = new JPanel();
//		panel.setBounds(346, 146, 114, 42);
//		contentPane.add(panel);
//		revalidate();
//		repaint();
		
		CheckCode check=new CheckCode();
		check.setBounds(261, 132, 94, 40);
		contentPane.add(check);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/view/i1.png")));
		lblNewLabel.setBounds(271, 10, 275, 280);
		contentPane.add(lblNewLabel);
		revalidate();
		repaint();
		
		
		
		
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserKeyBean bean=new UserKeyBean();
				bean.setUsername(textUsername.getText());
				bean.setPassword(new String(passwordField.getPassword()));
				String role=comboRole.getSelectedItem()+"";
				if(role.equals("学生"))
				{
					bean.setRid(1);
				}
				if(role.equals("辅导员"))
				{
					bean.setRid(2);
				}
				if(role.equals("就业指导中心"))
				{
					bean.setRid(3);
				}
				if(role.equals("管理员"))
				{
					bean.setRid(4);
				}
				StudentMainFrame.uBean=bean;//将登陆信息传到主界面
				
				//判断是否为学生
//				
//				int rid=rAction.getRid(role);//获取id
//				int ridStudent=sAction.geStudentOne();
				//可控制权限封装
			
				
				
				
				if (check.getCode().equalsIgnoreCase(textCheckCode.getText())) {
					if (uka.userKey(bean) != null || (sAction.studentLogin(bean) != null && bean.getRid() == 1)) {
						SelectStudent.autBean = uka.getControlAut(bean);
						if (bean.getRid() == 1)// 判断学生是否需要修改密码和问卷调查;
						{
							int question = sAction.getQuestion(bean.getUsername());
							if (question == 1) {// 弹出修改密码
								dispose();
								LgoinPass loginP = new LgoinPass(bean.getUsername(), bean.getPassword(), 1);
								return;
							}
							if (question == 2) {
								dispose();
								LoginQuestion loginQ=new LoginQuestion(bean.getUsername());
								return;
								// 弹出调查问卷
							} else {
								JOptionPane.showMessageDialog(null, "登录成功");
								StudentMainFrame smf = new StudentMainFrame();
								smf.setVisible(true);
								dispose();
							}
						} else {

							JOptionPane.showMessageDialog(null, "登录成功");
							StudentMainFrame smf = new StudentMainFrame();
							smf.setVisible(true);
							dispose();
						}
					} else {
						JOptionPane.showMessageDialog(null, "账号或密码错误");
					}
				} else {

					JOptionPane.showMessageDialog(null, "验证码错误");
				}
			}
		});
		
		
		
		
		
	}
}

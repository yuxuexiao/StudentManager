package com.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.action.AuditAction;
import com.action.StudentAction;
import com.bean.StudentBean;
import com.tools.SendMsg_webchinese;
import com.tools.SizeTools;

import javax.swing.JComboBox;

public class AuditTeacherCenterDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	StudentAction sAction=new StudentAction();
	AuditAction aAction=new AuditAction();
	
	private JTextField textCandId;
	private JTextField textName;
	private JTextField textSex;
	private JTextField textSid;
	private JTextField textNation;
	private JTextField textPoliticStatu;
	private JTextField textEducatin;
	private JTextField textProfession;
	private JTextField textTrainingMode;
	private JTextField textAddress;
	private JTextField textBirthday;
	private JTextField textIdCard;
	private JTextField textEnrollment;
	private JTextField textEducationSystem;
	private JTextField textDirectional;
	private JTextField textLanguage;
	private JTextField textStatusChanges;
	private JTextField textGraduation;
	private JTextField textContactWay;
	private JTextField textEmail;
	private JTextField textAcSchool;
	private JTextField textEnglish;
	private JTextField textDepartment;
	private JTextField textGrade;
	/**
	 * Create the dialog.
	 * @param stuTableName 
	 */
	public AuditTeacherCenterDialog(int rid,String stuTableName,String values) {
		setModal(true);
		StudentBean sBean=new StudentBean();
		StudentBean bean=sAction.getStudengById(stuTableName,values);
//		
		
//		setBounds(100, 100, 772, 700);
		setSize(772,700);
		SizeTools.dialogSize(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel labCandId = new JLabel("\u8003\u751F\u53F7");
		labCandId.setBounds(51, 10, 45, 22);
		contentPanel.add(labCandId);
		
		textCandId = new JTextField();
		textCandId.setBounds(149, 11, 118, 21);
		textCandId.setText(bean.getCandid());
		textCandId.setEditable(false);
		contentPanel.add(textCandId);
		textCandId.setColumns(10);
		
		JLabel labName = new JLabel("\u59D3\u540D");
		labName.setBounds(51, 42, 45, 22);
		contentPanel.add(labName);
		
		textName = new JTextField();
		textName.setEditable(false);
		textName.setColumns(10);
		textName.setBounds(149, 43, 118, 21);
		textName.setText(bean.getName());
		contentPanel.add(textName);
		
		JLabel labSex = new JLabel("\u6027\u522B");
		labSex.setBounds(51, 74, 45, 22);
		contentPanel.add(labSex);
		
		textSex = new JTextField();
		textSex.setEditable(false);
		textSex.setColumns(10);
		textSex.setBounds(149, 74, 118, 21);
		textSex.setText(bean.getSex());
		contentPanel.add(textSex);
		
		JLabel labSid = new JLabel("\u5B66\u53F7");
		labSid.setBounds(51, 106, 45, 22);
		contentPanel.add(labSid);
		
		textSid = new JTextField();
		textSid.setEditable(false);
		textSid.setColumns(10);
		textSid.setBounds(149, 106, 118, 21);
		textSid.setText(bean.getSid());
		contentPanel.add(textSid);
		
		JLabel labNation = new JLabel("\u6C11\u65CF");
		labNation.setBounds(51, 138, 45, 22);
		contentPanel.add(labNation);
		
		textNation = new JTextField();
		textNation.setEditable(false);
		textNation.setColumns(10);
		textNation.setBounds(149, 138, 118, 21);
		textNation.setText(bean.getNation());
		contentPanel.add(textNation);
		
		JLabel labPolitiStatu = new JLabel("\u653F\u6CBB\u9762\u8C8C");
		labPolitiStatu.setBounds(51, 170, 64, 22);
		contentPanel.add(labPolitiStatu);
		
		textPoliticStatu = new JTextField();
		textPoliticStatu.setEditable(false);
		textPoliticStatu.setColumns(10);
		textPoliticStatu.setBounds(149, 170, 118, 21);
		textPoliticStatu.setText(bean.getPoliticstatu());
		contentPanel.add(textPoliticStatu);
		
		JLabel labEducation = new JLabel("\u5B66\u5386");
		labEducation.setBounds(51, 202, 45, 22);
		contentPanel.add(labEducation);
		
		textEducatin = new JTextField();
		textEducatin.setEditable(false);
		textEducatin.setColumns(10);
		textEducatin.setBounds(149, 202, 118, 21);
		textEducatin.setText(bean.getEducation());
		contentPanel.add(textEducatin);
		
		JLabel labProfession = new JLabel("\u4E13\u4E1A");
		labProfession.setBounds(51, 234, 45, 22);
		contentPanel.add(labProfession);
		
		textProfession = new JTextField();
		textProfession.setEditable(false);
		textProfession.setColumns(10);
		textProfession.setBounds(149, 234, 118, 21);
		textProfession.setText(bean.getProfession());
		contentPanel.add(textProfession);
		
		JLabel labTrainingMode = new JLabel("\u57F9\u517B\u65B9\u5F0F");
		labTrainingMode.setBounds(51, 266, 64, 22);
		contentPanel.add(labTrainingMode);
		
		textTrainingMode = new JTextField();
		textTrainingMode.setEditable(false);
		textTrainingMode.setColumns(10);
		textTrainingMode.setBounds(149, 266, 118, 21);
		textTrainingMode.setText(bean.getTrainingmode());
		contentPanel.add(textTrainingMode);
		
		JLabel labAddress = new JLabel("\u751F\u6E90\u5730");
		labAddress.setBounds(51, 298, 45, 22);
		contentPanel.add(labAddress);
		
		textAddress = new JTextField();
		textAddress.setEditable(false);
		textAddress.setColumns(10);
		textAddress.setBounds(149, 298, 118, 21);
		textAddress.setText(bean.getAddress());
		contentPanel.add(textAddress);
		
		JLabel labBirthday = new JLabel("\u51FA\u751F\u65E5\u671F");
		labBirthday.setBounds(51, 330, 64, 22);
		contentPanel.add(labBirthday);
		
		textBirthday = new JTextField();
		textBirthday.setEditable(false);
		textBirthday.setColumns(10);
		textBirthday.setBounds(149, 330, 118, 21);
		textBirthday.setText(bean.getBirthday());
		contentPanel.add(textBirthday);
		
		JLabel labIdCard = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801");
		labIdCard.setBounds(51, 362, 88, 22);
		contentPanel.add(labIdCard);
		
		textIdCard = new JTextField();
		textIdCard.setEditable(false);
		textIdCard.setColumns(10);
		textIdCard.setBounds(149, 362, 118, 21);
		textIdCard.setText(bean.getIdcard());
		contentPanel.add(textIdCard);
		
		JLabel labEnrollment = new JLabel("\u5165\u5B66\u65F6\u95F4");
		labEnrollment.setBounds(340, 234, 77, 22);
		contentPanel.add(labEnrollment);
		
		textEnrollment = new JTextField();
		textEnrollment.setEditable(false);
		textEnrollment.setColumns(10);
		textEnrollment.setBounds(441, 235, 118, 21);
		textEnrollment.setText(bean.getEnrollment());
		contentPanel.add(textEnrollment);
		
		JLabel labEducationSystem = new JLabel("\u5B66\u5236");
		labEducationSystem.setBounds(340, 10, 45, 22);
		contentPanel.add(labEducationSystem);
		
		textEducationSystem = new JTextField();
		textEducationSystem.setEditable(false);
		textEducationSystem.setColumns(10);
		textEducationSystem.setBounds(441, 11, 118, 21);
		textEducationSystem.setText(bean.getEducationsystem());
		contentPanel.add(textEducationSystem);
		
		JLabel labDirectional = new JLabel("\u5B9A\u5411\u6216\u59D4\u57F9");
		labDirectional.setBounds(340, 42, 77, 22);
		contentPanel.add(labDirectional);
		
		textDirectional = new JTextField();
		textDirectional.setEditable(false);
		textDirectional.setColumns(10);
		textDirectional.setBounds(441, 43, 118, 21);
		textDirectional.setText(bean.getDirectional());
		contentPanel.add(textDirectional);
		
		JLabel labLanguage = new JLabel("\u4E3B\u4FEE\u5916\u8BED");
		labLanguage.setBounds(340, 74, 77, 22);
		contentPanel.add(labLanguage);
		
		textLanguage = new JTextField();
		textLanguage.setEditable(false);
		textLanguage.setColumns(10);
		textLanguage.setBounds(441, 75, 118, 21);
		textLanguage.setText(bean.getLanguage());
		contentPanel.add(textLanguage);
		
		JLabel labStatusChanges = new JLabel("\u5B66\u7C4D\u53D8\u52A8");
		labStatusChanges.setBounds(340, 106, 58, 22);
		contentPanel.add(labStatusChanges);
		
		textStatusChanges = new JTextField();
		textStatusChanges.setEditable(false);
		textStatusChanges.setColumns(10);
		textStatusChanges.setBounds(441, 107, 118, 21);
		textStatusChanges.setText(bean.getStatuschanges());
		contentPanel.add(textStatusChanges);
		
		JLabel labGraduation = new JLabel("\u6BD5\u4E1A\u65F6\u95F4");
		labGraduation.setBounds(340, 266, 58, 22);
		contentPanel.add(labGraduation);
		
		textGraduation = new JTextField();
		textGraduation.setEditable(false);
		textGraduation.setColumns(10);
		textGraduation.setBounds(441, 267, 118, 21);
		textGraduation.setText(bean.getGraduation());
		contentPanel.add(textGraduation);
		
		JLabel labContactWay = new JLabel("\u8054\u7CFB\u65B9\u5F0F");
		labContactWay.setBounds(340, 138, 58, 22);
		contentPanel.add(labContactWay);
		
		textContactWay = new JTextField();
		textContactWay.setEditable(false);
		textContactWay.setColumns(10);
		textContactWay.setBounds(441, 139, 118, 21);
		textContactWay.setText(bean.getContactway());
		contentPanel.add(textContactWay);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(340, 170, 45, 22);
		contentPanel.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setEditable(false);
		textEmail.setColumns(10);
		textEmail.setBounds(441, 171, 118, 21);
		textEmail.setText(bean.getEmail());
		contentPanel.add(textEmail);
		
		JLabel labAtSchool = new JLabel("\u6237\u53E3\u662F\u5426\u5728\u6821");
		labAtSchool.setBounds(330, 202, 87, 22);
		contentPanel.add(labAtSchool);
		
		textAcSchool = new JTextField();
		textAcSchool.setEditable(false);
		textAcSchool.setColumns(10);
		textAcSchool.setBounds(441, 203, 118, 21);
		textAcSchool.setText(bean.getAtschool());
		contentPanel.add(textAcSchool);
		
		JLabel labEnglish = new JLabel("\u82F1\u8BED\u7B49\u7EA7");
		labEnglish.setBounds(340, 298, 58, 22);
		contentPanel.add(labEnglish);
		
		textEnglish = new JTextField();
		textEnglish.setEditable(false);
		textEnglish.setColumns(10);
		textEnglish.setBounds(441, 299, 118, 21);
		textEnglish.setText(bean.getEnglish());
		contentPanel.add(textEnglish);
		
		JLabel labRewardorPunishment = new JLabel("\u5B66\u6821\u5956\u60E9\u8BB0\u5F55");
		labRewardorPunishment.setBounds(39, 426, 98, 22);
		contentPanel.add(labRewardorPunishment);
		
		JLabel labDepartment = new JLabel("\u9662\u7CFB");
		labDepartment.setBounds(340, 330, 45, 22);
		contentPanel.add(labDepartment);
		
		textDepartment = new JTextField();
		textDepartment.setEditable(false);
		textDepartment.setColumns(10);
		textDepartment.setBounds(441, 331, 118, 21);
		textDepartment.setText(bean.getDepartment());
		contentPanel.add(textDepartment);
		
		JLabel labGrade = new JLabel("\u5E74\u7EA7");
		labGrade.setBounds(340, 362, 45, 22);
		contentPanel.add(labGrade);
		
		textGrade = new JTextField();
		textGrade.setEditable(false);
		textGrade.setColumns(10);
		textGrade.setBounds(441, 363, 118, 21);
		textGrade.setText(bean.getGrade());
		contentPanel.add(textGrade);
		
		JTextArea textAreaRewardorPunishment = new JTextArea();
		textAreaRewardorPunishment.setEditable(false);
		textAreaRewardorPunishment.setBounds(148, 426, 536, 78);
		textAreaRewardorPunishment.setText(bean.getRewardpunishment());
		contentPanel.add(textAreaRewardorPunishment);
		
		JLabel labNotPassReason = new JLabel("\u5BA1\u6838\u672A\u901A\u8FC7\u539F\u56E0");
		labNotPassReason.setBounds(39, 516, 98, 15);
		contentPanel.add(labNotPassReason);
		
		JTextArea textNotPassReason = new JTextArea();
		textNotPassReason.setBounds(148, 514, 536, 78);
		textNotPassReason.setText(bean.getReason());
		contentPanel.add(textNotPassReason);
		
		JComboBox comboAudit = new JComboBox();
		comboAudit.setBounds(649, 318, 77, 21);
		comboAudit.addItem("不通过");
		comboAudit.addItem("通过");
		contentPanel.add(comboAudit);
		
		JLabel labAudit = new JLabel("\u5BA1\u6838");
		labAudit.setBounds(592, 321, 45, 15);
		contentPanel.add(labAudit);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String phone=textContactWay.getText();
						
						String reason="";
						int audit=0;
						if(rid==2)
						{
//						String tempStudent[]=aAction.tempStudent(values,reason,1);//通过学号获取学生信息存入数组
						if("不通过".equals(comboAudit.getSelectedItem()))
						{
							audit=1;
//							tempStudent[34]=String.valueOf(1);
//							tempStudent[37]=reason;
							reason=textNotPassReason.getText();
							try {
								SendMsg_webchinese.SendMessage(phone,reason);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
						
								e1.printStackTrace();
							}
						}
						if("通过".equals(comboAudit.getSelectedItem()))
						{
							audit=3;
							reason="";
						}
						System.out.println(audit);
						int num=aAction.updateStudentTeacher(values,reason,audit);//审核
						if(num>0)
						{
							
							JOptionPane.showMessageDialog(null, "审核成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "审核失败");
						}
					}
						if(rid==3)
						{
//							String tempStudent[]=aAction.tempStudent(values);//通过学号获取学生信息存入数组
							if("不通过".equals(comboAudit.getSelectedItem()))
							{
								
//								tempStudent[34]=String.valueOf(1);
//								tempStudent[37]=reason;
								reason=textNotPassReason.getText();
								audit=1;
								try {
									SendMsg_webchinese.SendMessage(phone,reason);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									
									e1.printStackTrace();
								}
							}
							if("通过".equals(comboAudit.getSelectedItem()))
							{
								
//								tempStudent[34]=String.valueOf(4);
//								tempStudent[37]="";
								audit=4;
								reason="";
							}
							
							int num=aAction.updateStudentTeacher(values,reason,audit);//删除学生后通过上面获取的数组重新插入学生
							
							
							if(num>0)
							{
								
								JOptionPane.showMessageDialog(null, "审核成功");
								dispose();
							}else {
								JOptionPane.showMessageDialog(null, "审核失败");
							}
						}
					}
				});
				okButton.setActionCommand("确认信息");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
		contentPanel.setVisible(true);
		
	}

}

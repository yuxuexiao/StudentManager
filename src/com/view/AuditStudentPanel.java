package com.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.action.AuditAction;
import com.bean.StudentBean;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuditStudentPanel extends JPanel {
	private JTextField textCandId;
	private JTextField textEducationSystem;
	private JTextField textDirection;
	private JTextField textName;
	private JTextField textSex;
	private JTextField textLanguage;
	private JTextField textStatusChange;
	private JTextField textSid;
	private JTextField textNation;
	private JTextField textContactWay;
	private JTextField textEmail;
	private JTextField textPoliticStatus;
	private JTextField textEducation;
	private JTextField textAtSchool;
	private JTextField textEnrollment;
	private JTextField textProfession;
	private JTextField textTrainingMode;
	private JTextField textGraduation;
	private JTextField textEnglish;
	private JTextField textAddress;
	private JTextField textBirthDay;
	private JTextField textDepartment;
	private JTextField textGrade;
	private JTextField textIdCard;
	AuditAction aAction=new AuditAction();
	StudentBean sBean=new StudentBean();
	private JTextField textAuditResult;
	/**
	 * Create the panel.
	 */
	public AuditStudentPanel() {
		this.setLayout(null);
		
		sBean = aAction.getStudentByKey(StudentMainFrame.uBean);
		
		JLabel labCandId = new JLabel("\u8003\u751F\u53F7");
		labCandId.setBounds(54, 10, 45, 22);
		add(labCandId);
		
		textCandId = new JTextField();
		textCandId.setText((String) null);
		textCandId.setEditable(false);
		textCandId.setColumns(10);
		textCandId.setBounds(152, 11, 118, 21);
		textCandId.setText(sBean.getCandid());
		add(textCandId);
		
		JLabel labEducationSystem = new JLabel("\u5B66\u5236");
		labEducationSystem.setBounds(343, 10, 45, 22);
		add(labEducationSystem);
		
		textEducationSystem = new JTextField();
		textEducationSystem.setText((String) null);
		textEducationSystem.setEditable(false);
		textEducationSystem.setColumns(10);
		textEducationSystem.setBounds(444, 11, 118, 21);
		textEducationSystem.setText(sBean.getEducationsystem());
		 add(textEducationSystem);
		
		textDirection = new JTextField();
		textDirection.setText((String) null);
		textDirection.setEditable(false);
		textDirection.setColumns(10);
		textDirection.setBounds(444, 43, 118, 21);
		textDirection.setText(sBean.getDirectional());
		add(textDirection);
		
		JLabel labDirection = new JLabel("\u5B9A\u5411\u6216\u59D4\u57F9");
		labDirection.setBounds(343, 42, 77, 22);
		add(labDirection);
		
		textName = new JTextField();
		textName.setText((String) null);
		textName.setEditable(false);
		textName.setColumns(10);
		textName.setBounds(152, 43, 118, 21);
		textName.setText(sBean.getName());
		add(textName);
		
		JLabel labName = new JLabel("\u59D3\u540D");
		labName.setBounds(54, 42, 45, 22);
		add(labName);
		
		JLabel labSex = new JLabel("\u6027\u522B");
		labSex.setBounds(54, 74, 45, 22);
		add(labSex);
		
		textSex = new JTextField();
		textSex.setText((String) null);
		textSex.setEditable(false);
		textSex.setColumns(10);
		textSex.setBounds(152, 74, 118, 21);
		textSex.setText(sBean.getSex());
		add(textSex);
		
		JLabel labLanguage = new JLabel("\u4E3B\u4FEE\u5916\u8BED");
		labLanguage.setBounds(343, 74, 77, 22);
		add(labLanguage);
		
		textLanguage = new JTextField();
		textLanguage.setText((String) null);
		textLanguage.setEditable(false);
		textLanguage.setColumns(10);
		textLanguage.setBounds(444, 75, 118, 21);
		textLanguage.setText(sBean.getLanguage());
		add(textLanguage);
		
		textStatusChange = new JTextField();
		textStatusChange.setText((String) null);
		textStatusChange.setEditable(false);
		textStatusChange.setColumns(10);
		textStatusChange.setBounds(444, 107, 118, 21);
		textStatusChange.setText(sBean.getStatuschanges());
		add(textStatusChange);
		
		JLabel labStatusChange = new JLabel("\u5B66\u7C4D\u53D8\u52A8");
		labStatusChange.setBounds(343, 106, 58, 22);
		add(labStatusChange);
		
		textSid = new JTextField();
		textSid.setText((String) null);
		textSid.setEditable(false);
		textSid.setColumns(10);
		textSid.setBounds(152, 106, 118, 21);
		textSid.setText(sBean.getSid());
		add(textSid);
		
		JLabel labSid = new JLabel("\u5B66\u53F7");
		labSid.setBounds(54, 106, 45, 22);
		add(labSid);
		
		JLabel labNation = new JLabel("\u6C11\u65CF");
		labNation.setBounds(54, 138, 45, 22);
		add(labNation);
		
		textNation = new JTextField();
		textNation.setText((String) null);
		textNation.setEditable(false);
		textNation.setColumns(10);
		textNation.setBounds(152, 138, 118, 21);
		textNation.setText(sBean.getNation());
		add(textNation);
		
		JLabel labContactWay = new JLabel("\u8054\u7CFB\u65B9\u5F0F");
		labContactWay.setBounds(343, 138, 58, 22);
		add(labContactWay);
		
		textContactWay = new JTextField();
		textContactWay.setText((String) null);
		textContactWay.setColumns(10);
		textContactWay.setBounds(444, 139, 118, 21);
		textContactWay.setText(sBean.getContactway());
		if(sBean.getAudit()>=2)
		{
			textContactWay.setEditable(false);
		}
		add(textContactWay);
		
		textEmail = new JTextField();
		textEmail.setText((String) null);
		textEmail.setColumns(10);
		textEmail.setBounds(444, 171, 118, 21);
		textEmail.setText(sBean.getEmail());
		if(sBean.getAudit()>=2)
		{
			textEmail.setEditable(false);
		}
		add(textEmail);
		
		JLabel labEmail = new JLabel("E-mail");
		labEmail.setBounds(343, 170, 45, 22);
		add(labEmail);
		
		textPoliticStatus = new JTextField();
		textPoliticStatus.setText((String) null);
		textPoliticStatus.setEditable(false);
		textPoliticStatus.setColumns(10);
		textPoliticStatus.setBounds(152, 170, 118, 21);
		textPoliticStatus.setText(sBean.getPoliticstatu());
		add(textPoliticStatus);
		
		JLabel labPoliticStatus = new JLabel("\u653F\u6CBB\u9762\u8C8C");
		labPoliticStatus.setBounds(54, 170, 64, 22);
		add(labPoliticStatus);
		
		JLabel labEducation = new JLabel("\u5B66\u5386");
		labEducation.setBounds(54, 202, 45, 22);
		add(labEducation);
		
		textEducation = new JTextField();
		textEducation.setText((String) null);
		textEducation.setEditable(false);
		textEducation.setColumns(10);
		textEducation.setBounds(152, 202, 118, 21);
		textEducation.setText(sBean.getEducation());
		add(textEducation);
		
		JLabel labAtSchool = new JLabel("\u6237\u53E3\u662F\u5426\u5728\u6821");
		labAtSchool.setBounds(333, 202, 87, 22);
		add(labAtSchool);
		
		textAtSchool = new JTextField();
		textAtSchool.setText((String) null);
		textAtSchool.setEditable(false);
		textAtSchool.setColumns(10);
		textAtSchool.setBounds(444, 203, 118, 21);
		textAtSchool.setText(sBean.getAtschool());
		add(textAtSchool);
		
		textEnrollment = new JTextField();
		textEnrollment.setText((String) null);
		textEnrollment.setEditable(false);
		textEnrollment.setColumns(10);
		textEnrollment.setBounds(444, 235, 118, 21);
		textEnrollment.setText(sBean.getEnrollment());
		add(textEnrollment);
		
		JLabel labEnrollment = new JLabel("\u5165\u5B66\u65F6\u95F4");
		labEnrollment.setBounds(343, 234, 77, 22);
		add(labEnrollment);
		
		textProfession = new JTextField();
		textProfession.setText((String) null);
		textProfession.setEditable(false);
		textProfession.setColumns(10);
		textProfession.setBounds(152, 234, 118, 21);
		textProfession.setText(sBean.getProfession());
		add(textProfession);
		
		JLabel labProfession = new JLabel("\u4E13\u4E1A");
		labProfession.setBounds(54, 234, 45, 22);
		add(labProfession);
		
		JLabel labTrainingMode = new JLabel("\u57F9\u517B\u65B9\u5F0F");
		labTrainingMode.setBounds(54, 266, 64, 22);
		add(labTrainingMode);
		
		textTrainingMode = new JTextField();
		textTrainingMode.setText((String) null);
		textTrainingMode.setEditable(false);
		textTrainingMode.setColumns(10);
		textTrainingMode.setBounds(152, 266, 118, 21);
		textTrainingMode.setText(sBean.getTrainingmode());
		add(textTrainingMode);
		
		JLabel labGraduation = new JLabel("\u6BD5\u4E1A\u65F6\u95F4");
		labGraduation.setBounds(343, 266, 58, 22);
		add(labGraduation);
		
		textGraduation = new JTextField();
		textGraduation.setText((String) null);
		textGraduation.setEditable(false);
		textGraduation.setColumns(10);
		textGraduation.setBounds(444, 267, 118, 21);
		textGraduation.setText(sBean.getGraduation());
		add(textGraduation);
		
		textEnglish = new JTextField();
		textEnglish.setText((String) null);
		textEnglish.setColumns(10);
		textEnglish.setBounds(444, 299, 118, 21);
		textEnglish.setText(sBean.getEnglish());
		if(sBean.getAudit()>=2)
		{
			textEnglish.setEditable(false);
		}
		add(textEnglish);
		
		JLabel labEnglish = new JLabel("\u82F1\u8BED\u7B49\u7EA7");
		labEnglish.setBounds(343, 298, 58, 22);
		add(labEnglish);
		
		textAddress = new JTextField();
		textAddress.setText((String) null);
		textAddress.setColumns(10);
		textAddress.setBounds(152, 298, 118, 21);
		textAddress.setText(sBean.getAddress());
		if(sBean.getAudit()>=2)
		{
			textAddress.setEditable(false);
		}
		add(textAddress);
		
		JLabel labAddress = new JLabel("\u751F\u6E90\u5730");
		labAddress.setBounds(54, 298, 45, 22);
		add(labAddress);
		
		JLabel labBirthday = new JLabel("\u51FA\u751F\u65E5\u671F");
		labBirthday.setBounds(54, 330, 64, 22);
		add(labBirthday);
		
		textBirthDay = new JTextField();
		textBirthDay.setText((String) null);
		textBirthDay.setEditable(false);
		textBirthDay.setColumns(10);
		textBirthDay.setBounds(152, 330, 118, 21);
		textBirthDay.setText(sBean.getBirthday());
		add(textBirthDay);
		
		JLabel labDepartment = new JLabel("\u9662\u7CFB");
		labDepartment.setBounds(343, 330, 45, 22);
		add(labDepartment);
		
		textDepartment = new JTextField();
		textDepartment.setText((String) null);
		textDepartment.setEditable(false);
		textDepartment.setColumns(10);
		textDepartment.setBounds(444, 331, 118, 21);
		textDepartment.setText(sBean.getDepartment());
		add(textDepartment);
		
		textGrade = new JTextField();
		textGrade.setText((String) null);
		textGrade.setEditable(false);
		textGrade.setColumns(10);
		textGrade.setBounds(444, 363, 118, 21);
		textGrade.setText(sBean.getGrade());
		add(textGrade);
		
		JLabel labGrade = new JLabel("\u5E74\u7EA7");
		labGrade.setBounds(343, 362, 45, 22);
		add(labGrade);
		
		textIdCard = new JTextField();
		textIdCard.setText((String) null);
		textIdCard.setColumns(10);
		textIdCard.setBounds(152, 362, 118, 21);
		textIdCard.setText(sBean.getIdcard());
		if(sBean.getAudit()>=2)
		{
			textIdCard.setEditable(false);
		}
		add(textIdCard);
		
		JLabel labIdCard = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801");
		labIdCard.setBounds(54, 362, 77, 22);
		add(labIdCard);
		
		JLabel labRewardPunishment = new JLabel("\u5B66\u6821\u5956\u60E9\u8BB0\u5F55");
		labRewardPunishment.setBounds(56, 425, 98, 22);
		add(labRewardPunishment);
		
		JTextArea textRewardPunishment = new JTextArea();
		textRewardPunishment.setText((String) null);
		textRewardPunishment.setBounds(165, 425, 536, 52);
		textRewardPunishment.setText(sBean.getRewardpunishment());
		if(sBean.getAudit()>=2)
		{
			textRewardPunishment.setEditable(false);
		}
		add(textRewardPunishment);
		
		JLabel labAuditFailedReason = new JLabel("\u5BA1\u6838\u672A\u901A\u8FC7\u539F\u56E0");
		labAuditFailedReason.setBounds(56, 489, 98, 15);
		add(labAuditFailedReason);
		
		JTextArea textAuditFailedReason = new JTextArea();
		textAuditFailedReason.setEditable(false);
		textAuditFailedReason.setText(sBean.getReason());
		textAuditFailedReason.setBounds(165, 487, 536, 52);
		//未完待续
		add(textAuditFailedReason);
		
		JButton btnAlter = new JButton("确认");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentBean selfBean=new StudentBean();
				selfBean.setSid(StudentMainFrame.uBean.getUsername());
				selfBean.setPassword(StudentMainFrame.uBean.getPassword());
				selfBean.setAddress(textAddress.getText());
				selfBean.setIdcard(textIdCard.getText());
				selfBean.setContactway(textContactWay.getText());
				selfBean.setEmail(textEmail.getText());
				selfBean.setEnglish(textEnglish.getText());
				selfBean.setRewardpunishment(textRewardPunishment.getText());
				selfBean.setAudit(2);
				int num=aAction.updateStudentOwn(selfBean);
				if(num>0)
				{
					JOptionPane.showMessageDialog(null, "修改成功");
					validate();
					repaint();
					
				}else {
					JOptionPane.showMessageDialog(null, "修改失败");				
				}
			}
		});
		btnAlter.setBounds(608, 241, 93, 35);
		if(sBean.getAudit()<2)
		{
			add(btnAlter);
		}
		
		
		JLabel labAuditResult = new JLabel("\u5BA1\u6838\u72B6\u6001");
		labAuditResult.setBounds(54, 393, 64, 22);
		add(labAuditResult);
		
		textAuditResult = new JTextField();
		textAuditResult.setEditable(false);
		textAuditResult.setText((String) null);
		textAuditResult.setColumns(10);
		textAuditResult.setBounds(152, 393, 153, 21);
		if(sBean.getAudit()==1)
		{
			textAuditResult.setText("等待确认信息");
		}
		if(sBean.getAudit()==2)
		{
			textAuditResult.setText("等待辅导员审核");
		}if(sBean.getAudit()==3)
		{
			textAuditResult.setText("等待就业指导中心审核");
		}if(sBean.getAudit()==4)
		{
			textAuditResult.setText("已通过");
		}
		add(textAuditResult);
	}
}

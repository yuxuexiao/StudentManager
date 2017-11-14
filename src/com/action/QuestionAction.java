package com.action;

import java.util.Vector;

import com.bean.QuestionBean;
import com.dao.QuestionDao;

public class QuestionAction {
	QuestionDao qDao=new QuestionDao();
	public int addQuestionOne(String question) {
		
		return qDao.addQuestionOne(question);
	}
	public Vector<QuestionBean> getAllQuestion() {
		return qDao.getAllQuestion();
	}
	public int getIdByQuestion(String question) {
		return qDao.getIdByQuestion(question);
	}
	public int addAnswer(String answer) {
		
		return qDao.addAnswer(answer);
	}
	public int getIdByAnswer(String answer) {
		return qDao.getIdByAnswer(answer);
	}
	public int relevance(int qid, int aid) {
		return qDao.relevance(qid,aid);
	}
	public Vector<Integer> getAidByQuestion(String question) {
		return qDao.getAidByQuestion(question);
	}
	public Vector<Vector> getAnswerByAid(Vector<Integer> aid) {
		return qDao.getAnswerByAid(aid);
	}
	public int delQuestion(int value) {
		return qDao.delQuestion(value);
	}
	public int updateQuestion(int value, String question) {
		return qDao.updateQuestion(value,question);
	}
	public int updateAnswer(int id, String answer) {
		return qDao.updateAnswer(id,answer);
	}
	public int delAnswer(int id) {
		return qDao.delAnswer(id);
	}
	public Vector<Vector> getIsNotAction(int isnot) {
		return qDao.getIsNotAction(isnot);
	}
	public int updateOpen(int open, int value) {
		return qDao.updateOpen(open,value);
	}
	public Vector<QuestionBean> getAllOpenQuestion() {
		return qDao.getAllOpenQuestion();
	}
	public int updateAnswerTime(int value) {
		return qDao.updateAnswerTime(value);
	}
	public int getCountTimeByAid(Vector<Integer> aid) {
		return qDao.getCountTimeByAid(aid);
	}
	public Vector<Vector> getTimeByAid(Vector<Integer> aid, int countTimes) {
		return qDao.getTimeByAid(aid,countTimes);
	}
	

}

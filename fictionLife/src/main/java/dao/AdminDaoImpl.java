package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import condition.PagingCondition;
import model.Admin;
import model.Notice_board;
import model.Report_novel;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SqlSession session;
	
	public void userBlind(String nickname) {
		session.update("mapper.adminMapper.userBlind", nickname);

	}

	public void novelBlind(Integer id) {
		session.update("mapper.adminMapper.novelBlind", id);

	}
	public List<Report_novel> getReport(PagingCondition pc) {
		
		return session.selectList("mapper.adminMapper.getReport", pc);
	}
	public Integer getCount() {
		
		return session.selectOne("mapper.adminMapper.countReport");
	}

	public void userBlind2(String nickname) {
		session.update("mapper.adminMapper.userBlind2", nickname);

	}

	public void novelBlind2(Integer id) {
		session.update("mapper.adminMapper.novelBlind2", id);

	}
	
	public Integer maxNoticeBno() {
	
		return session.selectOne("mapper.adminMapper.maxNoticeBno");
	}
	
	public void insertNotice(Notice_board nb) {
	session.insert("mapper.adminMapper.insertNotice", nb);
	}
	
	public Admin getAdminByEmail(String email) {
		
		return session.selectOne("mapper.adminMapper.getAdminByEmail", email);
	}
	
	public List<Notice_board> getLatestNotice() {
		
		return session.selectList("mapper.adminMapper.getLatestNotice");
	}
	
	public Integer countNoticeBno() {
		
		return session.selectOne("mapper.adminMapper.countNoticeBno");
	}
	
	public List<Notice_board> getNoticeList(PagingCondition pc) {
		
		return session.selectList("mapper.adminMapper.getNoticeList", pc);
	}
	public void deleteNotice(Integer bno) {
		session.update("mapper.adminMapper.deleteNotice", bno);
		
	}
	public void modifyNotice(Notice_board nb) {
		session.update("mapper.adminMapper.modifyNotice", nb);
	}
}

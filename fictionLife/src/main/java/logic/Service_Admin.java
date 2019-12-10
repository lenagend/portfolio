package logic;

import java.util.List;

import javax.servlet.http.HttpSession;

import condition.PagingCondition;
import model.Notice_board;
import model.Report_novel;

public interface Service_Admin {
	public void userBlind(String nickname);
	public void novelBlind(Integer id);
	public List<Report_novel> getReport(PagingCondition pc);
	public Integer getCount();
	public void userBlind2(String nickname);
	public void novelBlind2(Integer id);
	public void insertNotice(Notice_board nb);
	public List<Notice_board> getLatestNotice();
	public Integer countNoticeBno();
	public List<Notice_board> getNoticeList(PagingCondition pc);
	public void deleteNotice(Integer bno);
	public void modifyNotice(Notice_board nb);
	
}

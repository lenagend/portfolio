package dao;

import java.util.List;

import condition.PagingCondition;
import model.Admin;
import model.Notice_board;
import model.Report_novel;

public interface AdminDao {
public void userBlind(String nickname);
public void novelBlind(Integer id);
public List<Report_novel> getReport(PagingCondition pc);
public Integer getCount();
public void userBlind2(String nickname);
public void novelBlind2(Integer id);
public Integer maxNoticeBno();
public void insertNotice(Notice_board nb);
public Admin getAdminByEmail(String email);
public List<Notice_board> getLatestNotice();
public Integer countNoticeBno();
public List<Notice_board> getNoticeList(PagingCondition pc);
public void deleteNotice(Integer bno);
public void modifyNotice(Notice_board nb);
}

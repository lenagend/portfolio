package logic;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import condition.PagingCondition;
import dao.AdminDao;
import model.Admin;
import model.Member;
import model.Notice_board;
import model.Report_novel;

@Service
public class Service_Admin_Impl implements Service_Admin {

	@Autowired
	private AdminDao ad;
	
	public void userBlind(String nickname) {
		ad.userBlind(nickname);

	}

	public void novelBlind(Integer id) {
		ad.novelBlind(id);

	}
	public List<Report_novel> getReport(PagingCondition pc) {
		// TODO Auto-generated method stub
		return ad.getReport(pc);
	}
	public Integer getCount() {
		
		return ad.getCount();
	}
	public void userBlind2(String nickname) {
		ad.userBlind2(nickname);

	}

	public void novelBlind2(Integer id) {
		ad.novelBlind2(id);

	}

	public void insertNotice(Notice_board nb) {

		Integer bno = ad.maxNoticeBno();

		Admin admin = ad.getAdminByEmail(nb.getEmail());
		if (bno == null)
			bno = 0;
		nb.setAdmin_id(admin.getAdmin_id());
		nb.setBno(bno + 1);

		ad.insertNotice(nb);
	}

	public List<Notice_board> getLatestNotice() {

		return ad.getLatestNotice();
	}

	public Integer countNoticeBno() {

		return ad.countNoticeBno();
	}

	public List<Notice_board> getNoticeList(PagingCondition pc) {
		
		return ad.getNoticeList(pc);
	}
	
	public void deleteNotice(Integer bno) {
		ad.deleteNotice(bno);
		
	}
	
	public void modifyNotice(Notice_board nb) {
		ad.modifyNotice(nb);
		
	}
}

package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import condition.PagingCondition;
import logic.Service_Admin;
import model.Notice_board;
import model.Report_novel;

@Controller
public class AdminController {

	@Autowired
	private Service_Admin sa;
	
	@RequestMapping(value="/admin/loadAdminPage.html")
	public ModelAndView loadAdminPage(Integer pageNo) {
		ModelAndView mav = new ModelAndView("main");
		Integer cnt = 0;

		cnt = sa.getCount();

		
		PagingCondition c = new PagingCondition();
		c.paging(cnt, pageNo, 10);

		List<Report_novel> reports = sa.getReport(c);

		
		mav.addObject("REPORTS", reports);
		mav.addObject("COUNT", cnt);
		mav.addObject("pageCount", c.getPageCnt());
		mav.addObject("startRow", c.getStartRow());
		mav.addObject("endRow", c.getEndRow());
		mav.addObject("currentPage", c.getCurrentPage());
		mav.addObject("BODY", "managerPage.jsp");
		
		return mav;
	};
	
	
	@RequestMapping(value="/admin/userBlind.html")
	public ModelAndView userBlind(String nickname) {
		ModelAndView mav = new ModelAndView("blindResult");
		
		sa.userBlind(nickname);
		
		
		return mav;
	};
	
	@RequestMapping(value="/admin/novelBlind.html")
	public ModelAndView novelBlind(Integer id) {
		ModelAndView mav = new ModelAndView("blindResult");
		
		sa.novelBlind(id);
		
		
		return mav;
	};
	
	@RequestMapping(value="/admin/userBlind2.html")
	public ModelAndView userBlind2(String nickname) {
		ModelAndView mav = new ModelAndView("blindResult");
		
		sa.userBlind2(nickname);
		
		
		return mav;
	};
	
	@RequestMapping(value="/admin/novelBlind2.html")
	public ModelAndView novelBlind2(Integer id) {
		ModelAndView mav = new ModelAndView("blindResult");
		
		sa.novelBlind2(id);
		
		
		return mav;
	};
	

	@RequestMapping(value="/admin/notice.html")
	public ModelAndView notice(HttpSession session, String title, String content) {
		ModelAndView mav = new ModelAndView("blindResult");
		
		
		Notice_board nb = new Notice_board();
		nb.setTitle(title);
		nb.setContent(content);
		sa.insertNotice(nb, session);
		mav.addObject("notice","OK");
		return mav;
	};
	
	@RequestMapping(value="/admin/deleteNotice.html")
	public ModelAndView deleteNotice(Integer bno) {
		ModelAndView mav = new ModelAndView("blindResult");
		
		sa.deleteNotice(bno);
		mav.addObject("delete", "OK");
		return mav;
	};
	
	
	@RequestMapping(value="/admin/loadModifyNoticeForm.html")
	public ModelAndView loadModifyNoticeForm(Integer bno, String title, String content) {
		ModelAndView mav = new ModelAndView("main");
		
		mav.addObject("bno",bno);
		mav.addObject("title",title);
		mav.addObject("content", content);
		mav.addObject("BODY", "modifyNoticeForm.jsp");
		
		
		return mav;
	};
	
	@RequestMapping(value="/admin/modifyNotice.html")
	public ModelAndView modifyNotice(Integer bno, String title, String content) {
		ModelAndView mav = new ModelAndView("blindResult");
		Notice_board nb = new Notice_board(); 
		
		nb.setBno(bno);
		nb.setTitle(title);
		nb.setContent(content);
		sa.modifyNotice(nb);
		mav.addObject("modify", "OK");
		return mav;
	};
	
	@RequestMapping(value="/admin/loadReportReader.html")
	public ModelAndView loadNoticeReader(String content,  Integer novelId, Integer epi, Integer bno  ) {
		
		ModelAndView mav = new ModelAndView("main");
		mav.addObject("BODY", "reportReader.jsp");
		mav.addObject("content", content);
		mav.addObject("novelId", novelId);
		mav.addObject("epi", epi);
		mav.addObject("bno", bno);
		return mav;
		
	}
}

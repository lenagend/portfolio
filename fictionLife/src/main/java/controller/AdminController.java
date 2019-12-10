//package controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import condition.PagingCondition;
//import logic.Service_Admin;
//import model.Member;
//import model.Notice_board;
//import model.Report_novel;
//
//@Controller
//public class AdminController {
//
//	@Autowired
//	private Service_Admin sa;
//	
//	@RequestMapping(value="/admin/loadAdminPage.html")
//	public ModelAndView loadAdminPage(Integer pageNo) {
//		ModelAndView mav = new ModelAndView("main");
//		Integer cnt = 0;
//
//		cnt = sa.getCount();
//
//		if (cnt == null)
//			cnt = 0;
//
//		int startRow = 0;
//		int endRow = 0;
//		int pageCnt = 0;
//		int currentPage = 0;
//
//		if (pageNo == null)
//			currentPage = 1;
//		else
//			currentPage = pageNo;
//
//		if (cnt > 0) {
//			pageCnt = cnt / 5;
//			if (cnt % 5 > 0)
//				pageCnt++;
//			startRow = (currentPage - 1) * 5 + 1;
//			endRow = currentPage * 5;
//
//			if (endRow > cnt)
//				endRow = cnt;
//
//		}
//		PagingCondition c = new PagingCondition();
//		c.setStartRow(startRow);
//		c.setEndRow(endRow);
//
//		List<Report_novel> reports = sa.getReport(c);
//
//		
//		mav.addObject("REPORTS", reports);
//		mav.addObject("COUNT", cnt);
//		mav.addObject("pageCount", pageCnt);
//		mav.addObject("startRow", startRow);
//		mav.addObject("endRow", endRow);
//		mav.addObject("currentPage", currentPage);
//		mav.addObject("BODY", "managerPage.jsp");
//		
//		return mav;
//	};
//	
//	
//	@RequestMapping(value="/admin/userBlind.html")
//	public ModelAndView userBlind(String nickname) {
//		ModelAndView mav = new ModelAndView("blindResult");
//		
//		sa.userBlind(nickname);
//		
//		
//		return mav;
//	};
//	
//	@RequestMapping(value="/admin/novelBlind.html")
//	public ModelAndView novelBlind(Integer id) {
//		ModelAndView mav = new ModelAndView("blindResult");
//		
//		sa.novelBlind(id);
//		
//		
//		return mav;
//	};
//	
//	@RequestMapping(value="/admin/userBlind2.html")
//	public ModelAndView userBlind2(String nickname) {
//		ModelAndView mav = new ModelAndView("blindResult");
//		
//		sa.userBlind2(nickname);
//		
//		
//		return mav;
//	};
//	
//	@RequestMapping(value="/admin/novelBlind2.html")
//	public ModelAndView novelBlind2(Integer id) {
//		ModelAndView mav = new ModelAndView("blindResult");
//		
//		sa.novelBlind2(id);
//		
//		
//		return mav;
//	};
//	
//
//	@RequestMapping(value="/admin/notice.html")
//	public ModelAndView notice(HttpSession session, String title, String content) {
//		ModelAndView mav = new ModelAndView("blindResult");
//		
//		Member loginuser = (Member)session.getAttribute("LOGINMEMBER");
//	
//		Notice_board nb = new Notice_board();
//		nb.setNickname(loginuser.getNickname());
//		nb.setTitle(title);
//		nb.setContent(content);
//		nb.setEmail(loginuser.getEmail());
//		sa.insertNotice(nb);
//		mav.addObject("notice","OK");
//		return mav;
//	};
//	
//	@RequestMapping(value="/admin/deleteNotice.html")
//	public ModelAndView deleteNotice(Integer bno) {
//		ModelAndView mav = new ModelAndView("blindResult");
//		
//		sa.deleteNotice(bno);
//		mav.addObject("delete", "OK");
//		return mav;
//	};
//	
//	
//	@RequestMapping(value="/admin/loadModifyNoticeForm.html")
//	public ModelAndView loadModifyNoticeForm(Integer bno, String title, String content) {
//		ModelAndView mav = new ModelAndView("main");
//		
//		mav.addObject("bno",bno);
//		mav.addObject("title",title);
//		mav.addObject("content", content);
//		mav.addObject("BODY", "modifyNoticeForm.jsp");
//		
//		
//		return mav;
//	};
//	
//	@RequestMapping(value="/admin/modifyNotice.html")
//	public ModelAndView modifyNotice(Integer bno, String title, String content) {
//		ModelAndView mav = new ModelAndView("blindResult");
//		Notice_board nb = new Notice_board(); 
//		
//		nb.setBno(bno);
//		nb.setTitle(title);
//		nb.setContent(content);
//		sa.modifyNotice(nb);
//		mav.addObject("modify", "OK");
//		return mav;
//	};
//	
//	@RequestMapping(value="/admin/loadReportReader.html")
//	public ModelAndView loadNoticeReader(String content,  Integer novelId, Integer epi, Integer bno  ) {
//		
//		ModelAndView mav = new ModelAndView("main");
//		mav.addObject("BODY", "reportReader.jsp");
//		mav.addObject("content", content);
//		mav.addObject("novelId", novelId);
//		mav.addObject("epi", epi);
//		mav.addObject("bno", bno);
//		return mav;
//		
//	}
//}

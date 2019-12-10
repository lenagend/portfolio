package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import condition.EmailCondition;
import condition.PagingCondition;
import logic.Service_Admin;
import logic.Service_Member;
import logic.Service_Novel;
import model.Member;
import model.Notice_board;
import model.Novel;
import model.Novel_board;
import model.Reply_novel;

@Controller
public class HomeController {
	@Autowired
	private Service_Novel sn;
	
	@Autowired
	private Service_Member sm;
	
	@Autowired
	private Service_Admin sa;

	@RequestMapping(value="/home/goMain.html")
	public ModelAndView goMain(Integer pageNo, HttpSession session, String novelType, String search){
		//랭크업데이트
		Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
		if(loginMember!=null) {
		sm.rankProcess(loginMember, session);	
		}		
		
		
		ModelAndView mav = new ModelAndView("main");
		Integer cnt = 0;
		if(novelType!= null) {
			cnt= sn.countTypeNovelList(novelType);
		
		}else if(search!=null){
		
			cnt = sn.countSearchNovel(search);
		}else {
			cnt = sn.countNovelList();
		
		}
//
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
//		List<Novel> allNovelList;
//
//		if(novelType!=null) {
//			c.setType(novelType);
//			allNovelList = sn.findNovelByType(c);
//			
//		}else if(search!=null) {
//			c.setSearch(search);
//			allNovelList = sn.getSearchNovel(c);
//			if(allNovelList.isEmpty()) {
//				mav.addObject("searchResult", "noResult");
//			}
//			
//			
//			}
//		
//		else {
//			allNovelList = sn.findAllNovel(c);
//		}
//		
//		//각 닉네임으로 아이콘을 찾아와야한다...
//		if(allNovelList != null) {
//			Iterator it = allNovelList.iterator();
//			int i = 0;
//			while(it.hasNext()) {
//				Novel ci =(Novel)it.next();
//				String w_icon_image= sm.getW_icon_ImageByNickname(ci.getNickname());  
//				
//				ci.setW_icon_image(w_icon_image);
//				i++;
//			}
//			
//			
//		}
//		
//		
//		
//		//top5 작가들 작품
//		List<Novel> top5List = new ArrayList<Novel>();
//		top5List.add(0,sn.findTop1Novel());
//		top5List.add(1,sn.findTop2Novel());
//		top5List.add(2, sn.findTop3Novel());
//		top5List.add(3, sn.findTop4Novel());
//		top5List.add(4, sn.findTop5Novel());
//		
//		mav.addObject("TOP5_LIST", top5List);
//		
//		//추천수 1위~ 5위까지
//		List<Novel> top10List = sn.findTop10NovelByReco_cnt();
//		mav.addObject("TOP10_LIST", top10List);
//		
//		//공지사항
//		List<Notice_board> noticeList = sa.getLatestNotice();
//		mav.addObject("NOTICE_LIST", noticeList);
//		
//		mav.addObject("NOVEL_LIST", allNovelList);
//		mav.addObject("COUNT", cnt);
//		
//		mav.addObject("pageCount", pageCnt);
//		System.out.println("cnt: "+ cnt);
//		System.out.println("cnt: "+ cnt);System.out.println("cnt: "+ cnt);System.out.println("cnt: "+ cnt);System.out.println("cnt: "+ cnt);System.out.println("cnt: "+ cnt);
//		System.out.println("pageCnt: "+ pageCnt);
//		System.out.println("pageCnt: "+ pageCnt);
//		System.out.println("pageCnt: "+ pageCnt);
//		System.out.println("pageCnt: "+ pageCnt);
//		
//		
//		mav.addObject("startRow", startRow);
//		mav.addObject("endRow", endRow);
//		mav.addObject("currentPage", currentPage);
		mav.addObject("BODY", null);
//		mav.addObject("BOARD", "novelBoard.jsp");

		return mav;
	}
//
	@RequestMapping(value="/home/loadLogin.html")
	public ModelAndView doLogin(HttpServletRequest request) {
		//로그인후 이 페이지로 이동하려면...
				String referer = request.getHeader("Referer");
				request.getSession().setAttribute("redirectURI", referer);	
		
		
		ModelAndView mav = new ModelAndView("main");
		mav.addObject("BODY", "loginForm.jsp");
		mav.addObject(new Member());
		return mav;
	}
	
	@RequestMapping(value="/home/loadEmail.html")
	public ModelAndView doRegi() {
		
		ModelAndView mav = new ModelAndView("main");
		mav.addObject("BODY", "email.jsp");
		mav.addObject(new EmailCondition());
		return mav;
	}
	
	
	@RequestMapping(value="/home/loadFindEmail.html")
	public ModelAndView loadFindEmail() {
		ModelAndView mav = new ModelAndView("main");
		mav.addObject("BODY", "findEmail.jsp");
	
		return mav;
	}
	
	
	
	@RequestMapping(value="/home/loadMyPage.html")
	public ModelAndView loadMyPage(HttpSession session, Integer pageNo) {
		
		
		ModelAndView mav = new ModelAndView("main");
		Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
		String loginEmail = loginMember.getEmail();
		


			Integer cnt = sn.getMaxMyNovel(loginEmail);
			PagingCondition c = new PagingCondition();
			c.setEmail(loginEmail);
			c.paging(cnt, pageNo, 5);
			
			List<Novel> myNovelList = sn.findMyNovel(c);	

			mav.addObject("NOVEL_LIST", myNovelList);
			mav.addObject("COUNT", cnt);
			mav.addObject("pageCount",c.getPageCnt());
			mav.addObject("startRow",c.getStartRow());
			mav.addObject("endRow",c.getEndRow());
			mav.addObject("currentPage",c.getCurrentPage());
			mav.addObject("CONTENTNAME", "MYNOVEL");
			mav.addObject("BODY", "myPage.jsp");
	
		return mav;
	}

//	@RequestMapping(value="/home/loadMyPage2.html")
//	public ModelAndView loadMyPage2(HttpSession session, Integer pageNo) {
//		
//		
//		ModelAndView mav = new ModelAndView("main");
//		Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
//		String loginEmail = loginMember.getEmail();
//			
//		
//		Integer cnt = sn.countMyFavorite(loginEmail);
//		
//		if(cnt==null) cnt=0;
//		
//		int startRow = 0; int endRow=0;int pageCnt=0;
//		int currentPage=0;
//		
//		if(pageNo==null) currentPage =1;
//		else currentPage = pageNo;
//		
//		if(cnt>0) {
//			pageCnt = cnt/5;
//			if(cnt % 5>0) pageCnt++;
//			startRow = (currentPage-1)*5 +1;
//			endRow = currentPage * 5;
//			
//			if(endRow > cnt) endRow = cnt;
//			
//		}
//		
//		PagingCondition c = new PagingCondition();
//		c.setStartRow(startRow); c.setEndRow(endRow);c.setEmail(loginEmail);
//		List<Novel> myFavoriteList = sn.myFavoriteNovels(c);	
//		
//		//각 닉네임으로 아이콘을 찾아와야한다...
//				if(myFavoriteList != null) {
//					Iterator it = myFavoriteList.iterator();
//					int i = 0;
//					while(it.hasNext()) {
//						Novel ci =(Novel)it.next();
//						String w_icon_image= sm.getW_icon_ImageByNickname(ci.getNickname());  
//						
//						ci.setW_icon_image(w_icon_image);
//						i++;
//					}
//					
//					
//				}
//		
//		mav.addObject("NOVEL_LIST", myFavoriteList);
//		mav.addObject("COUNT", cnt);
//		mav.addObject("pageCount", pageCnt);
//		mav.addObject("startRow", startRow);
//		mav.addObject("endRow", endRow);
//		mav.addObject("currentPage", currentPage);
//		mav.addObject("CONTENTNAME", "MYFAVORITE");
//		mav.addObject("BODY", "myPage.jsp");
//
//		return mav;
//	}
//	
//	
//	
//	@RequestMapping(value="/home/loadModifyMember.html")
//	public ModelAndView loadModifyMember(HttpSession session) {
//		ModelAndView mav = new ModelAndView("main");
//		Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
//		String loginEmail = loginMember.getEmail();
//		Member modifyMember = sm.checkEmail(loginEmail); 
//		
//		mav.addObject("BODY", "myPage.jsp");
//		mav.addObject("member",modifyMember);
//		mav.addObject("CONTENTNAME", "modifyForm");
//		
//		return mav;
//	}
//	
//	
//	
//	
//
//	
	@RequestMapping(value="/home/loadRegiNovel.html")
	public ModelAndView loadRegiNovel(HttpSession session) {
		Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
		if(sn.countNotFinished(loginMember.getEmail())>0) {
			return new ModelAndView("finishedResult");
		}else {

			ModelAndView mav = new ModelAndView("main");		
			mav.addObject("BODY", "regiNovel.jsp");
			mav.addObject(new Novel());
			return mav;
		}
		
	}
	
//	@RequestMapping(value="/home/loadModiNovel.html")
//	public ModelAndView loadModiNovel(Integer novelId, String novelImage) {
//		ModelAndView mav = new ModelAndView("main");
//		Novel novel = new Novel();
//		novel.setId(novelId);
//		novel.setImage(novelImage);
//		mav.addObject("BODY", "modiNovel.jsp");
//		mav.addObject(novel);
//		return mav;
//	}
//	
//	@RequestMapping(value="/home/loadDeleteNovel.html")
//	public ModelAndView loadDeleteNovel(Integer novelId) {
//		ModelAndView mav = new ModelAndView("checkDeleteOk");
//		mav.addObject("deleteNovelid", novelId);
//		return mav;
//	}
//	@RequestMapping(value="/home/loadEpisodeForm.html")
//	public ModelAndView loadEpiForm(Integer novelId, String novelTitle, HttpSession session) {
//		
//		if(sn.thisNovelFinished(novelId)>0) {
//			//만약 이작품이 완결났으면
//			ModelAndView mav = new ModelAndView("finishedResult");
//			mav.addObject("result", "1");
//			return mav;
//		}
//		
//		
//		
//		
//		ModelAndView mav = new ModelAndView("main");
//		Integer epinum = sn.maxEpiNum(novelId);
//		if(epinum==null)epinum=0;
//		mav.addObject("BODY", "episodeForm.jsp");
//		session.setAttribute("epinum", epinum+1);
//		session.setAttribute("novelId", novelId);
//		session.setAttribute("novelTitle", novelTitle);
//		mav.addObject(new Novel_board());
//		return mav;
//	}
//	
//	@RequestMapping(value="/home/loadSeries.html")
//	public ModelAndView loadSeries(Integer novelId, Integer pageNo, HttpServletRequest request) {
//		String referer = request.getHeader("Referer");
//		request.getSession().setAttribute("redirectURI", referer);	
//		
//		ModelAndView mav = new ModelAndView("main");
//	
//		
//		//
//		Integer cnt = sn.getEpiCount(novelId);
//		if(cnt==null) cnt=0;
//		int startRow = 0; int endRow=0;int pageCnt=0;
//		int currentPage=0;
//		
//		if(pageNo==null) currentPage =1;
//		else currentPage = pageNo;
//		
//		if(cnt>0) {
//			pageCnt = cnt/5;
//			if(cnt % 5>0) pageCnt++;
//			startRow = (currentPage-1)*5 +1;
//			endRow = currentPage * 5;
//			
//			if(endRow > cnt) endRow = cnt;
//			
//		}
//		PagingCondition c = new PagingCondition();
//		c.setStartRow(startRow); c.setEndRow(endRow);c.setId(novelId);
//		List<Novel_board> epiList=sn.getEpiList(c);
//		Novel parentNovel = sn.findParentNovel(novelId);
//		
//		
//		
//		mav.addObject("novelId", novelId);
//		mav.addObject("parentNovel", parentNovel);
//		mav.addObject("EPI_LIST", epiList);
//		mav.addObject("COUNT", cnt);
//		mav.addObject("pageCount",pageCnt);
//		mav.addObject("startRow",startRow);
//		mav.addObject("endRow",endRow);
//		mav.addObject("currentPage",currentPage);
//		mav.addObject("BODY", "seriesView.jsp");
//
//		return mav;
//
//	}
//	@RequestMapping(value="/home/loadReader.html")
//	public ModelAndView loadReader(Integer epi_number, 
//			Integer pni, Integer bno, Integer pageNo, HttpServletRequest request) {
//		String referer = request.getHeader("Referer");
//		request.getSession().setAttribute("redirectURI", referer);	
//		
//		
//		Novel_board nb = new Novel_board();
//		nb.setEpi_number(epi_number);
//		nb.setNovel_id(pni);
//		
//		nb=  sn.getEpiContent(nb);
//		
//		ModelAndView mav = new ModelAndView("main");
//		Novel parentNovel = sn.findParentNovel(pni);
//		
//		//조회수 1추가
//		sn.plusViewCnt(bno);
//		
//		
//		//댓글 불러오기
//
//		Integer cnt = sn.countReplyByBno(bno);
//		if(cnt==null) cnt=0;
//		int startRow = 0; int endRow=0;int pageCnt=0;
//		int currentPage=0;
//		
//		if(pageNo==null) currentPage =1;
//		else currentPage = pageNo;
//		
//		if(cnt>0) {
//			pageCnt = cnt/10;
//			if(cnt % 10>0) pageCnt++;
//			startRow = (currentPage-1)*10 +1;
//			endRow = currentPage * 10;
//			
//			if(endRow > cnt) endRow = cnt;
//			
//		}
//		PagingCondition c = new PagingCondition();
//		c.setStartRow(startRow); c.setEndRow(endRow);c.setId(bno);
//		
//		List<Reply_novel> replyList = sn.getReplyList(c);
//		//각 닉네임으로 아이콘을 찾아와야한다...
//				if(replyList != null) {
//					Iterator it = replyList.iterator();
//					int i = 0;
//					while(it.hasNext()) {
//						Reply_novel ci =(Reply_novel)it.next();
//						String r_icon_image= sm.getR_icon_ImageByNickname(ci.getNickname());  
//						System.out.println("아이콘 이름: "+r_icon_image);
//						ci.setR_icon_image(r_icon_image);
//						i++;
//					}
//					
//					
//				}
//				
//				
//		//
//			
//		
//		//대댓글...
//		List<Reply_novel> reReplyList = sn.getReREply(c);
//		//각 닉네임으로 아이콘을 찾아와야한다...
//		if(reReplyList != null) {
//			Iterator it = reReplyList.iterator();
//			int i = 0;
//			while(it.hasNext()) {
//				Reply_novel ci =(Reply_novel)it.next();
//				String r_icon_image= sm.getR_icon_ImageByNickname(ci.getNickname());  
//				System.out.println("아이콘 이름: "+r_icon_image);
//				ci.setR_icon_image(r_icon_image);
//				i++;
//			}
//			
//			
//		}
//		
//		
////
//		
//		
//		mav.addObject("REPLY_LIST", replyList);
//		
//		mav.addObject("REREPLY_LIST", reReplyList);
//		
//		mav.addObject("COUNT", cnt);
//		mav.addObject("pageCount",pageCnt);
//		mav.addObject("startRow",startRow);
//		mav.addObject("endRow",endRow);
//		mav.addObject("currentPage",currentPage);
//		
//		mav.addObject("parentNovel", parentNovel);
//		mav.addObject("EPISODE", nb);
//		mav.addObject("BODY", "reader.jsp");
//		
//		//답글 갯수
//		
//		return mav;
//	};
//	
//	@RequestMapping(value="/novel/loadModifyEpiForm.html")
//	public ModelAndView modifyEpi(Integer epiNumber, Integer parentNovelId) {
//		Novel_board inputNb = new Novel_board();
//		inputNb.setEpi_number(epiNumber);
//		inputNb.setNovel_id(parentNovelId);
//		
//		inputNb = sn.getEpiContent(inputNb);
//		
//		ModelAndView mav = new ModelAndView("main");
//		mav.addObject("BODY", "modifier.jsp");
//		mav.addObject("novel_board", inputNb);
//		
//		
//		return mav;
//	}
//	
//	@RequestMapping(value="/home/loadNotice.html")
//	public ModelAndView loadNotice( Integer pageNo) {
//		
//		Integer cnt =sa.countNoticeBno();
//		
//		if(cnt==null) cnt=0;
//		
//		int startRow = 0; int endRow=0;int pageCnt=0;
//		int currentPage=0;
//		
//		if(pageNo==null) currentPage =1;
//		else currentPage = pageNo;
//		
//		if(cnt>0) {
//			pageCnt = cnt/10;
//			if(cnt % 10>0) pageCnt++;
//			startRow = (currentPage-1)*10 +1;
//			endRow = currentPage * 10;
//			
//			if(endRow > cnt) endRow = cnt;
//			
//		}
//		
//		PagingCondition c = new PagingCondition();
//		c.setStartRow(startRow); c.setEndRow(endRow);
//		List<Notice_board> nociteList = sa.getNoticeList(c);
//		
//		ModelAndView mav = new ModelAndView("main");
//
//		mav.addObject("NOTICE_LIST",nociteList);
//		mav.addObject("COUNT", cnt);
//		mav.addObject("pageCount",pageCnt);
//		mav.addObject("startRow",startRow);
//		mav.addObject("endRow",endRow);
//		mav.addObject("currentPage",currentPage);
//		mav.addObject("BODY", "noticeList.jsp");
//		
//		//답글 갯수
//		
//		return mav;
//	}
//	
//	@RequestMapping(value="/home/loadNoticeReader.html")
//	public ModelAndView loadNoticeReader(String content, HttpServletRequest request) {
//		String referer = request.getHeader("Referer");
//		request.getSession().setAttribute("redirectURI", referer);	
//		ModelAndView mav = new ModelAndView("main");
//		mav.addObject("BODY", "noticeReader.jsp");
//		mav.addObject("content", content);
//		return mav;
//		
//	}
}

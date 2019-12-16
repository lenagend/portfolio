package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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

//1210 저녁 11시 2분 수정

@Controller
public class HomeController {
	@Autowired
	private Service_Novel sn;
	
	@Autowired
	private Service_Member sm;
	
	@Autowired
	private Service_Admin sa;

	@RequestMapping(value="/home/goMain.html")
	public ModelAndView goMain(HttpSession session){
		//랭크업데이트
		Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
		if(loginMember!=null) {
		sm.rankProcess(loginMember, session);	
		}		
		
		
		ModelAndView mav = new ModelAndView("main");
		Integer cnt = 0;
		cnt = sn.countNovelList();
		
	

		PagingCondition c = new PagingCondition();
		c.paging(cnt, 1, 5);

		List<Novel> allNovelList;
		allNovelList = sn.findAllNovel(c);
		
		
		//각 닉네임으로 아이콘을 찾아와야한다...
		if(allNovelList != null) {
			Iterator it = allNovelList.iterator();
			int i = 0;
			while(it.hasNext()) {
				Novel ci =(Novel)it.next();
				ci.setMember(sm.checkEmail(ci.getEmail()));
				String w_icon_image= sm.getW_icon_ImageByEmail(ci.getEmail());  
				
				ci.setW_icon_image(w_icon_image);
				i++;
			}
			
			
		}
		
		
		
		//top5 작가들 작품
		List<Novel> top5List = new ArrayList<Novel>();
		top5List.add(0,sn.findTop1Novel());
		top5List.add(1,sn.findTop2Novel());
		top5List.add(2, sn.findTop3Novel());
		top5List.add(3, sn.findTop4Novel());
		top5List.add(4, sn.findTop5Novel());
		
		mav.addObject("TOP5_LIST", top5List);
		
		//추천수 1위~ 5위까지
		List<Novel> top10List = sn.findTop10NovelByReco_cnt();
		mav.addObject("TOP10_LIST", top10List);
		
		//공지사항
		List<Notice_board> noticeList = sa.getLatestNotice();
		//글마다 멤버객체를가지고있는다
				if(noticeList != null) {
					Iterator it = noticeList.iterator();
					int i = 0;
					while(it.hasNext()) {
						Notice_board ci =(Notice_board)it.next();
						ci.setMember(sm.checkEmail(ci.getEmail()));
						i++;
					}
					
					
				}
		mav.addObject("NOTICE_LIST", noticeList);
		
		mav.addObject("NOVEL_LIST", allNovelList);
		mav.addObject("COUNT", cnt);
		mav.addObject("pageCount", c.getPageCnt());
		mav.addObject("startRow",c.getStartRow());
		mav.addObject("endRow",c.getEndRow());
		mav.addObject("currentPage",c.getCurrentPage());
		mav.addObject("BODY", null);
		

		return mav;
	}
	
	@RequestMapping(value="/home/playBoard.html")
	public ModelAndView playBoard(Integer pageNo,String novelType, String search ) {
		ModelAndView mav = new ModelAndView("main");
		Integer cnt = 0;
		
		if(novelType!= null) {
			cnt= sn.countTypeNovelList(novelType);
		
		}else if(search!=null){
		
			cnt = sn.countSearchNovel(search);
		}else {
			cnt = sn.countNovelList();
		
		}
		PagingCondition c = new PagingCondition();
		c.paging(cnt, pageNo, 5);

		List<Novel> allNovelList;

		if(novelType!=null) {
			c.setType(novelType);
			allNovelList = sn.findNovelByType(c);
			
		}else if(search!=null) {
			c.setSearch(search);
			allNovelList = sn.getSearchNovel(c);
			if(allNovelList.isEmpty()) {
				mav.addObject("searchResult", "noResult");
			}
			
			
			}
		
		else {
			allNovelList = sn.findAllNovel(c);
		}
		//각 닉네임으로 아이콘을 찾아와야한다...
				if(allNovelList != null) {
					Iterator it = allNovelList.iterator();
					int i = 0;
					while(it.hasNext()) {
						Novel ci =(Novel)it.next();
						ci.setMember(sm.checkEmail(ci.getEmail()));
						String w_icon_image= sm.getW_icon_ImageByEmail(ci.getEmail());  
						
						ci.setW_icon_image(w_icon_image);
						i++;
					}
					
					
				}
				
				mav.addObject("NOVEL_LIST", allNovelList);
				mav.addObject("COUNT", cnt);
				mav.addObject("pageCount", c.getPageCnt());
				mav.addObject("startRow",c.getStartRow());
				mav.addObject("endRow",c.getEndRow());
				mav.addObject("currentPage",c.getCurrentPage());
				mav.addObject("BODY", "novelBoard.jsp");
						
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

	@RequestMapping(value="/home/loadMyPage2.html")
	public ModelAndView loadMyPage2(HttpSession session, Integer pageNo) {
		
		
		ModelAndView mav = new ModelAndView("main");
		Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
		String loginEmail = loginMember.getEmail();
			
		
		Integer cnt = sn.countMyFavorite(loginEmail);
	
		PagingCondition c = new PagingCondition();
		c.setEmail(loginEmail);
		c.paging(cnt, pageNo, 10);
		List<Novel> myFavoriteList = sn.myFavoriteNovels(c);	
		
		//각 닉네임으로 아이콘을 찾아와야한다...
				if(myFavoriteList != null) {
					Iterator it = myFavoriteList.iterator();
					int i = 0;
					while(it.hasNext()) {
						Novel ci =(Novel)it.next();
						Member writer = sm.checkEmail(ci.getEmail());
						String w_icon_image= sm.getW_icon_ImageByEmail(ci.getEmail());
						ci.setMember(writer);
						
						ci.setW_icon_image(w_icon_image);
						i++;
					}
					
					
				}
		
		mav.addObject("NOVEL_LIST", myFavoriteList);
		mav.addObject("COUNT", cnt);
		mav.addObject("pageCount",c.getPageCnt());
		mav.addObject("startRow",c.getStartRow());
		mav.addObject("endRow",c.getEndRow());
		mav.addObject("currentPage",c.getCurrentPage());
		mav.addObject("CONTENTNAME", "MYFAVORITE");
		mav.addObject("BODY", "myPage.jsp");

		return mav;
	}
	
	
	
	@RequestMapping(value="/home/loadModifyMember.html")
	public ModelAndView loadModifyMember(HttpSession session) {
		ModelAndView mav = new ModelAndView("main");
		Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
		String loginEmail = loginMember.getEmail();
		Member modifyMember = sm.checkEmail(loginEmail); 
		
		mav.addObject("BODY", "myPage.jsp");
		mav.addObject("member",modifyMember);
		mav.addObject("CONTENTNAME", "modifyForm");
		
		return mav;
	}
	
	
	
	

	
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
	
	@RequestMapping(value="/home/loadModiNovel.html")
	public ModelAndView loadModiNovel(Integer novelId, String novelImage) {
		ModelAndView mav = new ModelAndView("main");
		Novel novel = new Novel();
		novel.setId(novelId);
		novel.setImage(novelImage);
		mav.addObject("BODY", "modiNovel.jsp");
		mav.addObject(novel);
		return mav;
	}
	
	@RequestMapping(value="/home/loadDeleteNovel.html")
	public ModelAndView loadDeleteNovel(Integer novelId) {
		ModelAndView mav = new ModelAndView("checkDeleteOk");
		mav.addObject("deleteNovelid", novelId);
		return mav;
	}
	@RequestMapping(value="/home/loadEpisodeForm.html")
	public ModelAndView loadEpiForm(Integer novelId) {
		
		if(sn.thisNovelFinished(novelId)>0) {
			//만약 이작품이 완결났으면
			ModelAndView mav = new ModelAndView("finishedResult");
			mav.addObject("result", "1");
			return mav;
		}
			
		
		ModelAndView mav = new ModelAndView("main");
		Novel novel =sn.findParentNovel(novelId);
		
		mav.addObject("BODY", "episodeForm.jsp");
		mav.addObject("novel", novel);
		mav.addObject(new Novel_board());
		return mav;
	}
	
	@RequestMapping(value="/home/loadSeries.html")
	public ModelAndView loadSeries(Integer novelId, Integer pageNo, HttpSession session) {
		
		
		ModelAndView mav = new ModelAndView("main");
	
		
		//
		Integer cnt = sn.getEpiCount(novelId);

		PagingCondition c = new PagingCondition();
	    c.setId(novelId);
	    c.paging(cnt, pageNo, 3);
		List<Novel_board> epiList=sn.getEpiList(c);
		Novel parentNovel = sn.findParentNovel(novelId);
		parentNovel.setMember(sm.checkEmail(parentNovel.getEmail()));
		
		
		
		session.setAttribute("parentNovel", parentNovel);
		mav.addObject("EPI_LIST", epiList);
		mav.addObject("COUNT", cnt);
		mav.addObject("pageCount",c.getPageCnt());
		mav.addObject("startRow",c.getStartRow());
		mav.addObject("endRow",c.getEndRow());
		mav.addObject("currentPage",c.getCurrentPage());
		mav.addObject("BODY", "seriesView.jsp");

		return mav;

	}
	
	@RequestMapping(value="home/loadReply.html")
	@ResponseBody
	public String loadReply(Integer bno, Integer pageNo, HttpServletResponse response) {
		
		
		System.out.println("페이지넘버"+ pageNo);
		System.out.println("페이지넘버"+ pageNo);
		System.out.println("페이지넘버"+ pageNo);
		System.out.println("페이지넘버"+ pageNo);
		//댓글 불러오기

		Integer cnt = sn.countReplyByBno(bno);
		
		PagingCondition c = new PagingCondition();
		c.paging(cnt, pageNo, 10);c.setId(bno);
		
		List<Reply_novel> replyList = sn.getReplyList(c);
		System.out.println("List사이즈 : "+replyList.size());
		Gson gson = new Gson();
		
		JsonArray array = new JsonArray();
		// 각 닉네임으로 아이콘을 찾아와야한다...
		if (replyList != null) {
			Iterator it = replyList.iterator();
			int i = 0;
			while (it.hasNext()) {
				JsonObject object = new JsonObject();
				if(cnt > (c.getCurrentPage()*10)) {
					object.addProperty("endPage", "no");
					
				}
				else {
				
					object.addProperty("endPage", "yes");
					
				}
				Reply_novel ci = (Reply_novel) it.next();
				String r_icon_image = sm.getR_icon_ImageByEmail(ci.getEmail());
				ci.setMember(sm.checkEmail(ci.getEmail()));
				object.addProperty("rno", ci.getRno());
				object.addProperty("nickname", ci.getMember().getNickname());
				object.addProperty("regiDate", ci.getRegi_date());
				object.addProperty("content", ci.getContent());
				object.addProperty("iconImage", r_icon_image);
				array.add(object);
						i++;
					}
					
					
				}
	
		
		
		String json = gson.toJson(array);
		
		return json;
	}
	
	@RequestMapping(value="/home/loadReader.html")
	public ModelAndView loadReader(Integer epi_number, 
			Integer bno, Integer pageNo, HttpSession session) {
	
		
		
		Novel_board nb = new Novel_board();
		Novel parentNovel = (Novel)session.getAttribute("parentNovel");
		nb.setEpi_number(epi_number);
		nb.setNovel_id(parentNovel.getId());
		
		nb=  sn.getEpiContent(nb);
		
		ModelAndView mav = new ModelAndView("main");
		
		
		//조회수 1추가
		sn.plusViewCnt(bno);
		
		
		//댓글 불러오기

		Integer cnt = sn.countReplyByBno(bno);
		
		PagingCondition c = new PagingCondition();
		c.paging(cnt, pageNo, 10);c.setId(bno);
		
//		List<Reply_novel> replyList = sn.getReplyList(c);
//		//각 닉네임으로 아이콘을 찾아와야한다...
//				if(replyList != null) {
//					Iterator it = replyList.iterator();
//					int i = 0;
//					while(it.hasNext()) {
//						Reply_novel ci =(Reply_novel)it.next();
//						String r_icon_image= sm.getR_icon_ImageByEmail(ci.getEmail());  
//						ci.setMember(sm.checkEmail(ci.getEmail()));
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
		mav.addObject("COUNT", cnt);
//		mav.addObject("pageCount",c.getPageCnt());
//		mav.addObject("startRow",c.getStartRow());
//		mav.addObject("endRow",c.getEndRow());
//		mav.addObject("currentPage",c.getCurrentPage());
		
		mav.addObject("parentNovel", parentNovel);
		mav.addObject("EPISODE", nb);
		mav.addObject("BODY", "reader.jsp");
		
		//답글 갯수
		
		return mav;
	};
	
	@RequestMapping(value="/novel/loadModifyEpiForm.html")
	public ModelAndView modifyEpi(Integer epiNumber, Integer parentNovelId) {
		Novel_board inputNb = new Novel_board();
		inputNb.setEpi_number(epiNumber);
		inputNb.setNovel_id(parentNovelId);
		
		inputNb = sn.getEpiContent(inputNb);
		
		ModelAndView mav = new ModelAndView("main");
		mav.addObject("BODY", "modifier.jsp");
		mav.addObject("novel_board", inputNb);
		
		
		return mav;
	}
	
	@RequestMapping(value="/home/loadNotice.html")
	public ModelAndView loadNotice( Integer pageNo) {
		
		Integer cnt =sa.countNoticeBno();
		
		
		PagingCondition c = new PagingCondition();
		c.paging(cnt, pageNo, 10);
		
		
		List<Notice_board> nociteList = sa.getNoticeList(c);
		//글마다 멤버객체를가지고있는다
				if(nociteList != null) {
					Iterator it = nociteList.iterator();
					int i = 0;
					while(it.hasNext()) {
						Notice_board ci =(Notice_board)it.next();
						ci.setMember(sm.checkEmail(ci.getEmail()));
						i++;
					}
					
					
				}
		
		ModelAndView mav = new ModelAndView("main");

		mav.addObject("NOTICE_LIST",nociteList);
		mav.addObject("COUNT", cnt);
		mav.addObject("pageCount",c.getPageCnt());
		mav.addObject("startRow",c.getStartRow());
		mav.addObject("endRow",c.getEndRow());
		mav.addObject("currentPage",c.getCurrentPage());
		mav.addObject("BODY", "noticeList.jsp");
		
		//답글 갯수
		
		return mav;
	}
	
	@RequestMapping(value="/home/loadNoticeReader.html")
	public ModelAndView loadNoticeReader(Integer bno) {
		
		ModelAndView mav = new ModelAndView("main");
		Notice_board notice = sa.getNoticeContent(bno);
		mav.addObject("BODY", "noticeReader.jsp");
		mav.addObject("notice", notice);
		return mav;
		
	}
	@RequestMapping(value="/home/report.html")
	public ModelAndView report(Integer pid, Integer epiNum, Integer bno) {
		ModelAndView mav = new ModelAndView("report");
		mav.addObject("bno", bno);
		mav.addObject("pid", pid);
		mav.addObject("epiNum", epiNum);
		return mav;
	}
	
}

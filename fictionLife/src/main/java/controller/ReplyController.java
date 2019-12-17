package controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import condition.PagingCondition;
import condition.PointCondition;
import logic.Service_Member;
import logic.Service_Novel;
import model.Member;
import model.Reply_novel;

@RestController
public class ReplyController {
	@Autowired
	private Service_Novel sn;
	
	@Autowired
	private Service_Member sm;
	
	@RequestMapping(value="/reply/loadReply.html")
	public String loadReply(Integer bno, Integer pageNo, HttpServletResponse response) {
		
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
				object.addProperty("rereCnt", sn.countReRe(ci.getRno()));
				array.add(object);
						i++;
					}
					
					
				}
	
		
		
		String json = gson.toJson(array);
		
		return json;
	}
	
	
	@RequestMapping(value="/reply/loadreReply.html")
	public String loadreReply(Integer rno, Integer pageNo, HttpServletResponse response) {
		
		//댓글 불러오기

		Integer cnt = sn.countReRe(rno);
		
		PagingCondition c = new PagingCondition();
		c.paging(cnt, pageNo, 10);c.setId(rno);
		
		List<Reply_novel> replyList = sn.getReREply(c);
		
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
				object.addProperty("rereCnt", sn.countReRe(ci.getRno()));
				object.addProperty("parentCnt", sn.countReRe(ci.getParent_no()));
				array.add(object);
						i++;
					}
					
					
				}
	
		
		
		String json = gson.toJson(array);
		
		return json;
	}
	
	
	@RequestMapping(value="/reply/reply.html")
	public String reply(Integer bno, String reply, HttpSession session) {
		
		//댓글인 경우
		Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
		Reply_novel rn = new Reply_novel();
		
	
		try {
		
			rn.setBno(bno);
			rn.setContent(reply);
			rn.setEmail(loginMember.getEmail());
			rn.setParent_no(0);
			
			
			sn.insertReply(rn);
			
			//novel_board 댓글카운트 1증가
			sn.addReplCnt(bno);
			
			//r_point 1 증가
			PointCondition pc = new PointCondition();
			pc.setPoint(1); pc.setEmail(loginMember.getEmail());
			sm.AddR_point(pc);
			return "replSuc";
		}catch(Exception e){
			return "replFail";
		}
	

	
		
	}
	

	@RequestMapping(value="/reply/reReply.html")
	public String reReply(Integer bno, String reply,Integer parent_no, HttpSession session) {
		System.out.println("패런트넘버: "+ parent_no);
		System.out.println("패런트넘버: "+ parent_no);
		System.out.println("패런트넘버: "+ parent_no);
		System.out.println("패런트넘버: "+ parent_no);
		System.out.println("패런트넘버: "+ parent_no);
		//댓글인 경우
		Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
		Reply_novel rn = new Reply_novel();
		
	
		try {
		
			rn.setBno(bno);
			rn.setContent(reply);
			rn.setEmail(loginMember.getEmail());
			rn.setParent_no(parent_no);
			
			
			sn.insertReply(rn);
			
			//novel_board 댓글카운트 1증가 이걸 rno로
			sn.addReplCntByRno(parent_no);
			
			//r_point 1 증가
			PointCondition pc = new PointCondition();
			pc.setPoint(1); pc.setEmail(loginMember.getEmail());
			sm.AddR_point(pc);
			return "replSuc";
		}catch(Exception e){
			
			return "replFail";
			
		}
	

	
		
	}
	
	
//	
//	@RequestMapping(value="/reply/reReply.html")
//	public ModelAndView reReply(Integer epi_number, Integer pni, Integer bno, String reply, Integer parent_no,
//			Integer rno,HttpSession session) {
//		//대댓글인 경우
//		Member loginMember = (Member)session.getAttribute("LOGINMEMBER");
//		RankCondition rc = (RankCondition)session.getAttribute("memberRank");
//		
//		Reply_novel rn = new Reply_novel();
//		Integer maxRno = sn.maxRno();
//		if(maxRno == null) maxRno =0;
//		
//		rn.setRno(maxRno+1);
//		rn.setBno(bno);
//		rn.setContent(reply);
//		rn.setNickname(loginMember.getNickname());
//		rn.setR_point(rc.getUr().getR_point());
//		rn.setParent_no(rno);
//		rn.setOrder_no(sn.getOrder_no(rn));
//		
//		
//		
//		
//		sn.insertReply(rn);
//		
//		sn.addReplCnt2(rno);
//		
//		ModelAndView mav = new ModelAndView("replyResult");
//		mav.addObject("epi_number",epi_number);
//		mav.addObject("pni",pni);
//		mav.addObject("bno",bno);
//		return mav;
//	}
//	
	@RequestMapping(value="/reply/deleteReply.html")
	@ResponseBody
	public String deleteReply(Integer rno) {
		
		String result = "";
		
		
		Reply_novel rn = new Reply_novel();
		
		
		
		rn.setRno(rno);
		rn.setContent("<font color='red'>삭제된 댓글입니다</font>");
	
		//db에서 업데이트
		try {
			sn.deleteReply(rn);
			
			result="deleSuc";
		}catch(Exception e) {
			result="delefail";
		}
		
	
		return result;
	}
}

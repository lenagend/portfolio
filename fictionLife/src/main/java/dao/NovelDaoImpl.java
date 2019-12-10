package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import condition.LikeyCondition;
import condition.PagingCondition;
import model.Favorite;
import model.Likey;
import model.Novel;
import model.Novel_board;
import model.Reply_novel;
import model.Report_novel;

@Repository
public class NovelDaoImpl implements NovelDao {

	@Autowired
	private SqlSession session;

	public Integer max_NovelId() {

		return session.selectOne("mapper.novelMapper.maxSeqno");
	}

	public void insertNovel(Novel novel) {
		session.insert("mapper.novelMapper.insertNovel", novel);
	}

	public List<Novel> findMyNovel(PagingCondition p) {

		return session.selectList("mapper.novelMapper.findMyNovel", p);
	}

	public void modifyNovel(Novel novel) {
		session.update("mapper.novelMapper.modifyNovel", novel);
	}

	public void deleteNovel(Integer id) {
		session.update("mapper.novelMapper.deleteNovel", id);
	}

	public void insertEpi(Novel_board novel_board) {
		session.insert("mapper.novelMapper.insertEpi", novel_board);

	}

	public Integer maxEpiBno() {

		return session.selectOne("mapper.novelMapper.maxEpiBno");
	}

	public Integer maxEpiNum(Integer id) {

		return session.selectOne("mapper.novelMapper.maxEpisode", id);
	}

	public void addEpi(Integer novelId) {
		session.update("mapper.novelMapper.addEpi", novelId);

	}

	public Integer getEpiCount(Integer novelId) {

		return session.selectOne("mapper.novelMapper.getEpiCount", novelId);
	}

	public List<Novel_board> getEpiList(PagingCondition pc) {

		return session.selectList("mapper.novelMapper.getEpiList", pc);
	}

	public Integer getMaxMyNovel(String email) {

		return session.selectOne("mapper.novelMapper.getMaxMyNovel", email);
	}

	public Novel findParentNovel(Integer id) {

		return session.selectOne("mapper.novelMapper.findParentNovel", id);
	}

	public Novel_board getEpiContent(Novel_board novel_board) {

		return session.selectOne("mapper.novelMapper.getEpiContent", novel_board);
	}

	public List<Novel> findAllNovel(PagingCondition p) {

		return session.selectList("mapper.novelMapper.findAllNovel", p);
	}

	public Integer countNovelList() {

		return session.selectOne("mapper.novelMapper.countNovelList");
	}
	public Integer favorite(Favorite f) {
	
		return session.selectOne("mapper.novelMapper.favoriteAlready", f);
	}
	public void insertFavorite(Favorite f) {
		session.insert("mapper.novelMapper.insertFavorite", f);
		
	}
	public Integer maxFavoriteSeqno() {
		
		return session.selectOne("mapper.novelMapper.maxFavoriteSeqno");
	}
	
	public List<Novel> myFavoriteNovels(PagingCondition f) {
		
		return session.selectList("mapper.novelMapper.getMyFavorite",f);
	}
	
	public Integer countMyFavorite(String email) {
		return session.selectOne("mapper.novelMapper.countMyFavorite", email);
	}
	
	public void plusFavorite(Integer novel_id) {
	session.update("mapper.novelMapper.plusFavorite", novel_id);
		
	}
	public void minusFavorite(Integer novel_id) {
		session.update("mapper.novelMapper.minusFavorite", novel_id);
	}
	
	public void deleteMyFavorite(Favorite f) {
		session.delete("mapper.novelMapper.deleteMyFavorite", f);
		
	}
	public void plusViewCnt(Integer bno) {
		session.update("mapper.novelMapper.plusViewCnt", bno);
		
	}
	public void modifyEpi(Novel_board nb) {
		session.update("mapper.novelMapper.modifyEpi", nb);
		
	}
	public List<Novel> findNovelByType(PagingCondition pc) {
		
		return session.selectList("mapper.novelMapper.findNovelByType", pc);
	}
	
	public Integer countTypeNovelList(String type) {
	
		return session.selectOne("mapper.novelMapper.countTypeNovelList", type);
	}

	public void addLikey(LikeyCondition lc ) {
		session.update("mapper.novelMapper.addLikey", lc);
		
	}
	public void likey(Likey likey) {
		session.insert("mapper.novelMapper.likey", likey);
		
	}
	public Integer likeyCheck(Likey likey) {
	
		return session.selectOne("mapper.novelMapper.likeyCheck", likey);
	}
	
	public Integer maxLikeySeqno() {
		
		return session.selectOne("mapper.novelMapper.maxLikeySeqno");
	}
	
	public void insertReport(Report_novel rn) {
		session.insert("mapper.novelMapper.insertReport", rn);
		
	}
	
	public Integer maxReportSeqno() {
		
		return session.selectOne("mapper.novelMapper.maxReportSeqno");
	}
	
	public Integer cherkReportAlready(Report_novel rn) {
		
		return session.selectOne("mapper.novelMapper.cherkReportAlready", rn);
	}
	
	public Integer countNotFinished(String email) {
	
		return session.selectOne("mapper.novelMapper.countNotFinished", email);
	}
	
	public void novelFinish(Integer id) {
		session.update("mapper.novelMapper.novelFinish", id);

	}

	public Integer thisNovelFinished(Integer id) {

		return session.selectOne("mapper.novelMapper.thisNovelFinished", id);
	}

	public Integer maxRno() {

		return session.selectOne("mapper.replyMapper.maxRno");
	}

	public void insertReply(Reply_novel rn) {
		session.insert("mapper.replyMapper.insertReply", rn);

	}

	public List<Reply_novel> getReplyByBno(PagingCondition pc) {

		return session.selectList("mapper.replyMapper.getReplyByBno", pc);
	}
	
	public Integer countReplyByBno(Integer bno) {
		
		return session.selectOne("mapper.replyMapper.countReplyByBno", bno);
	}

	public void addReplCnt(Integer bno) {
		session.update("mapper.replyMapper.addReplCnt", bno);

	}

	public Integer getOrder_no(Reply_novel rn) {

		return session.selectOne("mapper.replyMapper.getOrder_no", rn);
	}

	public void addReplCnt2(Integer rno) {
		session.update("mapper.replyMapper.addReplCnt2", rno);

	}

	public List<Reply_novel> getReREply(PagingCondition pc) {

		return session.selectList("mapper.replyMapper.getReREply", pc);
	}

	public void deleteReply(Reply_novel rn) {
		session.update("mapper.replyMapper.deleteReply", rn);
	}

	public Novel findTop1Novel() {

		return session.selectOne("mapper.novelMapper.findTop1Novel");
	}
	
	public Novel findTop2Novel() {

		return session.selectOne("mapper.novelMapper.findTop2Novel");
	}
	
	public Novel findTop3Novel() {

		return session.selectOne("mapper.novelMapper.findTop3Novel");
	}
	
	public Novel findTop4Novel() {

		return session.selectOne("mapper.novelMapper.findTop4Novel");
	}
	
	public Novel findTop5Novel() {

		return session.selectOne("mapper.novelMapper.findTop5Novel");
	}

	public List<Novel> findTop10NovelByReco_cnt() {

		return session.selectList("mapper.novelMapper.findTop10NovelByReco_cnt");
	}
	public Integer countSearchNovel(String search) {
		
		return session.selectOne("mapper.novelMapper.countSearchNovel", search);
	}
	
	public List<Novel> getSearchNovel(PagingCondition pc) {
		
		return session.selectList("mapper.novelMapper.getSearchNovel",pc);
	}
}

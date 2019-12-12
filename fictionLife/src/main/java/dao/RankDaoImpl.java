package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import condition.PointCondition;
import model.Icon;
import model.User_rank;

@Repository
public class RankDaoImpl implements RankDao {

	
	@Autowired
	private SqlSession session;

	private String MAPPER = "mapper.rankMapper.";

	public void createRank(User_rank ur) {
		session.insert(MAPPER + "insertRank", ur);

	}

	public Integer maxSeq() {

		return session.selectOne(MAPPER + "maxSeqno");
	}

	public User_rank getRank(String email) {

		return session.selectOne(MAPPER + "getMemberRank", email);
	}

	public Icon getIcon(Integer icon_id) {

		return session.selectOne(MAPPER + "getIcon", icon_id);
	}

	public void updateReaders(User_rank ur) {
		session.update(MAPPER+"updateReaders", ur);

	}
	public void AddR_point(PointCondition pc) {
		session.update(MAPPER+"AddR_point", pc);
		
	}
	public void AddW_point(PointCondition pc) {
		session.update(MAPPER+"AddW_point", pc);
		
	}

	
	public String getR_icon_ImageByNickname(String nickname) {
		
		return session.selectOne(MAPPER+"getR_icon_ImageByNickname", nickname);
	}
	
    public String getW_icon_ImageByEmail(String email) {
		
		return session.selectOne(MAPPER+"getW_icon_ImageByEmail", email);
	}
	
	
	
}

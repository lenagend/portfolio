package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Member;

@Repository
public class MemberDaoImpl implements dao.MemberDao{
	
	@Autowired
	private SqlSession session;
	
	private String MAPPER = "mapper.loginMapper.";
public Member getMember(Member member) {//아이디 비밀번호로 로그인확인
	
	return session.selectOne(MAPPER+"login", member);
}

public void putMember(Member member) {
	session.insert(MAPPER+"register", member);
	
}

public Member checkEmail(String inputEmail) {
	
	return session.selectOne(MAPPER+"emailCheck", inputEmail);
}

public Integer checkNickname(String inputNickname) {
	return session.selectOne(MAPPER+"nicknameCheck", inputNickname);
}

public Member findEmailByPhone(String phone) {
	return session.selectOne(MAPPER+"findEmail", phone);
}

public Integer adminLogin(String email) {
	
	return session.selectOne(MAPPER+"adminLogin", email);
}

public void modifyMember(Member member) {
	session.update(MAPPER+"modifyMember", member);
	
}

public void quitMember(String email) {
	session.update(MAPPER+"quitMember", email);
	
}
}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.LogonDataBean;
import model.MyPageDataBean;
import model.RoomDataBean;
import mybatis.AbstractRepository;

public class RoomDaoMybatis extends AbstractRepository {

	private final String namespace = "mybatis.Room";

	public void RoomInsert(RoomDataBean Bean) {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int number = 0;
		try {

			String statement = namespace + ".getArticleNum";
			number = sqlSession.selectOne(statement);
			Bean.setNum(number);
			statement = namespace + ".insert_max";
			sqlSession.insert(statement, Bean);
			sqlSession.commit();

		} finally {
			sqlSession.close();
		}
	}

	public List<RoomDataBean> getRoomList() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Connection conn = null;
		List<RoomDataBean> rList = new ArrayList<RoomDataBean>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			String statement = namespace + ".getArticleList";
			return sqlSession.selectList(statement);

		} finally {
			sqlSession.close();
		}

	}

	public RoomDataBean getRoom(int num) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			String statement = namespace + ".getArticle";
			return (RoomDataBean) sqlSession.selectOne(statement, num);

		} finally {
			sqlSession.close();
		}
	}

	public void RoomJoin(RoomDataBean Bean, String email, int i) {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		MyPageDataBean member = new MyPageDataBean();
		LogonDataBean logon;
		Map map = new HashMap<>();
		map.put("i", i);
		map.put("email", email);
		int number = 0;
		try {

			if (Bean.getNum() == 0) {
				String statement = namespace + ".getArticleNum";
				number = sqlSession.selectOne(statement);
				Bean.setNum(number);
			}

			String statement = namespace + ".getMember";
			logon = sqlSession.selectOne(statement, email);
			member.setLike_ca(Bean.getLike_ca());
			member.setMeet_title(Bean.getMeet_title());
			member.setEmail(email);
			member.setClassnum(Bean.getNum());
			member.setMembercnt(Bean.getMembercnt());
			member.setStatus(i);
			member.setBirthday(logon.getBirthday());
			member.setGender(logon.getGender());
			member.setName(logon.getName());
			
			
			statement = namespace + ".roomJoin";
			sqlSession.insert(statement, member);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	public int check(int num, String email) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap<>();
		map.put("num", num);
		map.put("email", email);
		try {
			System.out.println("@@@@@@@ceddd@");
			String statement = namespace + ".check";
			return sqlSession.selectOne(statement, map);

		} finally {
			sqlSession.close();
		}
	}

	public void RoomOut(String email, int num) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap<>();
		map.put("num", num);
		map.put("email", email);
		try {
			String statement = namespace + ".roomOut";
			sqlSession.delete(statement, map);
			sqlSession.commit();

		} finally {
			sqlSession.close();
		}

	}

}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.InterestDataBean;
import mybatis.AbstractRepository;

public class MybatisInterestDaoOracle extends AbstractRepository {

	private final String namespace = "mybatis.Interest";

	// 전체관심사 tit 가져오기
	public List<InterestDataBean> allgetTit() {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			String statement = namespace + ".allgetTit";
			return sqlSession.selectList(statement);

		} finally {
			sqlSession.close();
		}

	}

	// 전체관심사 tit 가져오기
	public List<InterestDataBean> getLikeSub(String checklist) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int likeNum = 0;
		try {
			String statement = namespace + ".getLikeSub_interestNum";
			/*sqlSession.selectOne(statement, checklist);*/

			likeNum = sqlSession.selectOne(statement, checklist);

			String statement2 = namespace + ".getLikeSub_interest_sub";

			return sqlSession.selectList(statement2, likeNum);

		} finally {
			sqlSession.close();
		}

	}

	// 회원 관심사 저장
	public void insertMemberLike(String memberLikeName, String name) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		InterestDataBean article;
		int total = 0;
		try {
			String statement = namespace + ".insertMemberLike_select";
			
			article = sqlSession.selectOne(statement, memberLikeName);
			
			Map map = new HashMap();
			map.put("internum", article.getInterNum());
			map.put("intersubnum", article.getIntersubNum());
			map.put("name", name);
			sqlSession.insert(namespace + ".insertMemberLike_insert", map);
			sqlSession.commit();
		} finally {
		sqlSession.close();
	}

	}
}

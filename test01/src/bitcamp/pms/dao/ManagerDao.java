package bitcamp.pms.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.pms.annotation.Component;
import bitcamp.pms.domain.Manager;

@Component
public class ManagerDao {    
  SqlSessionFactory sqlSessionFactory;

  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {    
    this.sqlSessionFactory = sqlSessionFactory;
  }  

  public ManagerDao() {}
  
  public List<Manager> selectList() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    try {
      return sqlSession.selectList("ManagerDao.selectList");
    } finally {     
      sqlSession.close();
    }    
  }
  
  public Manager selectOne(int no) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    try {
      HashMap<String, Object> paramMap  =  new HashMap<>();
      paramMap.put("no", no);
      return sqlSession.selectOne("ManagerDao.selectOne", paramMap);
    } finally {     
      sqlSession.close();
    }    
  } 

  public Manager selectOneByEmail(String email) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    try {
      HashMap<String, Object> paramMap  =  new HashMap<>();
      paramMap.put("email", email);
      return sqlSession.selectOne("ManagerDao.selectOne", paramMap);
    } finally {     
      sqlSession.close();
    }
  }
  
  
  public int insert(Manager manager) throws Exception {    
   SqlSession sqlSession = sqlSessionFactory.openSession(true); //< true값을 넘기면 autocommit설정
    try {           
      return sqlSession.insert("ManagerDao.insert", manager);            
      
    } finally {      
      sqlSession.close();
    }
  }
  
  public int update(Manager manager) throws Exception {    
    SqlSession sqlSession = sqlSessionFactory.openSession(); //< true값을 넘기면 autocommit설정  
    try {      
      int count = sqlSession.update("ManagerDao.update", manager);
      sqlSession.commit();
      return count;
    } finally {            
      sqlSession.close();
    }  
  }
 
  public int delete(int no) throws Exception {    
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {      
      int count = sqlSession.delete("ManagerDao.delete", no);
      sqlSession.commit();
      return count; 
    } finally {            
      sqlSession.close();
    }
  }

  public boolean isManager(String email, String password) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {      
      HashMap<String,String> paramMap = new HashMap<>();
      paramMap.put("email", email);
      paramMap.put("password", password);
      int count = sqlSession.selectOne("ManagerDao.isManager", paramMap);
      if (count > 0) {
        return true;
      } else {
        return false; 
      }
    } finally {            
      sqlSession.close();
    }    
  }

}

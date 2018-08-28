package yhl.util;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import yhl.dao.UserMapper;
import yhl.dao.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class MappperTest {

	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void testCRUD(){
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
//		ioc.getBean(UserMapper.class);
//		System.out.println(ioc);
	//	userMapper.insertSelective(new User(3,"a","a",1));
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		for(int i=4;i<1000;i++){ 
			String uid =UUID.randomUUID().toString().substring(0, 5)+i; //利用uid自动生成姓名
			mapper.insertSelective(new User(i,"uid",uid+"heetian",12)); //id自增 
		}
		System.out.println("批量完成");
	}
}

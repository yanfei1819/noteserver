package org.tarena.note.dao.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.User;

public class UserMapperDaoJunitTest {
	private UserMapperDao userDao;
	
	@Before//每次调用test方法都执行一次
	public void init(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		userDao = ac.getBean(
				"userMapperDao",UserMapperDao.class);
	}
	
	/**
	 * 测试用例1
	 * 传入demo
	 * 传出user对象不为null
	 */
	@Test
	public void test1(){
		User user = userDao.findByName("demo");
		//使用断言机制判断user不为null
		Assert.assertNotNull(user);
	}
	
	/**
	 * 测试用例2
	 * 传入demo1
	 * 传出user对象为null
	 */
	@Test
	public void test2(){
		User user = userDao.findByName("demo1");
		//使用断言机制判断user为null
		Assert.assertNull(user);
	}
	
}

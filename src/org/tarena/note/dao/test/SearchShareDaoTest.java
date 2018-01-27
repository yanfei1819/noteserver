package org.tarena.note.dao.test;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.SearchBean;
import org.tarena.note.entity.Share;

public class SearchShareDaoTest {
	
	private NoteMapperDao noteDao;
	
	@Before//每次调用test方法都执行一次
	public void init(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		noteDao = ac.getBean("noteMapperDao",NoteMapperDao.class);
	}
	
	@Test
	public void test1(){
		SearchBean bean = new SearchBean();
		List<Map> list = noteDao.searchShare(bean);
		for(Map row : list){
			System.out.println(
				row.get("cn_share_title")+" "
				+row.get("cn_note_create_time")+" "
				+row.get("cn_user_name"));
		}
		Assert.assertEquals(38, list.size());
	}
	
	@Test
	public void test2(){
		SearchBean bean = new SearchBean();
		bean.setTitle("java");
		List<Map> list = noteDao.searchShare(bean);
		for(Map row : list){
			System.out.println(
				row.get("cn_share_title")+" "
				+row.get("cn_note_create_time")+" "
				+row.get("cn_user_name"));
		}
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void test3(){
		SearchBean bean = new SearchBean();
		bean.setTitle("a");
		Date begin = Date.valueOf("2015-06-15");
		bean.setBeginDate(begin.getTime());
		Date end = Date.valueOf("2015-06-20");
		bean.setEndDate(end.getTime());
		List<Map> list = noteDao.searchShare(bean);
		for(Map row : list){
			System.out.println(
				row.get("cn_share_title")+" "
				+row.get("cn_note_create_time")+" "
				+row.get("cn_user_name"));
		}
		//Assert.assertEquals(2, list.size());
	}
}

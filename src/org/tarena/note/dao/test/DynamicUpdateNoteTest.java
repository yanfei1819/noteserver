package org.tarena.note.dao.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;

public class DynamicUpdateNoteTest {
	
	private NoteMapperDao noteDao;
	
	@Before//每次调用test方法都执行一次
	public void init(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		noteDao = ac.getBean("noteMapperDao",NoteMapperDao.class);
	}
	
	//测试更新删除
	@Test
	public  void test1(){
		Note note = new Note();
		note.setCn_note_id("9187ffd3-4c1e-4768-9f2f-c600e835b823");
		note.setCn_note_status_id("2");
		int rows = noteDao.dynamicUpdateNote(note);
		Assert.assertEquals(1, rows);
	}
	
	//测试更新笔记内容
	@Test
	public  void test2(){
		Note note = new Note();
		note.setCn_note_id("9187ffd3-4c1e-4768-9f2f-c600e835b823");
		note.setCn_note_title("spring");
		note.setCn_note_body("springspringspring");
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		int rows = noteDao.dynamicUpdateNote(note);
		Assert.assertEquals(1, rows);
	}
	
	//测试笔记转移
	@Test
	public  void test3(){
		Note note = new Note();
		note.setCn_note_id("9187ffd3-4c1e-4768-9f2f-c600e835b823");
		note.setCn_notebook_id("c6e261c4-7ca8-44c1-a933-aa9320506949");
		int rows = noteDao.dynamicUpdateNote(note);
		Assert.assertEquals(1, rows);
	}
	
}

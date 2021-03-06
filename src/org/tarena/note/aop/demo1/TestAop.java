package org.tarena.note.aop.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String conf = "org/tarena/note/aop/demo1/spring-aop.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		SimpleService service = 
			ac.getBean("simpleService",SimpleService.class);
		service.add();
		service.delete();
		service.update();
		service.findAll();
		service.findById();
	}

}

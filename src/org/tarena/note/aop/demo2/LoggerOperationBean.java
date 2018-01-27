package org.tarena.note.aop.demo2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component//�����ɨ�赽����
@Aspect//�����������ɷ���
public class LoggerOperationBean {
	
	//����ǰ��֪ͨ���뵽SimpleService������
//	@Before("within(org.tarena.note.aop.demo2.SimpleServiceImpl)")//ǰ��֪ͨ
	public void logger(){
		System.out.println("��¼������־����");
	}
	
	@Around("within(org.tarena.note.aop.demo2.SimpleServiceImpl)")
	public Object doIntransaction(ProceedingJoinPoint pjp) throws Throwable{
		try{
			System.out.println("ִ��conn.setAutoCommit(false)");
			Object retval = pjp.proceed();//����Ŀ���������
			System.out.println("ִ��conn.commit()");
			return retval;
		}catch(Exception ex){
			System.out.println("ִ��conn.rollback()");
			return null;
		}finally{
			System.out.println("ִ��conn.close()");
		}
	}
	
}

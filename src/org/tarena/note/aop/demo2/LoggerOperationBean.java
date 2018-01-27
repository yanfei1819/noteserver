package org.tarena.note.aop.demo2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component//将组件扫描到容器
@Aspect//将该组件定义成方面
public class LoggerOperationBean {
	
	//采用前置通知切入到SimpleService方法上
//	@Before("within(org.tarena.note.aop.demo2.SimpleServiceImpl)")//前置通知
	public void logger(){
		System.out.println("记录操作日志功能");
	}
	
	@Around("within(org.tarena.note.aop.demo2.SimpleServiceImpl)")
	public Object doIntransaction(ProceedingJoinPoint pjp) throws Throwable{
		try{
			System.out.println("执行conn.setAutoCommit(false)");
			Object retval = pjp.proceed();//调用目标组件方法
			System.out.println("执行conn.commit()");
			return retval;
		}catch(Exception ex){
			System.out.println("执行conn.rollback()");
			return null;
		}finally{
			System.out.println("执行conn.close()");
		}
	}
	
}

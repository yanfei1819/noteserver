package org.tarena.note.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionLogger {
	
	Logger logger = Logger.getLogger(ExceptionLogger.class);
	
	//throwing属性与方法参数名保持一致
	@AfterThrowing(pointcut="within(org.tarena.note.service..*)",throwing="ex")
	public void execute(Exception ex){//ex就是目标组件抛出的异常
		//将目标组件抛出的异常记录到文件
		logger.error("=========发生"+ex+"错误=============");
		StackTraceElement[] els = ex.getStackTrace();
		for(StackTraceElement e : els){
			logger.error(e);
		}
		
	}
}

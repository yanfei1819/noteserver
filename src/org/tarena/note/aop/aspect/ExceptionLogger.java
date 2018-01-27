package org.tarena.note.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionLogger {
	
	Logger logger = Logger.getLogger(ExceptionLogger.class);
	
	//throwing�����뷽������������һ��
	@AfterThrowing(pointcut="within(org.tarena.note.service..*)",throwing="ex")
	public void execute(Exception ex){//ex����Ŀ������׳����쳣
		//��Ŀ������׳����쳣��¼���ļ�
		logger.error("=========����"+ex+"����=============");
		StackTraceElement[] els = ex.getStackTrace();
		for(StackTraceElement e : els){
			logger.error(e);
		}
		
	}
}

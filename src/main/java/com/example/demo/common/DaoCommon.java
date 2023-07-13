package com.example.demo.common;

import java.util.GregorianCalendar;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DaoCommon {
	@Pointcut("execution(public * com.example.demo.dao..*(..))")
	private void pro() {}
	
	//(After로 동작시키는 코드)타깃메소드가 정상완료 하거나 비정상완료해도 반드시 동작하는 어드바이스를 만듭니다.
	//@After("pro()")
	public void after(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toShortString();
		System.out.println(methodName +"메소드가 정상이던 비정상이던 완료되었습니다.");
	}
	
	//(AfterThrowing으로 동작시키는 코드)타깃메소드가 비정상 완료되었을 때 동작하는 어드바이스를 만듭니다.
	//@AfterThrowing("pro()")
	public void AfterThrowing(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().toShortString();
		System.out.println(methodName+"메소드가 비정상 완료되었습니다.");
	}
	
	//(AfterReturning 으로 동작시키는 코드)타깃메소드가 정상 완료되었을 때 동작하는 어드바이스를 만들고 결과를 확인해봅시다.
	//@AfterReturning("pro()")
	public void afterReturn(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().toShortString();
		System.out.println(methodName+"타깃메소드가 정상완료되었습니다.");
	}
	
	//(Before advice로 동작시키는 코드)메소드 동작 전 실행 : 매개변수를 반드시 가지지 않아도 되지만 타겟메소드를 갖고 싶다면 선택적으로 joinPoint를 가질 수 있음
	@Before("pro()")
	public void before(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		
		// 클래스이름까지만 짧게 toShortString() -> toShortStringDeptDAO.findAll()
		String name1 = joinpoint.getSignature().toShortString();
		
		// 메소드이름까지 길게 toLongString() -> toLongStringpublic java.util.List com.example.demo.dao.DeptDAO.findAll()
		String name2 = joinpoint.getSignature().toLongString();
		
		GregorianCalendar calendar = new GregorianCalendar();
		int yy = calendar.get(GregorianCalendar.YEAR);
		int mm = calendar.get(GregorianCalendar.MONTH)+1;
		int dd = calendar.get(GregorianCalendar.DAY_OF_MONTH);
		int hh = calendar.get(GregorianCalendar.HOUR_OF_DAY);
		int MM = calendar.get(GregorianCalendar.MINUTE);
		int ss = calendar.get(GregorianCalendar.SECOND);
		
		System.out.println(methodName+"타깃메소드 동작하기 전입니다.");
		System.out.println("toShortString"+name1);
		System.out.println("toLongString"+name2);
		System.out.println(yy +"년"+mm+"월"+dd+"일 "+hh+":"+MM+":"+ss);
	}

	//(Around advice로 동작시키는 코드)매개변수를 반드시 가져 조인포인트를 반드시 설정
	//@Around("pro()")
	public Object around(ProceedingJoinPoint joinPoint) {
		Object obj = null;
		try {
			long start = System.currentTimeMillis();
			System.out.println("타깃메소드 동작하기 전입니다.");
			
			//타깃메소드를 호출함(Around advice는 조인포인트를 반드시 설정해주어야 합니다.)
			obj = joinPoint.proceed();
			
			long end = System.currentTimeMillis();
			System.out.println("타깃메소드 동작한 후입니다.");
			System.out.println("걸린시간:"+(end-start));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
		}
		return obj;
	}
}

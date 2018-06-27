package com.lionel.utils.aop;

import java.util.List;

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

import com.lionel.model.property.Point;

@Component
@Aspect
public class CheckPointAOP {
	
	@Pointcut("execution(* com.lionel.service.property.impl.CheckPointServiceImpl.addPoint(..))")
	public void pointCut() {}
	
	@Pointcut("@annotation(com.lionel.utils.aop.Register)")
	public void pointCut1() {}
	
	//@Before("pointCut()")
	public void beginInsert(JoinPoint joinPoint) {
		System.out.println("即将插入巡检点");
		Object[] parms=joinPoint.getArgs();
		Point point=(Point)parms[0];
		System.out.println("参数为："+point.getPointLocation());
	}
	
	//@After("pointCut()")
	public void endInsert() {
		System.out.println("插入巡检点结束");
	}
	
	@AfterReturning(returning="entity",value="execution(* com.lionel.service.property.impl.CheckPointServiceImpl.slectPoint(..))")
	public void getReturn(JoinPoint joinPoint,Object entity) {
		List<Point> list=(List<Point>)entity;
		System.out.println("巡检点列表返回了多少条："+list.size());
	}
	
	
	@AfterThrowing(throwing="e",value="pointCut()")
	public void checkPointThrow() {
		System.out.println("插入巡检点异常");
	}
	
	//获取商场名字
	@Before(value = "@annotation(register)")
	public void  getRegister(JoinPoint joinPoint,Register register) throws Throwable {
		System.out.println("自定义注解切入");
	}
}

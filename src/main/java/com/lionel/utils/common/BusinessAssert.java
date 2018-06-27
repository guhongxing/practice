package com.lionel.utils.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.lionel.exception.ServiceExceptions;

/**
 * 业务校验
 *
 */
public abstract class BusinessAssert {

	public static void equal(Object excepted, Object target,Integer errCode, String messgae) {
		if (excepted == null || target == null || !excepted.equals(target)) {
			throw new ServiceExceptions(errCode,messgae);
		}
	}

	public static void notEqual(Object excepted, Object target,Integer errCode,String message) {
		if (excepted == null || target == null || excepted.equals(target)) {
			throw new ServiceExceptions(errCode,message);
		}
	}

	public static void toLarge(BigDecimal excepted, BigDecimal target,Integer errCode, String message) {
		if (target.doubleValue() > excepted.doubleValue()) {
			throw new ServiceExceptions(errCode,message);
		}
	}


	public static void notNull(Integer errCode,String message,Object ...objects){
		for(Object obj:objects ){
			if(obj == null){
				throw new ServiceExceptions(errCode,message);
			}
		}
	}

	public static void notEmpty(Integer errCode,String message,String...params){
		for(String str:params){
			if(Objects.equals("",str)){
				throw new ServiceExceptions(errCode,message);
			}
		}
	}
	
	public static void notNullOrEmpty(Integer errCode,String message,String...params){
		for(String str:params){
			if(str == null || Objects.equals("",str)){
				throw new ServiceExceptions(errCode,message);
			}
		}
	}

    public static void notEmpty(List<?> list, Integer errCode, String message) {
        if (list == null || list.isEmpty()) {
            throw new ServiceExceptions(errCode, message);
        }
    }

	public static void fail(Integer errCode,String message) {
		throw new ServiceExceptions(errCode,message);
	}

	public static ServiceExceptions exception(Integer errCode,String message) {
		return new ServiceExceptions(errCode,message);
	}

}

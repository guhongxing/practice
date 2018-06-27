package com.lionel.utils.redis;

import java.util.ArrayList;
import java.util.List;

import com.lionel.model.redis.Student;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisDemo {
	
	
	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("localhost", 6379);
		//存入对象
		Student student=new Student(1, "lionel", 18, "男");
		Student student2=new Student(1, "lionel", 18, "男");
		Student student3=new Student(1, "lionel", 18, "男");
		List<Student> list=new ArrayList<Student>() ;
		list.add(student);
		list.add(student2);
		list.add(student3);
		//jedis.set(("student"+student.getId()).getBytes(), SerializeUtil.serialize(student));
		jedis.set("list".getBytes(), SerializeUtil.serialize(list));
		
		//Student student1=(Student)SerializeUtil.unserialize(jedis.get("student1".getBytes()));
		//System.out.println(student1.getSname());
		
		List<Student> studentlist=(List<Student> )SerializeUtil.unserialize(jedis.get("list".getBytes()));
		System.out.println(studentlist.get(2).getSname());
	}

	public static void testInsert() {  
	    long currentTimeMillis = System.currentTimeMillis();  
	    Jedis jedis = new Jedis("localhost", 6379);  
	    for (int i = 0; i < 1000; i++) {  
	        jedis.set("test" + i, "test" + i);  
	    }  
	    long endTimeMillis = System.currentTimeMillis();  
	    System.out.println(endTimeMillis - currentTimeMillis);  
	}
	
	// 测试管道  
	public static void testPip() {  
	    long currentTimeMillis = System.currentTimeMillis();  
	    Jedis jedis = new Jedis("localhost", 6379);  
	    Pipeline pipelined = jedis.pipelined();  
	    for (int i = 0; i < 1000; i++) {  
	        pipelined.set("bb" + i, i + "bb");  
	    }  
	    pipelined.sync();  
	    long endTimeMillis = System.currentTimeMillis();  
	    System.out.println(endTimeMillis - currentTimeMillis);  
	}  
	
	public static void addObject(Student student) {
		
	}

}

package com.lionel.service.redis;

import java.util.List;

import com.lionel.model.redis.Student;

public interface RedisService {

	void batchInsert();
	String findNameById(int sid);
	Integer findIdByName(String sname);
	void updateNameById(Student student);
	List<Student> selectByCondition(String sex);
}

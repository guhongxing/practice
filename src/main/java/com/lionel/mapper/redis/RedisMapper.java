package com.lionel.mapper.redis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lionel.model.redis.Student;

public interface RedisMapper {
	
	int Insert(Student student);
	Student findNameByid(@Param("sid")Integer sid);
	Student findIdByName(@Param("sname")String sname);
	void updateNameById(Student student);
	List<Student> selectByCondition(String sex);
}

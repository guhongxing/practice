package com.lionel.service.redis.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lionel.exception.ServiceExceptions;
import com.lionel.mapper.redis.RedisMapper;
import com.lionel.model.redis.Student;
import com.lionel.service.redis.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

	@Autowired
	RedisMapper redisMapper;

	@Override
	public void batchInsert() {
		for (int i = 1; i <= 1000000; i++) {
			String sname = "Student" + i;
			int agea = i % 4;
			int sage;
			String sex = "男";
			switch (agea) {
			case 0:
				sage = 18;
				sex = "男";
				break;
			case 1:
				sage = 19;
				sex = "女";
				break;
			case 2:
				sage = 20;
				sex = "男";
				break;
			case 3:
				sage = 21;
				sex = "女";
				break;
			default:
				sage = 28;
				sex = "男";
				break;
			}
			Student student = new Student(i, sname, sage, sex);
			redisMapper.Insert(student);
		}

	}

	@Override
	@Cacheable(value = "idNameCache")
	public String findNameById(int sid) {
		Student student = redisMapper.findNameByid(sid);
		return student.getSname();
	}

	@Override
	@Cacheable(value = "idNameCache")
	public Integer findIdByName(String sname) {
		
		Student student = redisMapper.findIdByName(sname);
		if (student == null)
			throw new ServiceExceptions(10001, "查无此人");
		return student.getSid();
	}

	@Override
	@CacheEvict(value = "idNameCache", key = "#student.getSid()")
	public void updateNameById(Student student) {
		Student result = redisMapper.findNameByid(student.getSid());
		if (result == null)
			throw new ServiceExceptions(10001, "查无此人");
		redisMapper.updateNameById(student);
	}

	@Override
	public List<Student> selectByCondition(String sex) {
		
		return redisMapper.selectByCondition(sex);
	}

}

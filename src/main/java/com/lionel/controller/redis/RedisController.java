package com.lionel.controller.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lionel.base.base.ResponseBean;
import com.lionel.model.redis.Student;
import com.lionel.service.redis.RedisService;

@RestController
@RequestMapping("/redis")
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/batchInsert")
	public ResponseBean batchInsert() {
		redisService.batchInsert();
		return ResponseBean.success();
	}
	
	@RequestMapping("/finNameByid")
	public ResponseBean finNameByid(Integer sid) {
		String sname=redisService.findNameById(sid);
		Map<String, String> map=new HashMap<String,String>();
		map.put("name", sname);
		return ResponseBean.success(map);
	}
	@RequestMapping("/finIdByName")
	public ResponseBean finIdByName(String sname) {
		int sid=redisService.findIdByName(sname);
		Map<String, String> map=new HashMap<String,String>();
		map.put("sid", ""+sid);
		return ResponseBean.success(map);
	}
	@RequestMapping("/updateNameById")
	public ResponseBean updateNameById(String sname,int sid) {
		Student student=new Student();
		student.setSid(sid);
		student.setSname(sname);
		redisService.updateNameById(student);
		return ResponseBean.success();
	}
	@RequestMapping("/selectByCondition")
	public ResponseBean selectByCondition(Integer pageNum,Integer pageSize,Student student) {
		Page<Student> page = PageHelper.startPage(pageNum, pageSize);
		List<Student> list=redisService.selectByCondition(student.getSex());
		return ResponseBean.success(page, list);
	}

}

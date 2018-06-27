package com.lionel.controller.property;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lionel.base.base.ResponseBean;
import com.lionel.model.property.Condition;
import com.lionel.model.property.Point;
import com.lionel.model.redis.Student;
import com.lionel.service.property.CheckPointService;
import com.lionel.utils.aop.Register;
import com.lionel.utils.common.BusinessAssert;
import com.lionel.utils.common.DateUtil;

@RestController
@RequestMapping("/property/checkPoint")
public class CheckPointController {
	
	@Autowired
	private CheckPointService checkPointService;
	
	/*@RequestMapping("/add")
	public ResponseBean addPoint(String mallId, String serialNumber, String pointName,
			String pointLocation, String registerName) {
		BusinessAssert.notNullOrEmpty(601, "参数为空", mallId,serialNumber,pointName,pointLocation,registerName);
		Point point=new Point(mallId, serialNumber, pointName, pointLocation, registerName);
		checkPointService.addPoint(point);
		return ResponseBean.success();
	}*/
	
	@RequestMapping("/add")
	public ResponseBean addPoint(Point point) {
		BusinessAssert.notNullOrEmpty(601, "必须参数为空", point.getmallId(),point.getSerialNumber(),
				point.getPointName(),point.getPointLocation(),point.getRegisterName());
		checkPointService.addPoint(point);
		return ResponseBean.success();
	}
	
	@RequestMapping("/update")
	public ResponseBean updatePoint(Point point) {
		BusinessAssert.notNullOrEmpty(601, "必须参数为空",point.getPointId());
		checkPointService.update(point);
		
		return ResponseBean.success();
	}
	
	
	@RequestMapping("/pointList")
	public ResponseBean pointList(Condition condition) {

		Page<Student> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
		List<Point> list=checkPointService.slectPoint(condition);
		return ResponseBean.success(page,list);
	}


}

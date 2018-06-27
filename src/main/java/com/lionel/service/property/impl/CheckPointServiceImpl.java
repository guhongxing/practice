package com.lionel.service.property.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lionel.exception.ServiceExceptions;
import com.lionel.mapper.property.PointMapper;
import com.lionel.model.property.Condition;
import com.lionel.model.property.Point;
import com.lionel.service.property.CheckPointService;

@Service
public class CheckPointServiceImpl implements CheckPointService {

	@Autowired
	private PointMapper pointMapper;
	@Override
	public void addPoint(Point point) {
		String max=pointMapper.primaryKeyMax(point.getmallId()+"%");
		if(max==null)
			point.setPointId(point.getmallId()+"00001");
		else {
			int maxIndex=Integer.parseInt(max)+1;
			point.setPointId(String.format("%08d", maxIndex));
		}
		point.setRegisterTime(new Date());
		pointMapper.insert(point);
	}
	@Override
	public List<Point> slectPoint(Condition condition) {
		/*if(condition.getEndTime() != null) {
			Calendar   calendar = new GregorianCalendar(); 
			calendar.setTime(condition.getEndTime()); 
			calendar.add(calendar.DATE,1);
			condition.setEndTime(calendar.getTime());
		}*/
		return pointMapper.selectAll(condition);
	}
	@Override
	public Point getPoint(String pointId) {
		
		return pointMapper.selectByPrimaryKey(pointId);
	}
	@Override
	public void update(Point point) {
		Point original=pointMapper.selectByPrimaryKey(point.getPointId());
		if(original ==null) {
			throw new ServiceExceptions(500, "point_is_null");
		}
		point.setUpdateTime(new Date());
		pointMapper.updateByPrimaryKey(point);
	}

}
